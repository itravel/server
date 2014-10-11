package com.itravel.server.services.rest.filters;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		 String path = requestContext.getUriInfo().getPath();
		 if(path.startsWith("/authentication")){
			 return;
		 }
		 else {
			 String authToken = requestContext.getHeaderString("auth-token");
			 if(authToken ==null || authToken.isEmpty()){
				 throw new WebApplicationException(Status.UNAUTHORIZED);
			 }
		 }
	}

}
