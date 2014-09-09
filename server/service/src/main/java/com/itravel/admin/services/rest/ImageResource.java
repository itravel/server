package com.itravel.admin.services.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
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
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSink;
import com.google.common.io.Files;

@Path("/images")
public class ImageResource {
	private static File IMAGE_DIR=null;
	static {
		if(System.getProperty("imageDir")!=null){
			IMAGE_DIR=new File(System.getProperty("imageDir"));
		}
		else {
			IMAGE_DIR=new File("/usr/share/nginx/www/images/");//服务器上默认图片目录
		}
		if(!IMAGE_DIR.exists()){
			IMAGE_DIR.mkdirs();
		}
	}
	
	@Context
	UriInfo uriInfo;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Path("activities")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(FormDataMultiPart formDataMultiPart)
			throws IOException {
		List<FormDataBodyPart> bodyPartList = formDataMultiPart
				.getFields("images");
		FormDataBodyPart part = bodyPartList.get(0);
		
		File imageFile = File.createTempFile("activities-", ".png",IMAGE_DIR);
		ByteSink bs = Files.asByteSink(imageFile);
		InputStream input = part.getEntityAs(InputStream.class);
		bs.writeFrom(input);
		Map<String,String> entity = Maps.newHashMapWithExpectedSize(1);
		entity.put("imageNames", imageFile.getName());
		return Response.created(uriInfo.getBaseUriBuilder().path(imageFile.getName()).build()).entity(mapper.writeValueAsString(entity)).build();
	}
}
