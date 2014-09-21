package com.itravel.server.services.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityJourneyEntity;

public class ActivitySimpleSerializer extends StdSerializer<ActivityEntity> {

	public ActivitySimpleSerializer() {
		super(ActivityEntity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(ActivityEntity value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		// TODO Auto-generated method stub
		jgen.writeStartObject();
		jgen.writeNumberField("id",value.getId());
		jgen.writeStringField("title", value.getTitle());
		jgen.writeArrayFieldStart("images");
		jgen.writeString(wrapImage(value.getImage()));
		for(ActivityJourneyEntity journey : value.getJourney()){
			if(journey.getImage()!=null&&!journey.getImage().isEmpty()){
				jgen.writeString(wrapImage(journey.getImage()));
			}
		}
		jgen.writeEndArray();
		
		jgen.writeObjectField("start",value.getStartTime());
		jgen.writeObjectField("end",value.getEndTime());
		jgen.writeStringField("scenerySpot", value.getScenerySpot());
		jgen.writeEndObject();
		
	}
	
	private String wrapImage(String image){
		if (image.startsWith("/images")){
			return image;
		}
		else {
			return "/images/"+image;
		}
	}
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule listModule = new SimpleModule();
		listModule.addSerializer(new ActivitySimpleSerializer());
//		mapper.registerModule(listModule);
		ActivityEntity entity = new ActivityEntity();
		entity.getJourney().add(new ActivityJourneyEntity());
		String json = mapper.writeValueAsString(entity);
		System.out.println(json);
	}
}
