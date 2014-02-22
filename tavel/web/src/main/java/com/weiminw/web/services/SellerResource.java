package com.weiminw.web.services;

import java.net.URI;
import java.util.List;

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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weiminw.business.aos.Sellers;
import com.weiminw.business.managers.AccountManager;
import com.weiminw.travel.dao.impls.pos.SellerEntity;
import com.weiminw.travel.interfaces.daos.ISeller;
import com.weiminw.travel.interfaces.daos.IUser;
import com.weiminw.web.exceptions.ExceptionMessages;
import com.weiminw.web.services.json.JsonConverter;

@Path("/sellers")
public final class SellerResource {
	private static final Logger logger = LogManager.getLogger(SellerResource.class);
	AccountManager manager = AccountManager.create();
	@Context
	UriInfo uriInfo;
	@POST
	@Consumes("application/x-www-form-urlencoded") 
	public Response createUser(
			@FormParam(value = "name") String name,
			@FormParam(value = "password") String password,
			@FormParam(value="cellPhone") String cellPhone,
			@FormParam(value="cloudPushCk") String cloudPushCk,
			@FormParam(value="cloudPushUk") String cloudPushUk,
			@FormParam(value="cloudPushTag") String cloudPushTag,
			@FormParam(value="identityType") short identityType,
			@FormParam(value="identity") String identity,
			@FormParam(value="hid") long hid,
			@FormParam(value="fixTelephone") String fixTelephone){

		
		ISeller seller = Sellers.of(name, password, hid, cellPhone, fixTelephone, cloudPushCk, cloudPushTag, cloudPushUk, identity, (byte)identityType);
		this.manager.addSeller(seller);
		return Response.created(uriInfo.getBaseUriBuilder().path("/sellers/"+seller.getId()).build()).build();
		
	}
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response get(@PathParam("id") long id){
		if(id <=0) {
			return Response.noContent().build();
		}
		ISeller seller = this.manager.getSellerById(id);
		return  Response.ok(JsonConverter.convertToJson(seller)).build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getByHid(@QueryParam("hid") long hid){
		if(hid <=0) {
			return Response.noContent().build();
		}
		List<ISeller> sellers = this.manager.getSellerByHid(hid);
		return  Response.ok(JsonConverter.convertToJson(sellers)).build();
		
	}
	
	
	@GET
	@Path("auth")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response auth(@QueryParam("name") String name,@QueryParam("password") String password){
		ISeller seller = this.manager.getsellerByName(name);
		if(seller == null) {
			return Response.status(Status.NOT_FOUND).entity(JsonConverter.convertToJson(ExceptionMessages.USER_NOT_FOUND)).build();
		}
		if(seller.getPassword() != password){
			return Response.status(Status.FORBIDDEN).entity(JsonConverter.convertToJson(ExceptionMessages.AUTHENTICATION_FAILD)).build();
		}
		return Response.ok().entity(JsonConverter.convertToJson(seller)).build();
		
	}
	
	
}
