package com.itravel.server.services.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.services.rest.params.ActivitiesFormParam;

@Singleton
@Path("/activities")
public class Activities {
	@Context
	UriInfo uriInfo;
	protected ActivityManager activityManager = new ActivityManager();
	protected ObjectMapper objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static Logger logger = LogManager.getLogger(Activities.class);
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@QueryParam(value = "start") int start,@QueryParam(value="number") int number){
		List<ActivityEntity> activites = activityManager.getActivities(start, number);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activites);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@PathParam("id") long id){
		ActivityEntity activity = this.activityManager.getActivity(id);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activity);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createActivity(@BeanParam ActivitiesFormParam activityForm){
		Optional<ActivitiesFormParam> formData = Optional.fromNullable(activityForm);
		ActivityEntity entity = formData.transform(new Function<ActivitiesFormParam,ActivityEntity>(){
			@Override
			public ActivityEntity apply(ActivitiesFormParam input) {
				ActivityEntity entity = new ActivityEntity();
				try {
					String json = objectMapper.writeValueAsString(input);
					entity = objectMapper.readValue(json,ActivityEntity.class);
				} catch (IOException e) {
					logger.error(e);
				}
				return entity;
			}}).get();
		this.activityManager.save(entity);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(entity.getId())).build()).entity(activityJsonStr).build();
		
	}
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateActivity(@PathParam("id") long id,@BeanParam ActivitiesFormParam activityForm){
		Optional<ActivitiesFormParam> formData = Optional.fromNullable(activityForm);
		ActivityEntity entity = formData.transform(new Function<ActivitiesFormParam,ActivityEntity>(){
			@Override
			public ActivityEntity apply(ActivitiesFormParam input) {
				ActivityEntity entity = new ActivityEntity();
				try {
					String json = objectMapper.writeValueAsString(input);
					entity = objectMapper.readValue(json,ActivityEntity.class);
				} catch (IOException e) {
					logger.error(e);
				}
				return entity;
			}}).get();
		entity.setId(id);
		this.activityManager.save(entity);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(entity.getId())).build()).entity(activityJsonStr).build();
	}
}
