package com.itravel.server.services.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityJourneyEntity;

public class ActivityJourneySimpleSerializer extends StdSerializer<ActivityJourneyEntity> {

	public ActivityJourneySimpleSerializer() {
		super(ActivityJourneyEntity.class);
	}

	

	@Override
	public void serialize(ActivityJourneyEntity value, JsonGenerator jgen,
			SerializerProvider srp) throws IOException,
			JsonProcessingException {
	
		jgen.writeStartObject();
		jgen.writeStringField("id", String.valueOf(value.getId()));
		jgen.writeStringField("title", value.getTitle());
		jgen.writeStringField("content", value.getContent());
		if(value.getImage()!=null&&!value.getImage().isEmpty()){
			
			jgen.writeString(value.getImage());
		}
		jgen.writeEndObject();
	}

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule listModule = new SimpleModule();
		listModule.addSerializer(new ActivityJourneySimpleSerializer());
		mapper.registerModule(listModule);
		ActivityEntity entity = new ActivityEntity();
		entity.getJourney().add(new ActivityJourneyEntity());
		String json = mapper.writeValueAsString(entity);
		System.out.println(json);
	}
	

}
