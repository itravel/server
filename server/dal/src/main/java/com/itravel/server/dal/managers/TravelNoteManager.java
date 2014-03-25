package com.itravel.server.dal.managers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		try {
			Date from  = DateUtils.parseDate("2014-01-01 12:00:00", "yyyy-MM-dd hh:mm:ss");
			Date to  = DateUtils.parseDate("2014-02-01 12:00:00", "yyyy-MM-dd hh:mm:ss");
			TravelNoteManager m = new TravelNoteManager();
			System.out.println(m.getRange(0, 10));
//			logger.info(m.getByTimeRange(from, to));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	@Override
	public List<ITravelNote> getByTimeRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		List<ITravelNote> travelNotes = manager.createNamedQuery("TravelNoteEntity.findByTimeRange").setParameter("from", fromDate).setParameter("to", toDate).getResultList();
		manager.close();
		return travelNotes;
	}

	@Override
	public List<ITravelNote> getRange(int offset, int count) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		List<ITravelNote> travelNotes = manager.createNativeQuery(String.format("select * from travel_notes order by id limit %d,%d", offset,count),TravelNoteEntity.class).getResultList();
		manager.close();
		return travelNotes;
	}
	
	
	public static final ITravelNoteManager getInstance(){
		return TravelNoteManagerHolder.INSTANCE;
	}
	private static final class TravelNoteManagerHolder {
		private static final ITravelNoteManager INSTANCE = new TravelNoteManager();
	}
	
	

}
