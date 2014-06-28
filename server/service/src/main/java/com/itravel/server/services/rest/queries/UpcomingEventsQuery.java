package com.itravel.server.services.rest.queries;

import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.filters.ActivitiesDBFilter;
import com.itravel.server.dal.filters.ActivitiesDBFilter.UpcomingEventDBFilterByCity;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.services.interfaces.QueryFilterGenerator;

public class UpcomingEventsQuery {
		@QueryParam("id")
		public long id;
		@QueryParam("start")
		public int start;
		@QueryParam("count")
		public int count;
		
		@QueryParam("cityCode")
		public int cityCode; 
		
		
		
		public UpcomingEventsQuery(){
			
		}
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
			return ToStringBuilder.reflectionToString(this);
		}
		public IFilter<ActivitiesEntity> createFilter() {
			if(id > 0){
				return ActivitiesDBFilter.createIDFilter(id);
			}
			else if(cityCode > 0) {
				return ActivitiesDBFilter.createCityFilter(cityCode);
			}
			else {
				return ActivitiesDBFilter.createNoneFilter();
			}
		}
		
}
