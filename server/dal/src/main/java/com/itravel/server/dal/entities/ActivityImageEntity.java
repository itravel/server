package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * The persistent class for the activity_images database table.
 * 
 */
@Entity
@Table(name="activity_images")

public class ActivityImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@Id
	private String id;
//	@JsonIgnore
//	@Column(name="activity_id")
//	private long activityId;

	@Column(name="image_uri")
	private String imageUri;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id",nullable=false)
	private ActivityEntity activity;
	

	public ActivityImageEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public long getActivityId() {
//		return this.activityId;
//	}
//
//	public void setActivityId(long activityId) {
//		this.activityId = activityId;
//	}

	public String getImageUri() {
		return this.imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public ActivityEntity getEntity() {
		return activity;
	}

	public void setEntity(ActivityEntity entity) {
		this.activity = entity;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String value = mapper.writeValueAsString(new ActivityImageEntity());
		System.out.println(value);
	}
}