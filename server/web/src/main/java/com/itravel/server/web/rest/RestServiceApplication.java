package com.itravel.server.web.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.itravel.server.services.rest.filters.AuthenticationFilter;

@ApplicationPath( "services" )
public class RestServiceApplication extends ResourceConfig {
	public RestServiceApplication() {
		
        packages("com.itravel.server.services.rest","com.itravel.admin.services.rest")
//        .register(AuthenticationFilter.class)
        .register(JacksonFeature.class)
        .register(MultiPartFeature.class);
    }
}
