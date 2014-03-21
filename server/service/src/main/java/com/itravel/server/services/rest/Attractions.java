package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.dal.spatial.AbstractSpatialManager;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

@Path("/")
public class Attractions {
	
	private static final IAttractionsManager aManager = new AttractionsManager();
	private static final Logger logger = LogManager.getLogger(Attractions.class);
	public Attractions(){
		
	}
	
	@Path("around/attractions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam(value="latitude") double latitude,
			@QueryParam(value="longitude") double longitude,
			@QueryParam(value="start") int start,
			@QueryParam(value="count") int count){
		List<IAttractions> atts = this.aManager.getByLngLat(start, count, longitude,latitude /*116.406887, 39.98207*/);
		System.out.println(atts);
		return Response.ok().entity(atts).build();
	}
}
