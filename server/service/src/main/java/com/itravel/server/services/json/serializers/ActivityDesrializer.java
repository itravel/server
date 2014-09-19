package com.itravel.server.services.json.serializers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.ActivityImageEntity;
import com.itravel.server.dal.entities.TagEntity;

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
		entity.setConvenience(node.get("convenience").intValue());
		entity.setDepart(node.get("depart").textValue());
		entity.setDestination(node.get("destination").textValue());
		try {
			entity.setEndTime(DateUtils.parseDate(node.get("endTime").textValue(),"yyyy-MM-dd"));
			entity.setStartTime(DateUtils.parseDate(node.get("startTime").textValue(),"yyyy-MM-dd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entity.setFee(node.get("fee").intValue());
		entity.setJourney(node.get("journey").textValue());
		entity.setOriginality(node.get("originality").intValue());
		entity.setParticipationType(node.get("participationType").intValue());
		entity.setPopularity(node.get("popularity").intValue());
		entity.setRecommender(node.get("recommender").textValue());
		entity.setScale(node.get("scale").intValue());
		entity.setScenerySpot(node.get("scenerySpot").textValue());
		entity.setTips(node.get("tips").textValue());
		entity.setSponsor(node.get("sponsor").textValue());
		entity.setWeb(node.get("web").textValue());
		
		Iterator<JsonNode> iter = node.get("images").elements();
		while(iter.hasNext()){
			JsonNode imageNode = iter.next();
			ActivityImageEntity imageEntity = new ActivityImageEntity();
			imageEntity.setEntity(entity);
			imageEntity.setImageUri(imageNode.textValue());
			entity.getImages().add(imageEntity);
		}
		Iterator<JsonNode> _tagIter = node.get("tags").elements();
		
		while(_tagIter.hasNext()){
			JsonNode tagNode = _tagIter.next();
			TagEntity tagEntity = new TagEntity();
			tagEntity.setId(tagNode.get("id").longValue());
			tagEntity.setTag(tagNode.get("tag").textValue());
			entity.getTags().add(tagEntity);
		}
		
		return entity;
	}
	
	



}
