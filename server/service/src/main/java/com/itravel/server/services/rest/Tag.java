package com.itravel.server.services.rest;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

@Singleton
@Path("/tags")
public class Tag {
	private TagManager manager = new TagManager();
	private TagCategoryManager tcManager = new TagCategoryManager();
	private ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LogManager.getLogger(Tag.class);
	@Context
	UriInfo uriInfo;
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTag(
			@FormParam(value="tag") String tagValue,
			@FormParam(value="category") long category
		){
			
			TagEntity entity = new TagEntity();
			entity.setTag(tagValue);
			TagCategoryEntity tcEntity = tcManager.get(category);
			entity.setCategory(tcEntity);
			this.manager.save(entity);
			return Response.created(this.uriInfo.getBaseUriBuilder().path(String.valueOf(entity.getId())).build()).entity(entity).build();
	}
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTags(
			
		){
		List<TagEntity> tags = this.manager.getAll();
			
		return Response.ok(tags).build();
	}
	
	@Path("/categories")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTagCategory(
			@FormParam(value="category") String category
		){
			TagCategoryEntity entity = new TagCategoryEntity();
			entity.setCategory(category);
			this.tcManager.save(entity);
			return Response.created(this.uriInfo.getBaseUriBuilder().path(String.valueOf(entity.getId())).build()).entity(entity).build();
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
	
	@Path("/categories/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTagCatetory(@PathParam("id") long id){
		logger.info("delete tagcategory(id="+id+")");
		this.tcManager.delete(id);
		return Response.ok().build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTag(@PathParam("id") long id){
		logger.info("delete tag(id="+id+")");
		this.manager.delete(id);
		return Response.ok().build();
	}
}
