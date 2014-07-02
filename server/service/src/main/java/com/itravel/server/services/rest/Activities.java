package com.itravel.server.services.rest;


import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.filters.ActivitiesDBFilter;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
import com.itravel.server.services.rest.queries.UpcomingEventsQuery;
import com.mysql.jdbc.log.LogFactory;

/**
 * @author william.wangwm
 *
 */
@Singleton
@Path("/activities")
public class Activities {
	private static Logger logger = LogManager.getLogger(Activities.class);
	private static IDataRepository<ActivitiesEntity> dataRepo = new UpcomingEventsDBRepository();
	private static ObjectMapper om = new ObjectMapper();
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static {
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		om.setDateFormat( new SimpleDateFormat("yyyy-MM-dd")); 
	}
	public Activities(){
		logger.info("activities server started");
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response query(@BeanParam UpcomingEventsQuery query){
		logger.info(query);
		IFilter<ActivitiesEntity> filter = query.createFilter();
		List<ActivitiesEntity> entities = dataRepo.filterBy(filter);

		try {
			return Response.ok(om.writeValueAsString(entities)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response get(@PathParam(value = "id") long id){
		
		IFilter<ActivitiesEntity> filter = ActivitiesDBFilter.createIDFilter(id);
		List<ActivitiesEntity> entities = dataRepo.filterBy(filter);
		System.out.println(entities);
		if(entities.isEmpty()){
			return Response.status(Status.NOT_FOUND).entity("{}").build();
		}
		try {
			return Response.ok(om.writeValueAsString(entities.get(0))).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		
	}
	
	@Path("/month/{month}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getByMonth(@PathParam(value = "month") int month){
		if(month<0||month>12){
			Response.status(Status.BAD_REQUEST).entity("month should >0 and <= 12");
		}
		int cityCode = 110000;
		IFilter<ActivitiesEntity> filter = ActivitiesDBFilter.createMonthFilter(month,cityCode);
		List<ActivitiesEntity> entities = filter.doFilter(dataRepo);
		if(entities.isEmpty()){
			return Response.status(Status.NOT_FOUND).entity(entities).build();
		}
		try {
			return Response.ok(om.writeValueAsString(entities)).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(
			@FormParam(value = "title") String title,
			@FormParam(value = "abstractContent") String abstractContent,
			@FormParam(value = "startTime") String _startTime,
			@FormParam(value = "endTime") String _endTime,
			@FormParam(value = "city") int cityCode,
			@FormParam(value = "address") String address,
			@FormParam(value = "type") int type,
			@FormParam(value = "scale") int scale,
			@FormParam(value = "fee") long fee
			
		){
		try {
//			List<FormDataBodyPart> bodyPartList= formDataMultiPart.getFields("pictures");  
//			for(FormDataBodyPart part:bodyPartList){
//				InputStream input = part.getEntityAs(InputStream.class);
////				String url = ImageResourceUtil.saveImage(input, ImageCategory.TRAVEL_NOTE,String.valueOf(tNote.getId()));
//				System.out.println( "111");
//			}
			Date startTime = simpleDateFormat.parse(_startTime);
			Date endTime = simpleDateFormat.parse(_endTime);
			ActivitiesEntity entity = new ActivitiesEntity();
			entity.setTitle(title);
			entity.setAbstractContent(abstractContent);
			entity.setAddress(address);
			entity.setStartTime(startTime);
			entity.setEndTime(endTime);
			entity.setCityCode(cityCode);
			entity.setType(type);
			entity.setScale(scale);
			entity.setFee(fee);
			dataRepo.persist(entity);
//			entity.setImages("/images/"+entity.getId()+".jpg");
//			dataRepo.persist(entity);
			return Response.ok().build();
		}
		catch(Exception e){
			return Response.serverError().build();
		}
			
		} 
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response create(
			@FormParam(value = "title") String title,
			@FormParam(value = "abstractContent") String abstractContent,
			@FormParam(value = "startTime") String _startTime,
			@FormParam(value = "endTime") String _endTime,
			@FormParam(value = "city") String cityCode,
			@FormParam(value = "address") String address,
			@FormParam(value = "type") int type,
			@FormParam(value = "scale") int scale
		){
		try {
			Date startTime = simpleDateFormat.parse(_startTime);
			Date endTime = simpleDateFormat.parse(_endTime);
			ActivitiesEntity entity = new ActivitiesEntity();
			entity.setTitle(title);
			entity.setAbstractContent(abstractContent);
			entity.setAddress(address);
			entity.setStartTime(startTime);
			entity.setEndTime(endTime);
			entity.setType(type);
			entity.setScale(scale);
			dataRepo.persist(entity);
			return Response.ok().build();
			
		} catch (ParseException e) {
		
			e.printStackTrace();
			return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
}
