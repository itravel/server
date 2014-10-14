package com.itravel.server.services.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.FluentIterable;
import com.itravel.server.dal.entities.ActivityCommentEntity;
import com.itravel.server.dal.managers.ActivityCommentManager;
import com.itravel.server.services.aos.ActivityCommentBean;
import com.itravel.server.services.rest.utils.JsonFactory;

@Path("/")
public class ActivityCommentResource {
	private static final ActivityCommentManager manager = new ActivityCommentManager();
	private static final Logger logger = LogManager.getLogger(ActivityCommentResource.class);
	@Path("/activities/{activityId}/comments")
	@GET
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getCommentsByActivity(@PathParam("activityId") long activityId){
		List<ActivityCommentEntity> entities = manager.getCommentsByActivityId(activityId);
		List<ActivityCommentBean> comments = FluentIterable.from(entities).transform(ActivityCommentBean.FROM_ENTITY).toList();
		try {
			String json = JsonFactory.getMapper().writeValueAsString(comments);
			return Response.ok().encoding(json).build();
		} catch (JsonProcessingException e) {
			logger.error(e);
			return Response.serverError().entity(e.getMessage()).build();
		}
	}
	
	
}
