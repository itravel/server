package com.itravel.server.dal.filters;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.itravel.server.dal.common.LimitParameter;
import com.itravel.server.dal.entities.ActivitiesCommentsEntity;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.repos.AtivitiesCommentsDBRepository;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
public abstract class ActivitiesCommentsDBFilter implements IFilter<ActivitiesCommentsEntity>{
	protected Logger logger = LogManager.getLogger(this.getClass());

	public static ActivitiesCommentsDBFilter createCommentsFilterByActivitiesId(long activitiesId){
		return new ActivitiesCommentsFilterByActivitiesId(activitiesId);
	}
	
	/**
	 * 
	 * @author lixiaoyun
	 *
	 */
	public static class ActivitiesCommentsFilterByActivitiesId extends  ActivitiesCommentsDBFilter{
		private static final String QUERY_ALL = "select A from ActivitiesComments A where activities_id:activitiesId order by ID";;
		private int page = 0;
		private long activitiesId = 0;
		public ActivitiesCommentsFilterByActivitiesId(long activitiesId){
			this.activitiesId = activitiesId;
		}

		@Override
		public List<ActivitiesCommentsEntity> doFilter(
				IDataRepository<ActivitiesCommentsEntity> repo) {
			// TODO Auto-generated method stub
			AtivitiesCommentsDBRepository reppo = (AtivitiesCommentsDBRepository) repo;
			LimitParameter limit = new LimitParameter(page);
			int limitPara1 = limit.getLimitFirstPar();
			int limitPara2 = limit.getLimitSecondPar();
			//System.out.println(limitPara1);
			//System.out.println(limitPara2);
			EntityManager em = reppo.getEntityManager();
			List<ActivitiesCommentsEntity> result = em.createQuery(QUERY_ALL,ActivitiesCommentsEntity.class).setMaxResults(limitPara2).setFirstResult(limitPara1)
					.setParameter("activitiesId", this.activitiesId).getResultList();
			em.close();
			return result;
		}	
	}
	/**
	 * 
	 * @author william.wangwm
	 *
	 */
	public static IFilter<ActivitiesEntity> createMonthFilter(int month, int cityCode) {
		// TODO Auto-generated method stub
		return new IFilter<ActivitiesEntity>() {

			@Override
			public List<ActivitiesEntity> doFilter(
					IDataRepository<ActivitiesEntity> upcomingEventsRepository) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		};
	}


	
	
}
