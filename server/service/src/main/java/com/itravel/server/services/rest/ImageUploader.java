package com.itravel.server.services.rest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;

@Path("/imageUpload")
public class ImageUploader {
	
	@Path("activities")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response create(FormDataMultiPart formDataMultiPart) throws IOException{
		List<FormDataBodyPart> bodyPartList= formDataMultiPart.getFields("pictures");
		FormDataBodyPart part = bodyPartList.get(0);
//		for(FormDataBodyPart part:bodyPartList){
			File _temp = File.createTempFile("activities-", ".png");
			System.out.println(_temp.toString());
			ByteSink bs = Files.asByteSink(_temp);
			InputStream input = part.getEntityAs(InputStream.class);
			bs.writeFrom(input);
//		}
//		input.close();
		return Response.ok().entity("{}").build();
	}
			

}
