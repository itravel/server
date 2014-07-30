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
	 * 出发城市
	 */
	@Column(name="from_city")
	private String fromCity;
	
	/**
	 * 出发地址
	 */
	@Column(name="from_address")
	private String fromAddress;

	/**
	 * 目的地城市
	 */
	@Column(name="destination_city")
	private String destinationCity;
	
	/**
	 * 目的地地址
	 */
	@Column(name="destination_address")
	private String destinationAddress;

	

	/**
	 * 目的地纬度
	 */
	@Column(name="destination_latitude")
	private double destinationLatitude;

	/**
	 * 目的地经度
	 */
	@Column(name="destination_longitude")
	private double destinationLongitude;

	
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
		return this.destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationCity() {
		return this.destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public double getDestinationLatitude() {
		return this.destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return this.destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}


	public int getFee() {
		return this.fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromCity() {
		return this.fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
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


}