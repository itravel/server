package com.itravel.server.dal.spatial;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.itravel.server.dal.asyn.FetchActivitiesTask;
import com.itravel.server.dal.asyn.FetchAttractionTask;
import com.itravel.server.dal.managers.ActivitiesManager;
import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.interfaces.dal.EntityType;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;
import com.itravel.server.interfaces.dal.managers.ISpatialSearchManager;

public final class ActivitiesSpatialManager extends
		AbstractSpatialManager<IActivities> implements ISpatialSearchManager<IActivities> {
	private static final class ActivitiesSpatialManagerHolder {
		private static final ActivitiesSpatialManager INSTANCE = new ActivitiesSpatialManager();
	}
	
	public static final ActivitiesSpatialManager getInstance(){
		return ActivitiesSpatialManagerHolder.INSTANCE;
	}
	private final IActivitiesManager manager = ActivitiesManager.getInstance();
	@Override
	public void addIndex(IActivities poi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addIndex(Collection<IActivities> pois) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIndex(IActivities poi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIndex(Collection<IActivities> pois) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initIndex() {
		// TODO Auto-generated method stub
		IActivitiesManager aManager = new ActivitiesManager();
		final int BATCHSIZE = 300;
		final int round = (int) (aManager.size()/BATCHSIZE) +1;
		final CountDownLatch cdl = new CountDownLatch(round);
		try {
			final IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			for(int i=0;i<round;i++){
				ListenableFuture<List<IActivities>> future = service.submit(new FetchActivitiesTask(i*BATCHSIZE, BATCHSIZE, aManager));
				Futures.addCallback(future,
						new FutureCallback<List<IActivities>>() {

							@Override
							public void onSuccess(List<IActivities> activities) {
								// TODO Auto-generated method stub
								try {
									for(IActivities attraction:activities){
										indexWriter.addDocument(createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude(),EntityType.attraction));
									}
									
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								finally{
									cdl.countDown();
								}
							}

							@Override
							public void onFailure(Throwable t) { // TODO
								// Auto-generated method stub
							}
						});
			}
				try {
					cdl.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				indexWriter.close();
				logger.info("activities spatial index init finished");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	@Override
	public List<IActivities> getByLatLnt(double latitude, double longitude) {
		// TODO Auto-generated method stub
		return this.getByLatLnt(latitude, longitude, 10);
	}
	@Override
	public List<IActivities> getByLatLnt(double latitude, double longitude,
			int radius) {
		// TODO Auto-generated method stub
		List<Long> ids = this.search(EntityType.activity, longitude, latitude, radius);
		List<IActivities> result = Lists.newLinkedList();
		for (long id : ids) {
			result.add(this.manager.get(id));
		}
		return result;
	}
	

}
