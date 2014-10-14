package com.itravel.server.services.rest.filters;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.codec.binary.Base64;

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
//			 System.out.println(this.decodesAuth(authToken));
			 if(authToken ==null || authToken.isEmpty()){
				 throw new WebApplicationException(Status.UNAUTHORIZED);
//				 return ;
			 }
		 }
	}
	
	private String decodesAuth(String authToken){
		byte[] b=authToken.getBytes();  
        Base64 base64=new Base64();  
        b=base64.decode(b);  
        String s=new String(b);  
        return s;  
	}
	
	public static void main(String[] args) {
		byte[] b="william:12345".getBytes();  
        Base64 base64=new Base64();  
        b=base64.encode(b);  
        String s=new String(b);  
        System.out.println(s);
	}

}
