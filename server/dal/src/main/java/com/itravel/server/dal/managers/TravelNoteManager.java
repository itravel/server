package com.itravel.server.dal.managers;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.TravelNoteEntity;
import com.itravel.server.interfaces.dal.ITravelNote;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;

public class TravelNoteManager extends AbstractManager implements ITravelNoteManager {
	
	@Override
	public boolean save(ITravelNote entity) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(entity);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public boolean remove(ITravelNote entity) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(entity);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public ITravelNote get(long id) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		return manager.find(TravelNoteEntity.class, id);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public ITravelNote create() {
		// TODO Auto-generated method stub
		return new TravelNoteEntity();
	}

}
