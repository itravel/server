package com.itravel.server.dal.managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.ActivityEntity;

public class ActivityManager extends AbstractManager{
	public List<ActivityEntity> getActivities(int start,int number,boolean reverse){
		EntityManager manager = this.emf.createEntityManager();
		List<ActivityEntity> result;
		if(reverse){
			result = manager.createQuery("select AE from ActivityEntity AE order by AE.id desc",ActivityEntity.class).setFirstResult(start).setMaxResults(number).getResultList();
		}
		else {
			result = manager.createQuery("select AE from ActivityEntity AE order by AE.id",ActivityEntity.class).setFirstResult(start).setMaxResults(number).getResultList();
		}
		manager.close();
		return result;
	}

	public ActivityEntity save(ActivityEntity entity) {
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(entity.getId() > 0){
			ActivityEntity _entity = manager.find(ActivityEntity.class, entity.getId());
			if(_entity!=null){
				
				manager.merge(entity);
			}
			else {
				manager.persist(entity);
			}
		}
		else {
			manager.persist(entity);
		}
		manager.getTransaction().commit();
		manager.close();
		return entity;
	}

	public ActivityEntity getActivity(long id) {
		EntityManager manager = this.emf.createEntityManager();
		ActivityEntity entity = manager.find(ActivityEntity.class, id);
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
	
	public ActivityEntity remove(long id){
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		ActivityEntity entity = manager.find(ActivityEntity.class, id);
		manager.remove(entity);
		manager.getTransaction().commit();
		return entity;
	}
	
	public static void main(String[] args) {
		ActivityManager manager = new ActivityManager();
		ActivityEntity entity = manager.getActivity(1L);
//		TagManager tManager = new TagManager();
//		entity.getTags().add(tManager.get(3L));
//		manager.save(entity);
		System.out.println(entity);
	}
}
