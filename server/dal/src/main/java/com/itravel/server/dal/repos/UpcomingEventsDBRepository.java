package com.itravel.server.dal.repos;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;

public class UpcomingEventsDBRepository   implements IDataRepository<ActivitiesEntity>{
	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
	
	
	public EntityManager getEntityManager(){
		return emf.createEntityManager();
	}

	@Override
	public List<ActivitiesEntity> filterBy(IFilter<ActivitiesEntity> filter) {
		return filter.doFilter(this);
	}


}
