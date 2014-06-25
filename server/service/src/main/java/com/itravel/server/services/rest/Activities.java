package com.itravel.server.services.rest;


import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.filters.UpcomingEventDBFilter;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
import com.itravel.server.services.rest.queries.UpcomingEventsQuery;
import com.mysql.jdbc.log.LogFactory;

/**
 * 该类线程安全，每次请求都创建一个新对象。
 * 编写代码时请注意性能。
 * @author william.wangwm
 *
 */
@Singleton
@Path("/activities")
public class Activities {
	private static Logger logger = LogManager.getLogger(Activities.class);
	private static IDataRepository<ActivitiesEntity> dataRepo = new UpcomingEventsDBRepository();
	private static ObjectMapper om = new ObjectMapper();
	public Activities(){
		logger.info("activities server started");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response query(@BeanParam UpcomingEventsQuery query){
		logger.info(query);
		IFilter<ActivitiesEntity> filter = query.createFilter();
		List<ActivitiesEntity> entities = dataRepo.filterBy(filter);
		
		return Response.ok(entities).build();
	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response get(@PathParam(value = "id") long id){
		IFilter<ActivitiesEntity> filter = UpcomingEventDBFilter.createIDFilter(id);
		List<ActivitiesEntity> entities = dataRepo.filterBy(filter);
		return Response.ok(entities).build();
	}
}
