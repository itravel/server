package com.itravel.server.services.rest;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class LuckMoney {
	@Path("artificials")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createLuckMoney(
			@FormParam("userId") long userId,
			@FormParam("userName") String userName,
			@FormParam("userAvatar") String userAvatar,
			@FormParam("luckMoney") int luckMoney,
			@FormParam("latitude") double latitude,
			@FormParam("longitude") double longitude
		){
			return Response.created(URI.create("/1")).build();
		
	}
	
	@Path("around/luck")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Response get(@QueryParam(value="latitude") double latitude,
			@QueryParam(value="longitude") double longitude,
			@QueryParam(value="start") int start,
			@QueryParam(value="count") int count){
		return Response.ok().entity("testting").build();
	}
}
