package com.itravel.server.web.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.itravel.server.services.rest.HomeInfoStream;
import com.itravel.server.services.rest.Activities;
import com.itravel.server.services.rest.Users;

@ApplicationPath( "services" )
public class RestServiceApplication extends ResourceConfig {
	public RestServiceApplication() {
		
//        packages("com.itravel.server.services.rest")
		this.register(Activities.class)
		.register(HomeInfoStream.class)
		.register(Users.class)
        .register(JacksonFeature.class)
        .register(MultiPartFeature.class);
    }
}
