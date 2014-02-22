package com.weiminw.web.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import com.weiminw.business.aos.Users;
import com.weiminw.business.managers.AccountManager;
import com.weiminw.travel.interfaces.daos.IUser;
import com.weiminw.web.exceptions.ExceptionMessages;
import com.weiminw.web.services.json.JsonConverter;
@Path("/users")
public class UserResource {
	private static final AccountManager manager = AccountManager.create();
	private Logger logger = LogManager.getLogger(UserResource.class);
	@Context
	UriInfo uriInfo;
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	public Response createUser(@FormParam(value = "name") String name,
			@FormParam(value="cellPhone") String cellPhone,
			@FormParam(value="cloudPushCk") String cloudPushCk,
			@FormParam(value="cloudPushUk") String cloudPushUk,
			@FormParam(value="cloudPushTag") String cloudPushTag,
			@FormParam(value="password") String password,
			@FormParam(value="nick") String nick){
		IUser user = Users.of(name,nick,password,cellPhone,cloudPushCk,cloudPushUk,cloudPushTag);
		logger.debug(user);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(user.getId())).build()).build();
	}
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createUser(String userJson){

		return null;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id){
		IUser user = this.manager.getUserById(id);
		return Response.ok(JsonConverter.convertToJson(user)).build();
	}
	
	@GET
	@Path("auth")
	public Response auth(@QueryParam("name") String name,@QueryParam("password") String password){
		IUser user = this.manager.getUserByUserName(name);
		if(user == null) {
			return Response.status(Status.NOT_FOUND).entity(JsonConverter.convertToJson(ExceptionMessages.USER_NOT_FOUND)).build();
		}
		if(user.getPassword() != password){
			return Response.status(Status.FORBIDDEN).entity(JsonConverter.convertToJson(ExceptionMessages.AUTHENTICATION_FAILD)).build();
		}
		return Response.ok().entity(JsonConverter.convertToJson(user)).build();
	}
}
