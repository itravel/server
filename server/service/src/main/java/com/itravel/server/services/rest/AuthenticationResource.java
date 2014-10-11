package com.itravel.server.services.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/authentication")
public class AuthenticationResource {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response createAuth(String params){
		JsonNode rootNode;
		try {
			rootNode = mapper.readValue(params, JsonNode.class);
			String userName = rootNode.get("userName").textValue();
			String password = rootNode.get("password").textValue();
			return Response.ok().header("auth-token", "1234").build();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.UNAUTHORIZED).build();
		
	}
}
