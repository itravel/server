package com.itravel.server.services.rest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonFactory {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static ObjectMapper getMapper(){
		return mapper;
	}
}
