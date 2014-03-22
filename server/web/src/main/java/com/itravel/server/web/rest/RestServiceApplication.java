package com.itravel.server.web.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath( "services" )
public class RestServiceApplication extends ResourceConfig {
	public RestServiceApplication() {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
        packages("com.itravel.server.services.rest")
        .register(feature)
        .register(JacksonFeature.class)
        .register(MultiPartFeature.class);
    }
}
