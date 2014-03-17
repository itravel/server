package com.itravel.server.services.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.itravel.server.interfaces.dal.ITravelNote;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;
import com.itravel.server.interfaces.dal.managers.ManagerFactory;

@Path("travelnotes")
public class TravelNotes {
	ITravelNoteManager tManager = ManagerFactory.getTravelNoteManager();
	@Context
	UriInfo uriInfo;
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam(value="id") long id){
		ITravelNote travelNote = tManager.get(id);
		return Response.ok().entity(travelNote).build();
		
	}
}
