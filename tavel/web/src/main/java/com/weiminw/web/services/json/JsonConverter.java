package com.weiminw.web.services.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonConverter {
	static final ObjectMapper objectMapper = new ObjectMapper();
	public static String convertToJson(Object object){
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
