package com.itravel.server.services.json.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itravel.server.dal.entities.ActivityEntity;

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
		if(value.getImages().size()>0){
			String imageUri = value.getImages().get(0).getImageUri();
			
			if(imageUri!=null&&!imageUri.startsWith("/images")){
				jgen.writeStringField("image", "/images/"+imageUri);
			}
		}
		jgen.writeObjectField("start",value.getStartTime());
		jgen.writeObjectField("end",value.getEndTime());
		jgen.writeStringField("scenerySpot", value.getScenerySpot());
		jgen.writeEndObject();
		
	}

}
