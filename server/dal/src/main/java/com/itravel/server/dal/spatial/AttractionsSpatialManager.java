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
import com.itravel.server.dal.asyn.FetchAttractionTask;
import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.interfaces.dal.EntityType;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;
import com.itravel.server.interfaces.dal.managers.ISpatialSearchManager;

public final class AttractionsSpatialManager extends AbstractSpatialManager<IAttractions> implements ISpatialSearchManager<IAttractions>{
	private static final class AttrationsSpatialManagerHolder {
		private static final AttractionsSpatialManager INSTANCE = new AttractionsSpatialManager();
		
	}
	private final IAttractionsManager manager = AttractionsManager.getInstance();
	public AttractionsSpatialManager(){
		
	}

	@Override
	public void addIndex(IAttractions attraction) {
		// TODO Auto-generated method stub
		try {
			IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			indexWriter.addDocument(this.createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude(),EntityType.attraction));

			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void addIndex(Collection<IAttractions> pois) {
		// TODO Auto-generated method stub
		try {
			IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			for (IAttractions attraction : pois) {
				indexWriter.addDocument(this.createLntLatPoint(attraction.getId(), attraction.getLatitude(), attraction.getLongitude(),EntityType.attraction));
			}
			
			indexWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AttractionsSpatialManager getInstance() {
		return AttrationsSpatialManagerHolder.INSTANCE;
	}

	@Override
	public void deleteIndex(IAttractions poi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIndex(Collection<IAttractions> pois) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		final AttractionsSpatialManager asManager = new AttractionsSpatialManager();
		try {
			asManager.initIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		AttractionsManager aManager = new AttractionsManager();
//		final int BATCHSIZE = 300;
//		final int round = (int) (aManager.size()/BATCHSIZE) +1;
//		
//		for(int i=0;i<round;i++){
////			System.err.println(aManager.getRange(2, 2));
//			ListenableFuture<List<IAttractions>> future = service.submit(new FetchAttractionTask(i*BATCHSIZE, BATCHSIZE, aManager));
//			Futures.addCallback(future,
//					new FutureCallback<List<IAttractions>>() {
//
//						@Override
//						public void onSuccess(List<IAttractions> attractions) {
//							// TODO Auto-generated method stub
//							
//							asManager.addIndex(attractions);
//						}
//
//						@Override
//						public void onFailure(Throwable t) { // TODO
//							// Auto-generated method stub
//						}
//					});
//		}
		
	}

	@Override
	public void initIndex() {
		AttractionsManager aManager = new AttractionsManager();
		final int BATCHSIZE = 300;
		final int round = (int) (aManager.size()/BATCHSIZE) +1;
		final CountDownLatch cdl = new CountDownLatch(round);
		try {
			final IndexWriter indexWriter = new IndexWriter(directory,
					new IndexWriterConfig(Version.LUCENE_46, null));
			for(int i=0;i<round;i++){
				ListenableFuture<List<IAttractions>> future = service.submit(new FetchAttractionTask(i*BATCHSIZE, BATCHSIZE, aManager));
				Futures.addCallback(future,
						new FutureCallback<List<IAttractions>>() {

							@Override
							public void onSuccess(List<IAttractions> attractions) {
								// TODO Auto-generated method stub
								try {
									for(IAttractions attraction:attractions){
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
				logger.info("attractions spatial index init finished");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public List<IAttractions> getByLatLnt(double latitude, double longitude) {
		// TODO Auto-generated method stub
		return this.getByLatLnt(latitude, longitude, 50);
	}

	@Override
	public List<IAttractions> getByLatLnt(double latitude, double longitude,
			int radius) {
		// TODO Auto-generated method stub
		List<Long> ids = this.search(EntityType.attraction, longitude, latitude, radius);
		List<IAttractions> result = Lists.newLinkedList();
		for (long id : ids) {
			result.add(this.manager.get(id));
		}
		return result;
	}

}
