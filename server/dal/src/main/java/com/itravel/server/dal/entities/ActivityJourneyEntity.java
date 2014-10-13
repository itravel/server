package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The persistent class for the activity_journey database table.
 * 
 */
@Entity
@Table(name="activity_journey")
@NamedQuery(name="ActivityJourneyEntity.findAll", query="SELECT a FROM ActivityJourneyEntity a")
public class ActivityJourneyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long id;

	
	private ActivityEntity activity;

	
	private String content;

	
	private String image;

	
	private String title;
	
	
	private int journeyOrder;

	public ActivityJourneyEntity() {
		this.content = "";
		this.image = "";
		this.title = "";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name="content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		if(content!=null)
			this.content = content;
	}
	@Column(name="image")
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		if(image!=null)
			this.image = image;
	}
	@Column(name="title")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		if(title!=null)
			this.title = title;
	}
	@ManyToOne
	@JoinColumn(name="activity_id")
	public ActivityEntity getActivity() {
		return activity;
	}

	public void setActivity(ActivityEntity activity) {
		this.activity = activity;
	}
	@Column(name="journey_order")
	public int getJourneyOrder() {
		return journeyOrder;
	}

	public void setJourneyOrder(int journeyOrder) {
		this.journeyOrder = journeyOrder;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}