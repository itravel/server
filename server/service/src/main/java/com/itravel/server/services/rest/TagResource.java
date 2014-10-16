package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.TagCategoryEntity;
import com.itravel.server.dal.entities.TagEntity;
import com.itravel.server.dal.managers.TagCategoryManager;
import com.itravel.server.dal.managers.TagManager;

@Path("/tags")
public class TagResource {
	private static TagManager manager = new TagManager();
	private static TagCategoryManager tcManager = new TagCategoryManager();
	private ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LogManager.getLogger(TagResource.class);
	@Context
	UriInfo uriInfo;
	
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTags(
		){
		List<TagEntity> tags = this.manager.getAll();
			
		return Response.ok(tags).build();
	}
	
	@Path("/categories")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTagCategories(
		){
		List<TagCategoryEntity> entities = this.tcManager.getAll();
		System.out.println(entities);
			
		return Response.ok(entities).build();
	}
	
}
