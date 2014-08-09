package com.itravel.server.dal.managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.client.dos.IActivityObject;
import com.itravel.server.dal.entities.ActivityEntity;

public class ActivityManager extends AbstractManager{
	public List<ActivityEntity> getActivities(int start,int number){
		EntityManager manager = this.emf.createEntityManager();
		List<ActivityEntity> result = manager.createQuery("select AE from ActivityEntity AE order by AE.id",ActivityEntity.class).setFirstResult(start).setMaxResults(number).getResultList();
		manager.close();
		return result;
	}

	public IActivityObject save(ActivityEntity entity) {
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(entity.getId() > 0){
			manager.find(ActivityEntity.class, entity.getId());
			manager.merge(entity);
		}
		else {
			manager.persist(entity);
		}
		manager.getTransaction().commit();
		manager.close();
		return entity;
	}

	public IActivityObject getActivity(long id) {
		EntityManager manager = this.emf.createEntityManager();
		IActivityObject entity = manager.find(ActivityEntity.class, id);
		manager.close();
		return entity;
	}

	public List<ActivityEntity> getLatestActivities(Date from, Date to,
			int start, int number) {
		EntityManager manager = this.emf.createEntityManager();
		List<ActivityEntity> result = manager.createQuery("select AE from ActivityEntity AE where AE.startTime <:toTime and AE.endTime >= :fromTime  order by AE.id ",ActivityEntity.class).setFirstResult(start).setMaxResults(number).setParameter("toTime", to).setParameter("fromTime", from).getResultList();
		manager.close();
		return result;
	}
	
}
