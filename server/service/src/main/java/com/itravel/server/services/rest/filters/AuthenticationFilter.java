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
		// TODO Auto-generated method stub
		
		 final SecurityContext securityContext =
                 requestContext.getSecurityContext();
		 
//		 System.out.println(requestContext.getUriInfo().getPath());
//		 String path = requestContext.getUriInfo().getPath();
//		 if(!path.startsWith("/users/login")){
//			 String auth = requestContext.getHeaderString("Cookie");
//			 if(auth==null||auth.trim().isEmpty()){
//				 throw new WebApplicationException(Status.UNAUTHORIZED);
//			 }
//		 }
		 
		 System.out.println(requestContext.getHeaderString("authorization"));;
//		 requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
	}

}
