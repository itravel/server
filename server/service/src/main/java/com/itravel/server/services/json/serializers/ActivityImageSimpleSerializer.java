package com.itravel.server.services.json.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityImageEntity;

public class ActivityImageSimpleSerializer extends StdSerializer<ActivityImageEntity> {

	public ActivityImageSimpleSerializer() {
		super(ActivityImageEntity.class);
	}

	

	@Override
	public void serialize(ActivityImageEntity arg0, JsonGenerator jgen,
			SerializerProvider srp) throws IOException,
			JsonProcessingException {
		jgen.writeString(arg0.getImageUri());
	
		
	}

	

}
