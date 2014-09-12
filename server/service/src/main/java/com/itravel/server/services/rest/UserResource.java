package com.itravel.server.services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("users")
public class UserResource {
	@Context
	private ContainerRequestContext requestContext;
	@Path("/login")
	@GET
	public Response login(){
		return Response.ok().header("Cookie", "111:11").build();
		
	}
	
}
