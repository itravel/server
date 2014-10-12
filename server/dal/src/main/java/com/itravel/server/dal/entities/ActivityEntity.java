package com.itravel.server.dal.entities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
public class ActivityEntity extends AbstractEventsEntity  {
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	};
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 出发地
	 */
	@Column(name="depart")
	private String depart;
	
	

	/**
	 * 目的地
	 */
	@Column(name="destination")
	private String destination;
	
	/**
	 * 活动景点
	 */
	@Column(name="scenery_spot")
	private String scenerySpot;

	
	/**
	 * 活动联系电话
	 */
	@Column(name="contact")
	private String contact;
	
	
	/**
	 * 活动组织者
	 */
	@ManyToOne
	@JoinColumn(name="organizer")
	private UserEntity organizer;
	

	
	/**
	 * 活动费用
	 */
	@Column(name="fee")
	private int fee;
	
	/**
	 * 旅行活动安排
	 */
	@OneToMany(cascade = CascadeType.ALL,mappedBy="activity")
	@OrderColumn(name="journey_order")
	private List<ActivityJourneyEntity> journey;

	/**
	 * 活动须知
	 */
	@Column(name="tips")
	private String tips;

	/**
	 * 活动可用时间
	 */

	@Column(name="available_time")
	private String availableTime;
	
	/**
	 * 活动持续时间
	 */
	@Column(name="duration")
	private int duration;
	
	public ActivityEntity() {
		this.journey = Lists.newArrayList();
		this.availableTime = "0";
		
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDestination() {
		return this.destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getFee() {
		return this.fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
	public String getScenerySpot() {
		return scenerySpot;
	}

	public void setScenerySpot(String scenerySpot) {
		this.scenerySpot = scenerySpot;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<ActivityJourneyEntity> getJourney() {
		return this.journey;
	}

	public void setJourney(List<ActivityJourneyEntity> journey) {
		this.journey = journey;
	}
	
	public ActivityJourneyEntity addActivityJourney(ActivityJourneyEntity activityJourney) {
		this.journey.add(activityJourney);
		activityJourney.setActivity(this);

		return activityJourney;
	}

	public ActivityJourneyEntity removeActivityJourney(ActivityJourneyEntity activityJourney) {
		this.journey.remove(activityJourney);
		activityJourney.setActivity(null);

		return activityJourney;
	}

	public UserEntity getOrganizer() {
		return organizer;
	}

	public void setOrganizer(UserEntity organizer) {
		this.organizer = organizer;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}




}