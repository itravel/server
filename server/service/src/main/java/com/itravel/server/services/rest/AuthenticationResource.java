package com.itravel.server.services.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.UserEntity;
import com.itravel.server.dal.managers.UserManager;
import com.itravel.server.services.rest.utils.AuthUtil;

@Path("/authentication")
public class AuthenticationResource {
	private static final UserManager manager = new UserManager();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createAuth(@FormParam("userName") String userName,@FormParam("password") String password){
		try {
			UserEntity entity = manager.getUserByUserName(userName);
			if(entity.getPassword().equals(password)){
				String authToken = AuthUtil.encodes(userName, password);
				return Response.ok().header("auth-token", authToken).build();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.UNAUTHORIZED).build();
		
	}
	
}
