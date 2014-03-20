package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.AttractionEntity;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public final class AttractionsManager extends AbstractManager implements IAttractionsManager {

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

}
