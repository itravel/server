package com.itravel.server.services.rest;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itravel.server.dal.entities.ActivitiesCommentsEntity;
import com.itravel.server.dal.filters.ActivitiesCommentsDBFilter;
import com.itravel.server.dal.repos.AtivitiesCommentsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
@Path("/ActivitiesComments")
public class ActivitiesComments {
	private static IDataRepository<ActivitiesCommentsEntity> dataRepo = new AtivitiesCommentsDBRepository();
	private static ObjectMapper om = new ObjectMapper();
	@GET
	@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
	public Response f(@QueryParam (value = "activitiesId") @DefaultValue("0") long activitiesId
			) throws JsonProcessingException{
		IFilter<ActivitiesCommentsEntity> filter = ActivitiesCommentsDBFilter.createCommentsFilterByActivitiesId(activitiesId);
		List<ActivitiesCommentsEntity> entities = dataRepo.filterBy(filter);
		//List<LatestActivitiesAOS> aosEntities  = getAos(entities);
		//System.out.println(aosEntities.size());
		return Response.ok().entity(om.writeValueAsString(entities)).build();
	}
}
