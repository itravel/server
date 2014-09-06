package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the activity_images database table.
 * 
 */
@Entity
@Table(name="activity_images")
@NamedQuery(name="ActivityImageEntity.findAll", query="SELECT a FROM ActivityImageEntity a")
public class ActivityImageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="activity_id")
	private BigInteger activityId;

	@Column(name="image_uri")
	private String imageUri;

	public ActivityImageEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getActivityId() {
		return this.activityId;
	}

	public void setActivityId(BigInteger activityId) {
		this.activityId = activityId;
	}

	public String getImageUri() {
		return this.imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

}