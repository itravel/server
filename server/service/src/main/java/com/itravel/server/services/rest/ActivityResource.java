package com.itravel.server.services.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityImageEntity;
import com.itravel.server.dal.entities.TagEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.dal.managers.TagManager;
import com.itravel.server.services.json.serializers.ActivityJourneySimpleSerializer;
import com.itravel.server.services.json.serializers.ActivitySimpleSerializer;
import com.itravel.server.services.json.serializers.ActivityTagSimpleSerializer;
import com.itravel.server.services.rest.params.ActivitiesFormParam;
import com.itravel.server.services.rest.params.ActivitiesFormParam.ValidationEnum;

@Path("/activities")
public class ActivityResource {
	@Context
	UriInfo uriInfo;
	protected static final ActivityManager activityManager = new ActivityManager();
	protected static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule().addSerializer(new ActivityJourneySimpleSerializer()).addSerializer(new ActivityTagSimpleSerializer())).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	protected static final ObjectMapper listObjectMapper = new ObjectMapper().registerModule(new SimpleModule().addSerializer(new ActivitySimpleSerializer())).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static final Logger logger = LogManager.getLogger(ActivityResource.class);
	private static final LoadingCache<Long,TagEntity> tagCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<Long,TagEntity>(){
		private final TagManager tagManager = new TagManager();
		
		@Override
		public TagEntity load(Long id) throws Exception {
			return tagManager.get(id);
		}});
	
	/**
	 * 获取活动信息
	 * @param start
	 * @param number
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@QueryParam(value = "start") int start,@QueryParam(value="number") int number){
		List<ActivityEntity> activites = activityManager.getActivities(start, number);
		String activityJsonStr="";
		try {
			activityJsonStr = listObjectMapper.writeValueAsString(activites);
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
		System.out.println(activity);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activity);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	

	
	
}
