package com.itravel.server.services.rest;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("infostream")
public class HomeInfoStream {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfoStream(@QueryParam(value = "lastDate") Date lastDate){
		
		return null;
		
	}
	
}
