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
		jgen.writeNumberField("id", Long.valueOf(value.getId()));
		jgen.writeStringField("title", value.getTitle());
		jgen.writeStringField("content", value.getContent());
		if(value.getImage()!=null&&!value.getImage().isEmpty()){
			jgen.writeStringField("image",this.wrapImage(value.getImage()));
		}
		jgen.writeEndObject();
	}
	private String wrapImage(String image){
		if(image == null||image.isEmpty()){
			return "";
		}
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
		listModule.addSerializer(new ActivityJourneySimpleSerializer());
		mapper.registerModule(listModule);
		ActivityEntity entity = new ActivityEntity();
		entity.getJourney().add(new ActivityJourneyEntity());
		String json = mapper.writeValueAsString(entity);
		System.out.println(json);
	}
	

}
