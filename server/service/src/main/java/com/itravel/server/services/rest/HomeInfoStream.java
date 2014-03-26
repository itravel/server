package com.itravel.server.services.rest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.itravel.server.interfaces.dal.EntityType;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.ITravelNote;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;
import com.itravel.server.services.aos.Constants;
import com.itravel.server.services.aos.InformationStream;
import com.itravel.server.services.utils.ManagerFactory;


@Path("infostreams")
public class HomeInfoStream {
	
	private static final class TravelNote2InformationStream implements Function<ITravelNote,InformationStream>{
		@Override
		public InformationStream apply(ITravelNote input) {
			// TODO Auto-generated method stub
			return new InformationStream(EntityType.travelnote,input);
		}
	}
	
	private static final class Activities2InformationStream implements Function<IActivities,InformationStream>{
		@Override
		public InformationStream apply(IActivities input) {
			// TODO Auto-generated method stub
			return new InformationStream(EntityType.attraction,input);
		}
	}
	
	private static final class FetchTravelNotesTask implements Callable<List<ITravelNote>> {
		private final ITravelNoteManager tManager = ManagerFactory.getTravelNoteManager();
		private int offset;
		private int count;
		public FetchTravelNotesTask(int offset,int count){
			this.offset = offset;
			this.count = count;
		}
		@Override
		public List<ITravelNote> call() throws Exception {
			List<ITravelNote> travalNotes = tManager.getRange(offset, count);
			return travalNotes;
		}
	}
	
	private static final class FetchActivitiesTask implements Callable<List<IActivities>> {
		private final IActivitiesManager aManager = ManagerFactory.getActivitiesManager();
		private int offset;
		private int count;
		public FetchActivitiesTask(int offset,int count){
			this.offset = offset;
			this.count = count;
		}
		@Override
		public List<IActivities> call() throws Exception {
			List<IActivities> activities = aManager.getRange(offset, count);
			return activities;
		}
	}
	
	ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
	private Logger logger = LogManager.getLogger(Constants.LOGGER);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoStream(@QueryParam(value = "start") int start,@QueryParam(value="count") int count){
		final CountDownLatch cdl = new CountDownLatch(1);
		final List<InformationStream> stream = Lists.newArrayListWithCapacity(count);
		// add the travelNotes to informationstream;
		ListenableFuture<List<ITravelNote>> getTravelNoteFuture = service.submit(new FetchTravelNotesTask(start,count));
		Futures.addCallback(getTravelNoteFuture, new FutureCallback<List<ITravelNote>>(){
			@Override
			public void onSuccess(List<ITravelNote> travalNotes) {
				// TODO Auto-generated method stub
				stream.addAll(Lists.transform(travalNotes, new TravelNote2InformationStream()));
				cdl.countDown();
			}
			@Override
			public void onFailure(Throwable t) {
				// TODO Auto-generated method stub
				logger.error(t);
				cdl.countDown();
			}});
		// TODO add the activities to informationstream;
		ListenableFuture<List<IActivities>> getActivitiesFuture = service.submit(new FetchActivitiesTask(start,count));
		Futures.addCallback(getActivitiesFuture, new FutureCallback<List<IActivities>>() {

			@Override
			public void onSuccess(List<IActivities> activities) {
				// TODO Auto-generated method stub
				
				stream.addAll(Lists.transform(activities, new Activities2InformationStream()));
				cdl.countDown();
			}

			@Override
			public void onFailure(Throwable t) {
				// TODO Auto-generated method stub
				logger.error(t);
				cdl.countDown();
			}
			
		});
		
		try {
			cdl.await(5, TimeUnit.SECONDS);
			return Response.ok().entity(stream).build();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.REQUEST_TIMEOUT).entity("Time out").build();
		}
	}
	
}

