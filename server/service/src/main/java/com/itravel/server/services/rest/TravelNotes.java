package com.itravel.server.services.rest;

import java.io.InputStream;

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

import com.itravel.server.interfaces.dal.ITravelNote;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;
import com.itravel.server.interfaces.dal.managers.ManagerFactory;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;

@Path("travelnotes")
public class TravelNotes {
	ITravelNoteManager tManager = ManagerFactory.getTravelNoteManager();
	@Context
	UriInfo uriInfo;
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value="id") long id){
		ITravelNote travelNote = tManager.get(id);
		return Response.ok().entity(travelNote).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
			@FormParam(value = "userAvatar") String userAvatar
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
		return Response.ok().entity(tNote).build();
	}
	
	@Path("{traveNoteId}/pictures")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserAvatar(@PathParam(value = "traveNoteId") long traveNoteId,InputStream in){
		ITravelNote tNote = this.tManager.get(traveNoteId);
		String imagePath = ImageResourceUtil.saveImage(in, ImageCategory.TRAVEL_NOTE, String.valueOf(traveNoteId));
		tNote.addPicture(imagePath);
		this.tManager.save(tNote);
		return Response.ok().entity(tNote).build();
	
	}
	
	
	
}
