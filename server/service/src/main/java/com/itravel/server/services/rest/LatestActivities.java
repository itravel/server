package com.itravel.server.services.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

@Path("/latestActivities")
public class LatestActivities {
	protected ActivityManager activityManager = new ActivityManager();
	protected ObjectMapper objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static Logger logger = LogManager.getLogger(LatestActivities.class);
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@QueryParam(value = "days") int days,@QueryParam(value = "start") int start,@QueryParam(value="number") int number){
		Date now = new Date();
		Date from = DateUtils.addDays(now, 0-days);
		Date to = DateUtils.addDays(now, days);
		List<ActivityEntity> activites = activityManager.getLatestActivities(from,to,start, number);
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
