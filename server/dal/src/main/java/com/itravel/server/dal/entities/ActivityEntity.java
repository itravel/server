package com.itravel.server.dal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
public class ActivityEntity extends AbstractEventsEntity  {
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


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getContact()
	 */
	public String getContact() {
		return this.contact;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setContact(java.lang.String)
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getConvenience()
	 */
	public int getConvenience() {
		return this.convenience;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setConvenience(int)
	 */
	public void setConvenience(int convenience) {
		this.convenience = convenience;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getDestination()
	 */
	public String getDestination() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setDestination(java.lang.String)
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getFee()
	 */
	public int getFee() {
		return this.fee;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setFee(int)
	 */
	public void setFee(int fee) {
		this.fee = fee;
	}




	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getImages()
	 */
	public String getImages() {
		return this.images;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setImages(java.lang.String)
	 */
	public void setImages(String images) {
		this.images = images;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getOriginality()
	 */
	public int getOriginality() {
		return this.originality;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setOriginality(int)
	 */
	public void setOriginality(int originality) {
		this.originality = originality;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getParticipationType()
	 */
	public int getParticipationType() {
		return this.participationType;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setParticipationType(int)
	 */
	
	public void setParticipationType(int participationType) {
		this.participationType = participationType;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getPopularity()
	 */
	
	public int getPopularity() {
		return this.popularity;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setPopularity(int)
	 */
	
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getRecommender()
	 */
	
	public String getRecommender() {
		return this.recommender;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setRecommender(java.lang.String)
	 */
	
	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getScale()
	 */
	
	public int getScale() {
		return this.scale;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setScale(int)
	 */
	
	public void setScale(int scale) {
		this.scale = scale;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getSponsor()
	 */
	
	public String getSponsor() {
		return this.sponsor;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setSponsor(java.lang.String)
	 */
	
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getTags()
	 */
	
	public String getTags() {
		return this.tags;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setTags(java.lang.String)
	 */
	
	public void setTags(String tags) {
		this.tags = tags;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getWeb()
	 */
	
	public String getWeb() {
		return web;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setWeb(java.lang.String)
	 */
	
	public void setWeb(String web) {
		this.web = web;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getScenerySpot()
	 */
	
	public String getScenerySpot() {
		return scenerySpot;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setScenerySpot(java.lang.String)
	 */
	
	public void setScenerySpot(String scenerySpot) {
		this.scenerySpot = scenerySpot;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getDepart()
	 */
	
	public String getDepart() {
		return depart;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setDepart(java.lang.String)
	 */
	
	public void setDepart(String depart) {
		this.depart = depart;
	}


}