package com.itravel.server.dal.entities;

import java.io.Serializable;

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

	private String id;

	
	private String imageUri;
	

	private ActivityEntity activity;
	

	public ActivityImageEntity() {
	}
	@JsonIgnore
	@Id
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="image_uri")
	public String getImageUri() {
		return this.imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id",nullable=false)
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