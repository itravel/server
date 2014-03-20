package com.itravel.server.services.rest;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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

import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.interfaces.dal.managers.IUserManager;
import com.itravel.server.interfaces.dal.managers.ManagerFactory;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;



@Path("users")
public class Users {
	IUserManager manager = ManagerFactory.getUserManager();
	@Context
	UriInfo uriInfo;
	public Users() {
		
	}
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUser(@PathParam("id") long id){
		IUser user = manager.get(id);
		return user.toString();
	}
	
	/**
	 * �����û����������ȡ�û���Ϣ
	 * @param userName
	 * @param password
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUsrPwd(@QueryParam(value = "userName") String userName,@QueryParam(value = "password") String password){
		if(userName == null || password == null) {
			return Response.status(Status.BAD_REQUEST).entity("�û������벻��Ϊ��").build();
		}
		IUser usr = this.manager.getUserByUserName(userName);
		if(usr == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		if (password == usr.getPassword()) {
			return Response.ok(usr.toString()).build();
		}
		
		return Response.status(Status.FORBIDDEN).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createUser(
			@FormParam(value = "userName") String userName,
			@FormParam(value = "password") String email,
			@FormParam(value = "password") String password,
			@FormParam(value = "password") String avatar,
			@FormParam(value = "password") String cellPhone,
			@FormParam(value = "password") int qq,
			@FormParam(value = "password") String weibo,
			@FormParam(value = "password") int province,
			@FormParam(value = "password") int city,
			@FormParam(value = "password") int district,
			@FormParam(value = "password") double longitude,
			@FormParam(value = "password") double latitude
		){
		
		IUser user = manager.create();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		user.setAvatar("");
		user.setCellPhone(cellPhone);
		user.setQq(qq);
		user.setWeibo(weibo);
		user.setProvince(province);
		user.setCity(city);
		user.setDistrict(district);
		user.setLongitude(longitude);
		user.setLatitude(latitude);
		manager.save(user);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(user.getId())).build()).build();
	}
	
	@Path("{userId}/avatar")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserAvatar(@PathParam(value = "userId") long userId,InputStream in){
		String avatarPicPath = ImageResourceUtil.saveImage(in, ImageCategory.USER_AVATAR,String.valueOf(userId));
		IUser user = this.manager.get(userId);
		user.setAvatar(avatarPicPath);
		this.manager.save(user);
		return Response.ok().build();
		
	}
	
	
	@Path("{userId}/avatar")
	@GET
	@Produces("image/png")
	public Response readUserAvatar(@PathParam(value = "userId") long userId){
		try {
			InputStream input = ImageResourceUtil.readImageAsStream(String.valueOf(userId));
			return Response.ok().entity(input).type("image/png").build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.NOT_FOUND).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.serverError().build();
		
	}
	
	
	
	
}
