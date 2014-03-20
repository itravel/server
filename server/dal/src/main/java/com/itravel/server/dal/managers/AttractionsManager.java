package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.AttractionEntity;
import com.itravel.server.dal.spatial.AttrationsSpatialManager;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public final class AttractionsManager extends AbstractManager implements IAttractionsManager {

	AttrationsSpatialManager spatialManager = new AttrationsSpatialManager();
	public AttractionsManager() {
		// TODO Auto-generated constructor stub
		System.out.println("------------");
		this.spatialManager.initIndex(this.getAll());
	}
	@Override
	public IAttractions create(){
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
		return null;
	}
	@Override
	public boolean batchSave(List<IAttractions> attractions) {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		for(IAttractions entity:attractions){
			
			manager.persist(entity);
		}
		manager.getTransaction().commit();
		manager.close();
		return true;
	}
	
	private List<IAttractions> getAll() {
		// TODO Auto-generated method stub
		EntityManager manager = this.emf.createEntityManager();
		List<IAttractions> atts = manager.createNamedQuery("AttractionEntity.findAll").getResultList();
		return atts;
	}
	@Override
	public List<IAttractions> getByLngLat(int start, int count,
			double longitude, double latitude) {
		// TODO Auto-generated method stub
		List<Long> ids = this.spatialManager.search(longitude,latitude,50);
		List<IAttractions> result = Lists.newLinkedList();
		for(long id:ids){
			result.add(this.get(id));
		}
		return result;
	}

}
