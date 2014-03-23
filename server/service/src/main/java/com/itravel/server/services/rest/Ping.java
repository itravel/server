package com.itravel.server.services.rest;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.itravel.server.dal.entities.UserEntity;
import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;

@Path("ping")
public class Ping {
	@Context
	SecurityContext sc;
	@GET
	public Response Hello(){
		return Response.ok().entity(sc.getUserPrincipal().getName()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(@FormParam("a") String form){
		return Response.ok().build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creat2(UserEntity form){
		System.out.println(form);
		return Response.ok().build();
		
	}
}
