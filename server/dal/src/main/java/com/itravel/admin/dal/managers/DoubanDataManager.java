package com.itravel.admin.dal.managers;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.google.common.base.Joiner;
import com.itravel.admin.dal.entities.DoubanActivityEntity;

public class DoubanDataManager {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	
	public List<DoubanActivityEntity> getFilteredPageData(int offset,int limit,Set<Long> filteredIds){
		EntityManager manager = emf.createEntityManager();
		List<DoubanActivityEntity> activities = manager.createNativeQuery("select * from douban_activity where edit_status = 0 and id not in (?editings) order by id", DoubanActivityEntity.class).setParameter("editings", Joiner.on(",").join(filteredIds)).setFirstResult(offset).setMaxResults(limit).getResultList();
		manager.close();
		return activities;
		
	}

	public DoubanActivityEntity get(long id) {
		EntityManager manager = emf.createEntityManager();
		DoubanActivityEntity entity = manager.find(DoubanActivityEntity.class, id);
		manager.close();
		return entity;
	}

	public void save(DoubanActivityEntity douban){
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		if(douban.getId()>0){
			manager.find(DoubanActivityEntity.class, douban.getId());
			manager.merge(douban);
			
		}
		else {
			manager.persist(douban);
		}
		manager.getTransaction().commit();
		manager.close();
	}

}
