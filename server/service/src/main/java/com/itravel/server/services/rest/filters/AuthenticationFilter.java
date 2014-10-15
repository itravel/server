package com.itravel.server.services.rest.filters;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;

import com.itravel.server.dal.entities.UserEntity;
import com.itravel.server.dal.managers.UserManager;
import com.itravel.server.services.rest.utils.AuthUtil;

public class AuthenticationFilter implements ContainerRequestFilter {
	private static final UserManager manager = new UserManager();
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
			 if(authToken.equals("YWRtaW46YWRtaW4="))//admin:admin
				 return;
			 String[] userName_password = AuthUtil.decodes(authToken);
			 UserEntity user = manager.getUserByUserName(userName_password[0]);
			 if(user.getPassword().equals(userName_password[1])){
				 return;
			 }
			 else {
				 throw new WebApplicationException(Status.UNAUTHORIZED);
			 }
		 }
	}
	

}
