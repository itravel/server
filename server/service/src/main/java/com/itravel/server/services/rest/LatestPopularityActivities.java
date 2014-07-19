package com.itravel.server.services.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.filters.ActivitiesDBFilter;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
import com.itravel.server.services.aos.LatestActivitiesAOS;

@Path("/latestPopularityActivities")
public class LatestPopularityActivities {
	private static IDataRepository<ActivitiesEntity> dataRepo = new UpcomingEventsDBRepository();
	private static ObjectMapper om = new ObjectMapper();
	@GET
	@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
	public Response f(@QueryParam (value = "days") @DefaultValue("14") int days,
			@QueryParam (value = "pages") @DefaultValue("0") int page,
			@QueryParam (value = "popularity") @DefaultValue("0") int popularity) throws JsonProcessingException{
		//System.out.println(days);
		System.out.println(page);
		Date currentTime = new Date();
		Date timeBegin = currentTime;
		Date timeEnd = currentTime;
		timeBegin = DateUtils.addDays(timeBegin, 0-days);
		timeEnd = DateUtils.addDays(timeEnd, days);
		IFilter<ActivitiesEntity> filter = ActivitiesDBFilter.createTimeFilterPopularity(timeBegin, timeEnd, page,popularity);
		List<ActivitiesEntity> entities = dataRepo.filterBy(filter);
		List<LatestActivitiesAOS> aosEntities  = getAos(entities);
		//System.out.println(aosEntities.size());
		return Response.ok().entity(om.writeValueAsString(aosEntities)).build();
	}

	private List<LatestActivitiesAOS> getAos(List<ActivitiesEntity> entities){
		List<LatestActivitiesAOS> aosEntities = new ArrayList<LatestActivitiesAOS>();
		for(int i=0;i<entities.size();i++){
			ActivitiesEntity tmpEntity = entities.get(i);
			LatestActivitiesAOS latestActivityAos = new LatestActivitiesAOS(tmpEntity);
			aosEntities.add(latestActivityAos);
		}
		return aosEntities;
	}
}
