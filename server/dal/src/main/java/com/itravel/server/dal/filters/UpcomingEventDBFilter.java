package com.itravel.server.dal.filters;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;







import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
public abstract class UpcomingEventDBFilter implements IFilter<ActivitiesEntity>{
	protected Logger logger = LogManager.getLogger(this.getClass());


	public static UpcomingEventDBFilter createIDFilter(long... ids){
		return new UpcomingEventDBFilterByIDs(ids);
	}
	public static UpcomingEventDBFilter createCityFilter(int city){
		return new UpcomingEventDBFilterByCity(city);
	}
	
	/**
	 * 
	 * @author william.wangwm
	 *
	 */
	public static class UpcomingEventDBFilterByIDs extends  UpcomingEventDBFilter {
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
	
	/**
	 * 
	 * @author william.wangwm
	 *
	 */
	public static class UpcomingEventDBFilterByCity extends  UpcomingEventDBFilter {
		private static final String QUERY_BY_CITY = "select A from ActivitiesEntity A order by A.id ";
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
			private static final String QUERY_ALL = "select A from ActivitiesEntity A";
			@Override
			public List<ActivitiesEntity> doFilter(
					IDataRepository<ActivitiesEntity> repo) {
				UpcomingEventsDBRepository reppo = (UpcomingEventsDBRepository) repo;
				return reppo.getEntityManager().createQuery(QUERY_ALL,ActivitiesEntity.class).getResultList();
			}
			
		};
	}

	
	
}
