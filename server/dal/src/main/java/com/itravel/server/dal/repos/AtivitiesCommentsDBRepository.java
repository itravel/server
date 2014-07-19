package com.itravel.server.dal.repos;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.itravel.server.dal.entities.ActivitiesCommentsEntity;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;

public class AtivitiesCommentsDBRepository   implements IDataRepository<ActivitiesCommentsEntity>{
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

	@Override
	public List<ActivitiesCommentsEntity> filterBy(IFilter<ActivitiesCommentsEntity> filter) {
		return filter.doFilter(this);
	}

	@Override
	public void persist(ActivitiesCommentsEntity entity) {
		// TODO Auto-generated method stub
		
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		if(entity.getId()>0){
			manager.find(ActivitiesEntity.class, entity.getId());
		}
		manager.merge(entity);
		manager.getTransaction().commit();
	}

	@Override
	public void persistAll(List<ActivitiesCommentsEntity> entities) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		for(ActivitiesCommentsEntity entity:entities){
			
			manager.persist(entity);
		}
		manager.getTransaction().commit();
	}
	
	


}
