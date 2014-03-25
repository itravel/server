package com.itravel.server.services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itravel.server.services.aos.Constants;

public class AbstractLuck {
	private final Logger logger = LogManager.getLogger(Constants.LOGGER);
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
