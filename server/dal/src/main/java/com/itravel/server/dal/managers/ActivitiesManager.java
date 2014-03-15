package com.itravel.server.dal.managers;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.ActivitiesUserEntity;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;

public class ActivitiesManager extends AbstractManager implements
		IActivitiesManager {

	@Override
	public IActivities newActivities() {
		// TODO Auto-generated method stub
		return new ActivityEntity();
	}

	@Override
	public boolean add(IActivities activities) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(activities);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public boolean remove(IActivities activities) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.remove(activities);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public IActivities get(long id) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		IActivities entity = manager.find(ActivityEntity.class, id);
		return entity;
	}

	@Override
	public List<IActivities> getAvailableActivities(int start, int count) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		System.out.println("-----------");
		List<IActivities> entities = manager.createNamedQuery("ActivityEntity.findAvailable").setParameter("endTime", new Date()).getResultList();
		if( entities == null ) {
			entities = Lists.newArrayList();
		}
		return entities;
	}

	
	public static void main(String[] args) {
		ActivitiesManager manager = new ActivitiesManager();
		System.out.println(manager.get(2).getUsers().size());
	}


}
