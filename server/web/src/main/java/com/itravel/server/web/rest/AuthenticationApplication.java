package com.itravel.server.web.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.itravel.server.services.rest.Authentication;
import com.itravel.server.services.rest.Users;
@ApplicationPath( "auth" )
public class AuthenticationApplication extends ResourceConfig {
	
	
public AuthenticationApplication() {
		this.register(Authentication.class)
        .register(JacksonFeature.class)
        .register(MultiPartFeature.class);
    }
}
