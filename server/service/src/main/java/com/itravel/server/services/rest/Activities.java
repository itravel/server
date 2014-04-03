package com.itravel.server.services.rest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.ServiceLoader;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.itravel.server.dal.managers.ActivitiesManager;
import com.itravel.server.dal.spatial.ActivitiesSpatialManager;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.interfaces.dal.managers.ISpatialSearchManager;
import com.itravel.server.interfaces.dal.managers.IUserManager;
import com.itravel.server.services.aos.Constants;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;
import com.itravel.server.services.utils.ManagerFactory;

@Path("/")
public class Activities {
	private final IActivitiesManager manager = ManagerFactory.getActivitiesManager();
	private final IUserManager userManager = ManagerFactory.getUserManager();
	private final ISpatialSearchManager<IActivities> sManager = ActivitiesSpatialManager.getInstance();
	private Logger logger = LogManager.getLogger(Constants.LOGGER);
	@Context
	UriInfo uriInfo;
	public Activities() {
	}
	
	/**
	 * 根据活动id获取活动信息
	 * @param activitiesId
	 * @return
	 */
	@Path("activities/{activitiesId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivities(@PathParam("activitiesId") long activitiesId)
	{
		
		IActivities activities = this.manager.get(activitiesId);
		logger.debug(activities);
		return Response.ok().entity(activities).build();
	}
	/**
	 * 创建一个新的结伴活动
	 * @param name
	 * @param description
	 * @param longitude
	 * @param latitude
	 * @param startTime
	 * @param endTime
	 * @param userId
	 * @param userName
	 * @param userAvatar
	 * @return
	 */
	@Path("activities")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response createActivities(
			@FormParam(value = "name") String name,
			@FormParam(value = "description") String description,
			@FormParam(value = "longitude") double longitude,
			@FormParam(value = "latitude") double latitude,
			@FormParam(value = "startTime") Date startTime,
			@FormParam(value = "endTime") Date endTime,
			@FormParam(value = "userId") long userId,
			@FormParam(value = "userName") String userName,
			@FormParam(value = "userAvatar") String userAvatar,
			FormDataMultiPart formDataMultiPart
		)
	{
		IActivities activities = manager.create();
		activities.setName(name);
		activities.setDescription(description);
		activities.setLongitude(longitude);
		activities.setLatitude(latitude);
		activities.setStartTime(startTime);
		activities.setEndTime(endTime);
		activities.setUserId(userId);
		activities.setUserName(userName);
		activities.setUserAvatar(userAvatar);
		manager.save(activities);
		List<FormDataBodyPart> bodyPartList= formDataMultiPart.getFields("pictures");  
		for(FormDataBodyPart part:bodyPartList){
			InputStream input = part.getEntityAs(InputStream.class);
			String url = ImageResourceUtil.saveImage(input, ImageCategory.ACTIVITIES,String.valueOf(activities.getId()));
			System.out.println( url);
			activities.addActivitiesPic(url);
			this.manager.save(activities);
		}
		logger.debug(activities);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(activities.getId())).build()).build();
		
	}
	
	/**
	 * 批量获取活动信息
	 * @param start 页数
	 * @param count 每页个数
	 * @return
	 */
	@Path("activities")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActivities(
			@QueryParam(value = "start") int start,
			@QueryParam(value = "count") int count
		)
	{
		
		List<IActivities> activities = manager.getAvailableActivities(start, count);
		logger.debug(activities);
		return Response.ok().entity(activities).build();
	}
	/**
	 * 活动中增加用户/用户加入
	 * @param activitiesId
	 * @param userIds
	 * @return
	 */
	@Path("activities/{activitiesId}/users")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addActvitiesUsers(@PathParam(value = "activitiesId") long activitiesId,@FormParam(value = "users") String userIds){
		List<Long> _userIds = FluentIterable.from(Splitter.on(",").split(userIds)).transform(new Function<String,Long>(){

			@Override
			public Long apply(String input) {
				// TODO Auto-generated method stub
				long id = Integer.valueOf(input);
				return id;
			}}).toList();
		IActivities activities = this.manager.get(activitiesId);
		for(long userId:_userIds){
			IUser user = userManager.get(userId);
			activities.addUser(user);
		}
		logger.debug(activities);
		this.manager.save(activities);
		return Response.ok().entity(activities).build();
	}
	
	/**
	 * 
	 * @param activitiesId
	 * @param in
	 * @return
	 */
	@Path("activities/{activitiesId}/distination")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response uploadDistinationPic(@PathParam(value = "activitiesId") long activitiesId,InputStream in){
		String filePath = ImageResourceUtil.saveImage(in, ImageCategory.ACTIVITIES,String.valueOf(activitiesId));
		IActivities activites = this.manager.get(activitiesId);
		activites.addActivitiesPic(filePath);
		this.manager.save(activites);
		logger.debug(activites);
		return Response.ok().build();
		
	}
	
	/**
	 * 读取活动目的地图片
	 * @param activitiesId
	 * @return
	 */
	@Path("activities/{activitiesId}/distination")
	@GET
	@Produces("image/png")
	public Response readDistinationPic(@PathParam(value = "activitiesId") long activitiesId){
		try {
			InputStream input = ImageResourceUtil.readImageAsStream(String.valueOf(activitiesId));
			return Response.ok().entity(input).type("image/png").build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
	/**
	 * 获取指定位置周边活动
	 * @param latitude
	 * @param longitude
	 * @param start
	 * @param count
	 * @return
	 */
	@Path("around/activities")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response aroundAttractions(@QueryParam(value="latitude") double latitude,
			@QueryParam(value="longitude") double longitude,
			@QueryParam(value="start") int start,
			@QueryParam(value="count") int count){
		
		List<IActivities> atts = this.sManager.getByLatLnt(latitude, longitude/*116.406887, 39.98207*/);
		atts = FluentIterable.from(atts).skip(start).limit(count).toList();
		logger.debug(atts);
		return Response.ok().entity(atts).build();
	}
	
}
