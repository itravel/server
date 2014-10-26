package com.itravel.admin.services.rest;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import com.google.common.io.ByteSink;
import com.google.common.io.Files;
import com.itravel.server.services.rest.utils.RedisUtil;

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
	
	public BufferedImage FangDaImage(BufferedImage srcimage,int targetWidth,int targetHeight){
        Image image = srcimage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);  
        BufferedImage tag = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);  
        Graphics g = tag.getGraphics();  
        g.drawImage(image, 0, 0, null);
        g.dispose(); 
        return tag;
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
		
		InputStream input = part.getEntityAs(InputStream.class);
		BufferedImage image = ImageIO.read(input);
		String fileName="activities-"+String.valueOf(System.nanoTime());
		RedisUtil redisUtil = new RedisUtil();
		redisUtil.setImage(fileName, image);
		Map<String,String> entity = Maps.newHashMapWithExpectedSize(1);
		entity.put("imageNames",fileName);
		return Response.created(uriInfo.getBaseUriBuilder().path(fileName).build()).entity(mapper.writeValueAsString(entity)).build();
	}
	
	@Path("activities/{name}")
	@GET
	@Produces("image/png")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response getPicture(@PathParam("name") String name){
		String sizeStr = name.substring(name.indexOf("_")+"_".length());
		String keyName = name.substring(0,name.indexOf("_"));
		String[] aa  = sizeStr.split("x");
		if(aa.length==2){
			int width = Integer.parseInt(aa[0]);
			int height = Integer.parseInt(aa[1]);
			System.out.println(width);
			System.out.println(height);
			System.out.println(keyName);
			RedisUtil redisUtil = new RedisUtil();
			BufferedImage image = redisUtil.getImage(keyName);
			BufferedImage newImage = FangDaImage(image,width,height);
			System.out.println(newImage.getHeight());
			System.out.println(newImage.getWidth());
			Response.ok().entity(redisUtil.getCompressedImage(newImage)).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
	
}
