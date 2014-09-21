package com.itravel.server.dal.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
	 * 活动联系人
	 */
	@Column(name="contact")
	private String contact;
	
	/**
	 * 活动推荐人
	 */
	@Column(name="recommender")
	private String recommender;
	
	/**
	 * 活动主办方
	 */
	@Column(name="sponsor")
	private String sponsor;
	
//	/**
//	 * 活动标签
//	 */
//	@ManyToMany
//	@JoinTable(
//		name="activity_tags",
//		joinColumns = @JoinColumn(name="activity_id"),
//		inverseJoinColumns = @JoinColumn(name="tag_id")
//	)
	
	
	/**
	 * 活动参与类型
	 */
	@Column(name="participation_type")
	private int participationType;

	
	/**
	 * 活动费用
	 */
	@Column(name="fee")
	private int fee;
	
	

	@Column(name="web")
	private String web;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="activity")
	private List<ActivityJourneyEntity> journey;

	@Column(name="tips")
	private String tips;

//	/**
//	 * 活动图片
//	 */
//	@OneToMany(cascade = CascadeType.ALL,mappedBy="activity")
////	@JoinColumn(name = "activity_id")
//	private List<ActivityImageEntity> images;
	
	public ActivityEntity() {
		this.journey = Lists.newArrayList();
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

	public int getParticipationType() {
		return this.participationType;
	}

	public void setParticipationType(int participationType) {
		this.participationType = participationType;
	}

	public String getRecommender() {
		return this.recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
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
		return journey;
	}

	public void setJourney(List<ActivityJourneyEntity> journey) {
		this.journey = journey;
	}




}