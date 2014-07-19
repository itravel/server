package com.itravel.server.dal.filters;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.itravel.server.dal.common.LimitParameter;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
public abstract class ActivitiesDBFilter implements IFilter<ActivitiesEntity>{
	protected Logger logger = LogManager.getLogger(this.getClass());


	public static ActivitiesDBFilter createIDFilter(long... ids){
		return new UpcomingEventDBFilterByIDs(ids);
	}
	public static ActivitiesDBFilter createCityFilter(int city){
		return new UpcomingEventDBFilterByCity(city);
	}
	
	public static ActivitiesDBFilter createTimeFilter(Date timeBegin,Date timeEnd,int page){
		return new ActivitiesDBFilterByTime(timeBegin,timeEnd,page);
	}
	
	public static ActivitiesDBFilter createTimeFilterPopularity(Date timeBegin,Date timeEnd,int page,int sortNum){
		String sortPra = "asc";
		if(sortNum==0){
			sortPra = "asc";
		}else{
			sortPra = "desc";
		}
		return new ActivitiesDBFilterByPopularity(timeBegin,timeEnd,page,sortPra);
	}
	
	public static ActivitiesDBFilter createTimeFilterUserInterest(Date timeBegin,Date timeEnd,int page,long userId){
		return new createTimeFilterUserInterest(timeBegin,timeEnd,page,userId);
	}
	
	public static ActivitiesDBFilter createTimeFilterByCityName(Date timeBegin,Date timeEnd,int page,String cityName){
		return new createTimeFilterByCityName(timeBegin,timeEnd,page,cityName);
	}
	/**
	 * 
	 * @author william.wangwm
	 *
	 */
	public static class UpcomingEventDBFilterByIDs extends  ActivitiesDBFilter {
		private final List<Long> idList=Lists.newArrayList();
		private static final String QUERY_BY_IDS = "select A from ActivitiesEntity A where A.id in :ids order by A.id";
		public UpcomingEventDBFilterByIDs(long... ids){
			for(long id:ids){
				this.idList.add(id);
			}
		}
		public List<ActivitiesEntity> doFilter(UpcomingEventsDBRepository reppo){
			return reppo.getEntityManager().createNamedQuery("",ActivitiesEntity.class).getResultList();
		}
		@Override
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo) {
			// TODO Auto-generated method stub
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			return reppo.getEntityManager().createQuery(QUERY_BY_IDS,ActivitiesEntity.class).setParameter("ids", this.idList).getResultList();
		}
	}
	
	public static class ActivitiesDBFilterByTime extends  ActivitiesDBFilter{
		private static final String QUERY_ALL = "select A from ActivitiesEntity A where A.startTime<:timeEnd and  A.endTime>:timeBegin order"
				+ " by A.popularity";
		private Date timeBegin = null;
		private Date timeEnd = null;
		private int page = 0;
		public ActivitiesDBFilterByTime(Date timeBegin,Date timeEnd,int page){
			this.timeBegin = timeBegin;
			this.timeEnd = timeEnd;
			this.page = page;
		}

		@Override
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo) {
			// TODO Auto-generated method stub
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			LimitParameter limit = new LimitParameter(page);
			int limitPara1 = limit.getLimitFirstPar();
			int limitPara2 = limit.getLimitSecondPar();
			System.out.println(limitPara1);
			System.out.println(limitPara2);
			EntityManager em = reppo.getEntityManager();
			List<ActivitiesEntity> result = em.createQuery(QUERY_ALL,ActivitiesEntity.class).setParameter("timeBegin", this.timeBegin)
					.setParameter("timeEnd",this.timeEnd).setMaxResults(limitPara2).setFirstResult(limitPara1).getResultList();
			em.close();
			return result;
		}	
	}
	
	public static class createTimeFilterUserInterest extends  ActivitiesDBFilter{
		private static final String QUERY_ALL = "select A from ActivitiesEntity A where A.startTime<:timeEnd and  A.endTime>:timeBegin order"
				+ " by A.popularity";
		private Date timeBegin = null;
		private Date timeEnd = null;
		private int page = 0;
		private long userId = 0;
		public createTimeFilterUserInterest(Date timeBegin,Date timeEnd,int page,long userId){
			this.timeBegin = timeBegin;
			this.timeEnd = timeEnd;
			this.page = page;
			this.userId = userId;
		}

		@Override
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo) {
			// TODO Auto-generated method stub
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			LimitParameter limit = new LimitParameter(page);
			int limitPara1 = limit.getLimitFirstPar();
			int limitPara2 = limit.getLimitSecondPar();
			//System.out.println(limitPara1);
			//System.out.println(limitPara2);
			EntityManager em = reppo.getEntityManager();
			List<ActivitiesEntity> result = em.createQuery(QUERY_ALL,ActivitiesEntity.class).setParameter("timeBegin", this.timeBegin)
					.setParameter("timeEnd",this.timeEnd).setMaxResults(limitPara2).setFirstResult(limitPara1).getResultList();
			em.close();
			return result;
		}	
	}
	
	public static class createTimeFilterByCityName extends  ActivitiesDBFilter{
		private static final String QUERY_ALL = "select A from ActivitiesEntity A where A.startTime<:timeEnd and  A.endTime>:timeBegin and A.cityName=:cityName";
		private Date timeBegin = null;
		private Date timeEnd = null;
		private int page = 0;
		private String cityName = null;
		public createTimeFilterByCityName(Date timeBegin,Date timeEnd,int page,String cityName){
			this.timeBegin = timeBegin;
			this.timeEnd = timeEnd;
			this.page = page;
			this.cityName = cityName;
		}

		@Override
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo) {
			// TODO Auto-generated method stub
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			LimitParameter limit = new LimitParameter(page);
			int limitPara1 = limit.getLimitFirstPar();
			int limitPara2 = limit.getLimitSecondPar();
			EntityManager em = reppo.getEntityManager();
			List<ActivitiesEntity> result = em.createQuery(QUERY_ALL,ActivitiesEntity.class).setParameter("timeBegin", this.timeBegin)
					.setParameter("timeEnd",this.timeEnd).setParameter("cityName",cityName).setMaxResults(limitPara2).setFirstResult(limitPara1)
					.getResultList();
			em.close();
			return result;
		}	
	}
	
	public static class ActivitiesDBFilterByPopularity extends  ActivitiesDBFilter{
		private static final String QUERY_ALL = "select A from ActivitiesEntity A where A.startTime<:timeEnd and  A.endTime>:timeBegin order"
				+ " by A.popularity %s";
		//private static final String QUERY_ALL = "select A from ActivitiesEntity A where startTime<:timeEnd and  endTime>:timeBegin";
		private Date timeBegin = null;
		private Date timeEnd = null;
		private int page = 0;
		private String sortPar = null;
		public ActivitiesDBFilterByPopularity(Date timeBegin,Date timeEnd,int page,String sortPra){
			this.timeBegin = timeBegin;
			this.timeEnd = timeEnd;
			this.page = page;
			this.sortPar = sortPra ;
		}

		@Override
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo) {
			// TODO Auto-generated method stub
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			LimitParameter limit = new LimitParameter(page);
			int limitPara1 = limit.getLimitFirstPar();
			int limitPara2 = limit.getLimitSecondPar();
			System.out.println(limitPara1);
			System.out.println(limitPara2);
			EntityManager em = reppo.getEntityManager();
			List<ActivitiesEntity> result = em.createQuery(String.format(QUERY_ALL,this.sortPar),ActivitiesEntity.class).setParameter("timeBegin", this.timeBegin).
					setParameter("timeEnd",this.timeEnd).setMaxResults(limitPara2).setFirstResult(limitPara1).getResultList();
			em.close();
			return result;
		}	
	}
	
	/**
	 * 
	 * @author william.wangwm
	 *
	 */
	public static class UpcomingEventDBFilterByCity extends  ActivitiesDBFilter {
		private static final String QUERY_BY_CITY = "select A from ActivitiesEntity A order by ABS(A.startTime-current_date()) ";
		int city = 0;
		public UpcomingEventDBFilterByCity(int city){
			this.city = city;
		}
		
		public List<ActivitiesEntity> doFilter(
				IDataRepository<ActivitiesEntity> repo){
			UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
			return reppo.getEntityManager().createQuery(QUERY_BY_CITY,ActivitiesEntity.class).getResultList();
		}
		
		
		
	}

	public static IFilter<ActivitiesEntity> createNoneFilter() {
		// TODO Auto-generated method stub
		return new IFilter<ActivitiesEntity>(){
			private static final String QUERY_ALL = "select * from activities order by abs(start_time - now())";
			@Override
			public List<ActivitiesEntity> doFilter(
					IDataRepository<ActivitiesEntity> repo) {
				UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
				return reppo.getEntityManager().createNativeQuery(QUERY_ALL,ActivitiesEntity.class).getResultList();
			}
			
		};
	}
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
