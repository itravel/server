package com.itravel.server.services.rest;

import java.text.SimpleDateFormat;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.managers.ActivityManager;

@Path("/latestPopularityActivities")
public class LatestPopularityActivities {
	protected ActivityManager activityManager = new ActivityManager();
	protected ObjectMapper objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static Logger logger = LogManager.getLogger(LatestPopularityActivities.class);
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
		List<ActivityEntity> activites = activityManager.getLatestActivities(timeBegin,timeEnd
				,page, 15);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activites);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}

}

