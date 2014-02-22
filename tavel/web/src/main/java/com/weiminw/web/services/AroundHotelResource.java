package com.weiminw.web.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weiminw.business.managers.HotelManager;
import com.weiminw.business.spatial.HotelSpatial;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.daos.IHotelLocation;
import com.weiminw.travel.interfaces.managers.IHotelManager;
import com.weiminw.web.services.json.JsonConverter;

@Path("/around/hotels/")
public class AroundHotelResource {
	private static final HotelManager hotelDAO = HotelManager.create();
	private static final Logger logger = LogManager.getLogger(AroundHotelResource.class);
	@Context
	UriInfo uriInfo;
	@GET
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public String getHotels(@QueryParam("lnt") double lnt,@QueryParam("lat") double lat,@QueryParam("start") int start){
		List<IHotelLocation> hotels = hotelDAO.getHotelLocation(lnt,lat,5,start,50);
		return JsonConverter.convertToJson(hotels);
	}
}
