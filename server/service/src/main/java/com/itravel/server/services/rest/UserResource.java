package com.itravel.server.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Optional;
import com.itravel.server.dal.entities.UserEntity;
import com.itravel.server.dal.managers.UserManager;
import com.itravel.server.services.aos.UserBean;
import com.itravel.server.services.rest.utils.JsonFactory;

@Path("users")
public class UserResource {
	@Context
	private ContainerRequestContext requestContext;
	
	private static final UserManager manager = new UserManager();
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response createUser(UserBean userBean){
		UserEntity entity = Optional.fromNullable(userBean).transform(UserBean.TO_ENTITY).get();
		boolean success = manager.saveUser(entity);
		if(!success){
			return Response.serverError().entity("保存失败").build();
		}
		userBean = new UserBean(entity);
		try {
			String json = JsonFactory.getMapper().writeValueAsString(userBean);
			return Response.ok().entity(json).build();
		} catch (JsonProcessingException e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	@Path("/{userId}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response createUser(@PathParam("userId") long id,UserBean userBean){
		userBean.setId(id);
		UserEntity entity = Optional.fromNullable(userBean).transform(UserBean.TO_ENTITY).get();
		boolean success = manager.saveUser(entity);
		if(!success){
			return Response.serverError().entity("保存失败").build();
		}
		userBean = new UserBean(entity);
		try {
			String json = JsonFactory.getMapper().writeValueAsString(userBean);
			return Response.ok().entity(json).build();
		} catch (JsonProcessingException e) {
			return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
}
