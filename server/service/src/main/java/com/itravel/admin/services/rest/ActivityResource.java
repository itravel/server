package com.itravel.admin.services.rest;

import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Optional;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.services.aos.ActivityBean;
import com.itravel.server.services.json.serializers.ActivityDesrializer;
import com.itravel.server.services.json.serializers.ActivityJourneySimpleSerializer;
import com.itravel.server.services.json.serializers.ActivitySimpleSerializer;
import com.itravel.server.services.rest.utils.JsonFactory;

@Path("activities")
public class ActivityResource {
	private static ObjectMapper mapper = new ObjectMapper();
	protected static final ObjectMapper listObjectMapper = new ObjectMapper();
	static {
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ActivityJourneySimpleSerializer()).addDeserializer(ActivityEntity.class, new ActivityDesrializer());
		SimpleModule listModule = new SimpleModule();
		listModule.addSerializer(new ActivitySimpleSerializer());
		
		mapper.registerModule(module).setDateFormat(new SimpleDateFormat("yyyy-MM-dd")).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		listObjectMapper.registerModule(listModule).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
	private static Logger logger = LogManager.getLogger(ActivityResource.class);
	private static ActivityManager aManager = new ActivityManager();
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id,String jsonStr){
		try {
			ActivityEntity entity = mapper.readValue(jsonStr,ActivityEntity.class);
			logger.debug(entity);
			entity = aManager.save(entity);
			String activityJsonStr="";
			try {
				activityJsonStr = mapper.writeValueAsString(entity);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return Response.ok().entity(activityJsonStr).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
			
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response create(ActivityBean paramBean){
		try {
			ActivityEntity entity = Optional.of(paramBean).transform(ActivityBean.TO_ENTITY).get();
			this.aManager.save(entity);
			entity = this.aManager.getActivity(entity.getId());
			ActivityBean bean = new ActivityBean(entity);
			String activityJsonStr="";
			try {
				activityJsonStr = JsonFactory.getMapper().writeValueAsString(bean);
				
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Response.ok().entity(activityJsonStr).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
			
		}
	}
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam(value = "id") long id){
		try {
			
			ActivityEntity entity = aManager.remove(id);
			return Response.ok().entity(entity).build();
		}
		catch(Exception e){
			return Response.serverError().entity(e.getMessage()).build();
			
		}
		
	}
}
