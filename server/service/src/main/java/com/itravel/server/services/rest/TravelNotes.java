package com.itravel.server.services.rest;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.itravel.server.interfaces.dal.ITravelNote;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;
import com.itravel.server.services.aos.Constants;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;
import com.itravel.server.services.utils.ManagerFactory;
@Path("/")
public class TravelNotes {
	private final ITravelNoteManager tManager = ManagerFactory.getTravelNoteManager();
	private final Logger logger = LogManager.getLogger(Constants.LOGGER);
	@Context
	UriInfo uriInfo;
	
	/**
	 * 根据id获取微游记信息
	 * @param id
	 * @return
	 */
	@Path("travelnotes/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value="id") long id){
		ITravelNote travelNote = tManager.get(id);
		return Response.ok().entity(travelNote).build();
		
	}
	/**
	 * 创建微游记
	 * @param province
	 * @param city
	 * @param destination
	 * @param title
	 * @param content
	 * @param latitude
	 * @param longitude
	 * @param userId
	 * @param userName
	 * @param userAvatar
	 * @param formDataMultiPart
	 * @return
	 */
	@Path("travelnotes")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(
			@FormParam(value = "province") int province, 
			@FormParam(value = "city") int city, 
			@FormParam(value = "destination") String destination, 
			@FormParam(value = "title") String title, 
			@FormParam(value = "content") String content, 
			@FormParam(value = "latitude") double latitude, 
			@FormParam(value = "longitude") double longitude, 
			@FormParam(value = "userId") long userId,
			@FormParam(value = "userName") String userName,
			@FormParam(value = "userAvatar") String userAvatar,
			FormDataMultiPart formDataMultiPart
		) 
	{
		ITravelNote tNote = this.tManager.create();
		tNote.setProvince(province);
		tNote.setCity(city);
		tNote.setDestination(destination);
		tNote.setTitle(title);
		tNote.setContent(content);
		tNote.setUserId(userId);
		tNote.setUserName(userName);
		tNote.setUserAvatar(userAvatar);
		tNote.setLatitude(latitude);
		tNote.setLongitude(longitude);
		this.tManager.save(tNote);
		List<FormDataBodyPart> bodyPartList= formDataMultiPart.getFields("pictures");  
		for(FormDataBodyPart part:bodyPartList){
			InputStream input = part.getEntityAs(InputStream.class);
			String url = ImageResourceUtil.saveImage(input, ImageCategory.TRAVEL_NOTE,String.valueOf(tNote.getId()));
			System.out.println( url);
			tNote.addPicture(url);
			this.tManager.save(tNote);
		}
		logger.debug(tNote);
		return Response.ok().entity(tNote).build();
	}
	
	@Path("travelnotes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(String json){
		ITravelNote travelNote = this.tManager.create(json);
		this.tManager.save(travelNote);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(travelNote.getId())).build()).build();
	}
			
	
//	@Path("{traveNoteId}/pictures")
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response uploadUserAvatar(@PathParam(value = "traveNoteId") long traveNoteId,InputStream in){
//		ITravelNote tNote = this.tManager.get(traveNoteId);
//		String imagePath = ImageResourceUtil.saveImage(in, ImageCategory.TRAVEL_NOTE, String.valueOf(traveNoteId));
//		tNote.addPicture(imagePath);
//		this.tManager.save(tNote);
//		return Response.ok().entity(tNote).build();
//	
//	}
	
	
	
}
