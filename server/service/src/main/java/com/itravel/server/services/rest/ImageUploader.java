package com.itravel.server.services.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSink;
import com.google.common.io.Files;

@Singleton
@Path("/images")
public class ImageUploader {
	@Context
	UriInfo uriInfo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@GET
	public String ss (){
		System.out.println(uriInfo.getBaseUri().toString());
		return uriInfo.getBaseUriBuilder().path("fdfdf").build().toString();
	}
	
	@Path("activities")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(FormDataMultiPart formDataMultiPart)
			throws IOException {
		List<FormDataBodyPart> bodyPartList = formDataMultiPart
				.getFields("pictures");
		FormDataBodyPart part = bodyPartList.get(0);
		File _temp = File.createTempFile("activities-", ".png");
		System.out.println(_temp.toString());
		ByteSink bs = Files.asByteSink(_temp);
		InputStream input = part.getEntityAs(InputStream.class);
		bs.writeFrom(input);
		Map<String,String> entity = Maps.newHashMapWithExpectedSize(1);
		entity.put("imageNames", _temp.getName());
		return Response.created(uriInfo.getBaseUriBuilder().path(_temp.getName()).build()).entity(mapper.writeValueAsString(entity)).build();
	}

}
