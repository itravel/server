package com.itravel.server.services.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.itravel.server.dal.entities.ActivityImageEntity;

public class ActivityImageDeserializer extends StdDeserializer<ActivityImageEntity> {

	public ActivityImageDeserializer() {
		super(String.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActivityImageEntity deserialize(JsonParser jp,
			DeserializationContext ctxt) throws IOException,
			JsonProcessingException {
		// TODO Auto-generated method stub
		ActivityImageEntity entity = new ActivityImageEntity();
		entity.setImageUri(jp.getValueAsString());
		
		return entity;
	}

}
