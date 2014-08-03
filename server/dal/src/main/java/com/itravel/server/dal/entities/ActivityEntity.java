package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
public class ActivityEntity extends AbstractEventsEntity {
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 出发地
	 */
	@Column(name="from")
	private String from;
	
	

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
	 * 活动图片
	 */
	@Column(name="images")
	private String images;
	
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
	
	/**
	 * 活动标签
	 */
	@Column(name="tags")
	private String tags;
	
	
	/**
	 * 活动参与类型
	 */
	@Column(name="participation_type")
	private int participationType;

	/**
	 * 活动规模
	 */
	@Column(name="scale")
	private int scale;
	
	/**
	 * 活动费用
	 */
	@Column(name="fee")
	private int fee;
	
	/**
	 * 活动名气
	 */
	@Column(name="popularity")
	private int popularity;

	/**
	 * 活动交通方便度
	 */
	@Column(name="convenience")
	private int convenience;
	

	/**
	 * 活动独特性
	 */
	@Column(name="originality")
	private int originality;

	@Column(name="web")
	private String web;

	public ActivityEntity() {
	}


	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


	public int getConvenience() {
		return this.convenience;
	}

	public void setConvenience(int convenience) {
		this.convenience = convenience;
	}

	public String getDestinationAddress() {
		return this.scenerySpot;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.scenerySpot = destinationAddress;
	}

	public String getDestinationCity() {
		return this.destination;
	}

	public void setDestinationCity(String destinationCity) {
		this.destination = destinationCity;
	}

	public int getFee() {
		return this.fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}


	public String getFrom() {
		return this.from;
	}

	public void setFrom(String from) {
		this.from = from;
	}


	public String getImages() {
		return this.images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getOriginality() {
		return this.originality;
	}

	public void setOriginality(int originality) {
		this.originality = originality;
	}

	public int getParticipationType() {
		return this.participationType;
	}

	public void setParticipationType(int participationType) {
		this.participationType = participationType;
	}

	public int getPopularity() {
		return this.popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public String getRecommender() {
		return this.recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public int getScale() {
		return this.scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}


	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}


	public String getWeb() {
		return web;
	}


	public void setWeb(String web) {
		this.web = web;
	}


}