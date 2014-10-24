package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.AreaEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.dal.managers.AreaManager;
import com.itravel.server.services.aos.ActivityBean;
import com.itravel.server.services.aos.ActivityListViewBean;
import com.itravel.server.services.aos.AreaType;
import com.itravel.server.services.rest.utils.JsonFactory;

@Path("/activities")
public class ActivityResource {
	@Context
	UriInfo uriInfo;
	protected static final ActivityManager activityManager = new ActivityManager();
	private static final AreaManager areaManager = new AreaManager();
	private static final Logger logger = LogManager.getLogger(ActivityResource.class);
	
	
	/**
	 * 获取活动信息
	 * @param start
	 * @param number
	 * @return
	 */
//	@GET
//	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
//	public Response getActivities(@QueryParam(value = "start") int start,@QueryParam(value="number") int number){
//		List<ActivityEntity> _activites = activityManager.getActivities(start, number,true);
//		List<ActivityListViewBean> activites = FluentIterable.from(_activites).transform(ActivityListViewBean.FROM_ENTITY).toList();
//		String activityJsonStr="";
//		try {
//			activityJsonStr = JsonFactory.getMapper().writeValueAsString(activites);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			logger.error(e);
//		}
//		return Response.ok().entity(activityJsonStr).build();
//	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivities(@PathParam("id") long id){
		ActivityEntity activity = this.activityManager.getActivity(id);
		if(activity==null){
			return Response.status(Status.NOT_FOUND).build();
		}
		String activityJsonStr="";
		try {
			
			activityJsonStr = JsonFactory.getMapper().writeValueAsString(Optional.of(activity).transform(ActivityBean.FROM_ENTITY).get());
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	
//	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getActivitiesByCity(@QueryParam("city") String cityName){
		AreaEntity a = areaManager.getAreaByNameAndType(cityName, AreaType.city.intValue());
		List<ActivityEntity> _activites = this.activityManager.getActivitiesByCity(a.getId());
		List<ActivityListViewBean> activites = FluentIterable.from(_activites).transform(ActivityListViewBean.FROM_ENTITY).toList();
		String activityJsonStr="";
		try {
			activityJsonStr = JsonFactory.getMapper().writeValueAsString(activites);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}
	

	
	
}
