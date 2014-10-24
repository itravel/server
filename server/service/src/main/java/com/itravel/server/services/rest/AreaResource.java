package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.itravel.server.dal.entities.AreaEntity;
import com.itravel.server.dal.managers.AreaManager;
import com.itravel.server.services.aos.AreaType;

@Path("/")
public class AreaResource {
	private static final AreaManager aManager = new AreaManager();
	
	@Path("/countries")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getCountries(){
		List<AreaEntity> areas = aManager.getAreasByType(AreaType.country.intValue());
		return Response.ok().entity(areas).build();
	}
	
	@Path("/countries/{cId}/provinces")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getProvinces(@PathParam("cId") long countryId){
		List<AreaEntity> areas = aManager.getAreasByParent(countryId, AreaType.province.intValue());
		return Response.ok().entity(areas).build();
	}
	
	@Path("/provinces/{pId}/cities")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getCities(@PathParam("pId") long provinceId){
		List<AreaEntity> areas = aManager.getAreasByParent(provinceId, AreaType.city.intValue());
		return Response.ok().entity(areas).build();
	}
	
	@Path("/cities/{cityName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getCityByCityName(@PathParam("cityName") String cityName){
		AreaEntity entity = aManager.getAreaByNameAndType(cityName,AreaType.city.intValue());
		return Response.ok().entity(entity).build();
		
	}
	
}
