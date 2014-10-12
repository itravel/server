package com.itravel.server.services.json.serializers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import jersey.repackaged.com.google.common.base.Optional;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityImageEntity;
import com.itravel.server.dal.entities.ActivityJourneyEntity;
import com.itravel.server.dal.entities.TagEntity;
import com.itravel.server.dal.entities.UserEntity;
import com.sun.research.ws.wadl.Option;

public class ActivityDesrializer extends StdDeserializer<ActivityEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628552199597579480L;

	public ActivityDesrializer() {
		super(String.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActivityEntity deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ActivityEntity entity = new ActivityEntity();
		JsonNode node = jp.getCodec().readTree(jp);
		entity.setId(node.get("id").longValue());
		entity.setTitle(node.get("title").textValue());
		entity.setContent(node.get("content").textValue());
		entity.setContact(node.get("contact").textValue());
		entity.setDepart(node.get("depart").textValue());
		entity.setDestination(node.get("destination").textValue());
		
		entity.setFee(node.get("fee").intValue());
		entity.setScenerySpot(node.get("scenerySpot").textValue());
		entity.setTips(node.get("tips").textValue());
		entity.setImage(node.get("image").textValue());
		entity.setAvailableTime("0");
		entity.setDuration(node.get("duration").intValue());
		UserEntity organizer = new UserEntity();
		organizer.setId(node.get("organizer").longValue());
		entity.setOrganizer(organizer);
		
		Iterator<JsonNode> iter = node.get("journey").elements();
		while(iter.hasNext()){
			JsonNode journeyNode = iter.next();
			ActivityJourneyEntity journeyEntity = new ActivityJourneyEntity();
			journeyEntity.setActivity(entity);
			if(journeyNode.get("id")!=null){
				System.out.println(journeyNode.get("id").longValue());
				journeyEntity.setId(journeyNode.get("id").longValue());
			}
			if(journeyNode.get("image")!=null){
				journeyEntity.setImage(journeyNode.get("image").textValue());
			}
			if(journeyNode.get("title")!=null){
				
				journeyEntity.setTitle(journeyNode.get("title").textValue());
			}
			if(journeyNode.get("content")!=null){
				
				journeyEntity.setContent(journeyNode.get("content").textValue());
			}
			entity.getJourney().add(journeyEntity);
		}
		
		return entity;
	}
	
	



}
