package com.itravel.server.dal.managers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.itravel.server.dal.asyn.FetchAttractionTask;
import com.itravel.server.dal.entities.AttractionEntity;
import com.itravel.server.dal.spatial.AttrationsSpatialManager;
import com.itravel.server.dal.spatial.PoiType;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public final class AttractionsManager extends AbstractManager implements
		IAttractionsManager {

	private final AttrationsSpatialManager spatialManager = AttrationsSpatialManager
			.getInstance();
	private static final int BATCHNUMBER = 20;

	public AttractionsManager() {
		// TODO Auto-generated constructor stub
		int offset = 0;
		final int count = 1500;
		boolean hasNext = true;
		final AtomicInteger a = new AtomicInteger();
		final CountDownLatch cdl = new CountDownLatch(BATCHNUMBER);
		final AtomicInteger size = new AtomicInteger();
		final List<IAttractions> batch = Collections
				.synchronizedList(new LinkedList<IAttractions>());
		for (int i = 0; i < BATCHNUMBER; i++) {
			offset = offset + i * count;

			ListenableFuture<List<IAttractions>> future = service
					.submit(new FetchAttractionTask(offset, count, this));
			Futures.addCallback(future,
					new FutureCallback<List<IAttractions>>() {

						@Override
						public void onSuccess(List<IAttractions> attractions) {
							// TODO Auto-generated method stub
							batch.addAll(attractions);
							a.addAndGet(attractions.size());
							size.addAndGet(attractions.size());
							cdl.countDown();
						}

						@Override
						public void onFailure(Throwable t) { // TODO
							// Auto-generated method stub
							cdl.countDown();
						}
					});

		}
		try {
			cdl.await();
			spatialManager.addIndex(batch);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public IAttractions create() {
		return new AttractionEntity();
	}

	@Override
	public boolean save(IAttractions entity) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(entity);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public boolean remove(IAttractions entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IAttractions get(long id) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		IAttractions attractions = manager.find(AttractionEntity.class, id);
		manager.close();
		return attractions;
	}

	@Override
	public boolean batchSave(List<IAttractions> attractions) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		for (IAttractions entity : attractions) {

			manager.persist(entity);
		}
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public List<IAttractions> getByLngLat(int start, int count,
			double longitude, double latitude) {
		// TODO Auto-generated method stub
		List<Long> ids = this.spatialManager.search(PoiType.attraction,
				longitude, latitude, 50);
		List<IAttractions> result = Lists.newLinkedList();
		for (long id : ids) {
			result.add(this.get(id));
		}

		return result;
	}

	@Override
	public IAttractions create(String json) {
		// TODO Auto-generated method stub
		try {
			IAttractions attraction = mapper.readValue(json,
					AttractionEntity.class);
			return attraction;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IAttractions> getRange(int offset, int count) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		List<IAttractions> attractions = manager
				.createNativeQuery(
						String.format(
								"select * from attractions order by id asc limit %d,%d",
								offset, count), AttractionEntity.class)
				.getResultList();
		manager.close();
		return attractions;
	}

	public static final IAttractionsManager getInstance(){
		return AttractionsManagerHolder.INSTANCE;
	}
	
	private static final class AttractionsManagerHolder {
		private static final IAttractionsManager INSTANCE = new AttractionsManager();
	}
	public static void main(String[] args) {
		final AttractionsManager managr = new AttractionsManager();

	}
}
