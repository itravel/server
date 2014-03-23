package com.itravel.server.dal.managers;

import java.io.IOException;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
		ITravelNote travelNote = manager.find(TravelNoteEntity.class, id);
		manager.close();
		return travelNote;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public ITravelNote create() {
		// TODO Auto-generated method stub
		return new TravelNoteEntity();
	}

	@Override
	public ITravelNote create(String json) {
		// TODO Auto-generated method stub
		try {
			ITravelNote travelNote = mapper.readValue(json, TravelNoteEntity.class);
			return travelNote;
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

}
