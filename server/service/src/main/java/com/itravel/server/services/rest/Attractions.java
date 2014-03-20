package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.dal.spatial.AbstractSpatialManager;
import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

@Path("attractions")
public class Attractions {
	
	public static final IAttractionsManager aManager = new AttractionsManager();
	public Attractions(){
		System.out.println("----------------");
	}
	@GET
	public Response get(){
		List<IAttractions> atts = this.aManager.getByLngLat(0, 0, 116.406887, 39.98207);
		return Response.ok().entity(atts).build();
	}
}
