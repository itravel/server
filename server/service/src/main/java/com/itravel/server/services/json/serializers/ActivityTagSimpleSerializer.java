package com.itravel.server.services.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itravel.server.dal.entities.TagEntity;

public class ActivityTagSimpleSerializer extends StdSerializer<TagEntity>{

	public ActivityTagSimpleSerializer() {
		super(TagEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(TagEntity value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeStartObject();
		jgen.writeNumberField("id", value.getId());
		jgen.writeStringField("tag",value.getTag());
		jgen.writeEndObject();
	}

}
