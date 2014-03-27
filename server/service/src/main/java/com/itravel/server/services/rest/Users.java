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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.interfaces.dal.managers.IUserManager;
import com.itravel.server.services.aos.Constants;
import com.itravel.server.services.utils.ImageCategory;
import com.itravel.server.services.utils.ImageResourceUtil;
import com.itravel.server.services.utils.ManagerFactory;



@Path("/")
public class Users {
	private final IUserManager manager = ManagerFactory.getUserManager();
	private final Logger logger = LogManager.getLogger(Constants.LOGGER);
	@Context
	UriInfo uriInfo;
	public Users() {
		
	}
	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return
	 */
	@Path("users/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("id") long id){
		IUser user = manager.get(id);
		logger.debug(user);
		return Response.ok().entity(user).build();
	}
	
	/**
	 * 根据用户名，密码获取用户信息
	 * @param userName
	 * @param password
	 * @return
	 */
	@Path("users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUsrPwd(@QueryParam(value = "userName") String userName,@QueryParam(value = "password") String password){
		if(userName == null || password == null) {
			return Response.status(Status.BAD_REQUEST).entity("用户名密码不能为空").build();
		}
		IUser usr = this.manager.getUserByUserName(userName);
		if(usr == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		if (password == usr.getPassword()) {
			return Response.ok(usr.toString()).build();
		}
		logger.debug(usr);
		return Response.status(Status.FORBIDDEN).build();
	}
	/**
	 * 创建用户信息
	 * @param userName
	 * @param email
	 * @param password
	 * @param avatar
	 * @param cellPhone
	 * @param qq
	 * @param weibo
	 * @param province
	 * @param city
	 * @param district
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	@Path("users")
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
		logger.debug(user);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(user.getId())).build()).build();
	}
	
	/**
	 * 创建用户信息
	 * @param json
	 * @return
	 */
	@Path("users")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(String json){
		IUser user = this.manager.create(json);
		this.manager.save(user);
		logger.debug(user);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(user.getId())).build()).build();
	}
			
	/**
	 * 上传头像
	 * @param userId
	 * @param in
	 * @return
	 */
	@Path("users/{userId}/avatar")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserAvatar(@PathParam(value = "userId") long userId,InputStream in){
		String avatarPicPath = ImageResourceUtil.saveImage(in, ImageCategory.USER_AVATAR,String.valueOf(userId));
		IUser user = this.manager.get(userId);
		user.setAvatar(avatarPicPath);
		this.manager.save(user);
		logger.debug(user);
		return Response.ok().build();
		
	}
	
	/**
	 * 获取头像
	 * @param userId
	 * @return
	 */
	@Path("users/{userId}/avatar")
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
	
	/**
	 * 验证手机号码是否重复
	 * @param phoneNumber
	 * @return
	 */
	@Path("users/phone/{phoneNumber}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response validate(@PathParam(value = "phoneNumber") String phoneNumber){
		IUser user = this.manager.getUserByPhoneNumber(phoneNumber);
		if (user!=null){
			return Response.ok().build();
		}
		logger.debug(user);
		return Response.status(Status.NOT_FOUND).entity("").build();
	}
	

	
	
	
}
