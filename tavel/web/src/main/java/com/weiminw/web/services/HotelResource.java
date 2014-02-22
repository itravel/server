package com.weiminw.web.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weiminw.business.managers.HotelManager;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.managers.IHotelManager;
import com.weiminw.web.services.json.JsonConverter;
@Path("hotels")
public class HotelResource {
	
	private IHotelManager hotelManager = HotelManager.create();
	private Logger logger = LogManager.getLogger(HotelResource.class);
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
    public Response getHotels() {
		return Response.status(Status.REQUEST_ENTITY_TOO_LARGE).build();
    }
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHotel(@PathParam("id") long id) {
		
		IHotel hotel = hotelManager.getHotelById(id);
		logger.debug(hotel);
		return Response.ok(JsonConverter.convertToJson(hotel)).build();
    }
	
	
	@GET
	@Path("/{id}")
	@Produces("image/*")
	public Response getHotel2(@PathParam("id") long id) throws URISyntaxException {
		return Response.temporaryRedirect(new URI("/travel/pictures/10000442_1.png")).build();
    }
}
