package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itravel.server.interfaces.dal.IAttractions;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;
import com.itravel.server.services.aos.Constants;
import com.itravel.server.services.utils.ManagerFactory;

@Path("/")
public class Attractions {
	public static final class QueryBean {
		@QueryParam("queryBy")
		String queryBy;
		@QueryParam("start")
		int start;
		@QueryParam("count")
		int count;
		@QueryParam("latitude")
		double latitude;
		@QueryParam("longitude")
		double longitude;
		@QueryParam("cityCode")
		public int cityCode;
		
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return ToStringBuilder.reflectionToString(this);
		}
	}
	private final IAttractionsManager aManager = ManagerFactory.getAttractionManager();
	private final Logger logger = LogManager.getLogger(Constants.LOGGER);
	@Context
	private UriInfo requestContext;
	public Attractions(){
		
	}
	/**
	 * 获取指定位置周边景点
	 * @param latitude
	 * @param longitude
	 * @param start
	 * @param count
	 * @return
	 */
	@Path("around/attractions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response aroundAttractions(@QueryParam(value="latitude") double latitude,
			@QueryParam(value="longitude") double longitude,
			@QueryParam(value="start") int start,
			@QueryParam(value="count") int count){
		
		List<IAttractions> atts = this.aManager.getByLngLat(start, count, longitude,latitude /*116.406887, 39.98207*/);
		logger.debug(atts);
		return Response.ok().entity(atts).build();
	}
	
	/**
	 * 获取指定位置周边景点
	 * @param latitude
	 * @param longitude
	 * @param start
	 * @param count
	 * @return
	 */
	@Path("attractions")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAttractionsByCity(@BeanParam QueryBean query){
		if("city".equals(query.queryBy)){
			List<IAttractions> atts = this.queryByCity(query);
			return Response.ok().entity(atts).build();
		}
		return Response.serverError().entity("").build();
	}
	
	private List<IAttractions> queryByCity(QueryBean query){
		List<IAttractions> atts = this.aManager.getByCity(query.start, query.count, query.cityCode);
		return atts;
	}
	
}
