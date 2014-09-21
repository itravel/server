package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigInteger;


/**
 * The persistent class for the activity_journey database table.
 * 
 */
@Entity
@Table(name="activity_journey")
@NamedQuery(name="ActivityJourneyEntity.findAll", query="SELECT a FROM ActivityJourneyEntity a")
public class ActivityJourneyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private ActivityEntity activity;

	@Column(name="content")
	private String content;

	@Column(name="image")
	private String image;

	@Column(name="title")
	private String title;

	public ActivityJourneyEntity() {
		this.content = "";
		this.image = "";
		this.title = "";
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		if(content!=null)
			this.content = content;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		if(image!=null)
			this.image = image;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		if(title!=null)
			this.title = title;
	}

	public ActivityEntity getActivity() {
		return activity;
	}

	public void setActivity(ActivityEntity activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}