package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.itravel.server.client.dos.IActivityObject;

import java.util.Date;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
public class ActivityEntity extends AbstractEventsEntity implements IActivityObject {
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
	@Override
	public String getContact() {
		return this.contact;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setContact(java.lang.String)
	 */
	@Override
	public void setContact(String contact) {
		this.contact = contact;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getConvenience()
	 */
	@Override
	public int getConvenience() {
		return this.convenience;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setConvenience(int)
	 */
	@Override
	public void setConvenience(int convenience) {
		this.convenience = convenience;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getDestination()
	 */
	@Override
	public String getDestination() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setDestination(java.lang.String)
	 */
	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getFee()
	 */
	@Override
	public int getFee() {
		return this.fee;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setFee(int)
	 */
	@Override
	public void setFee(int fee) {
		this.fee = fee;
	}




	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getImages()
	 */
	@Override
	public String getImages() {
		return this.images;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setImages(java.lang.String)
	 */
	@Override
	public void setImages(String images) {
		this.images = images;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getOriginality()
	 */
	@Override
	public int getOriginality() {
		return this.originality;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setOriginality(int)
	 */
	@Override
	public void setOriginality(int originality) {
		this.originality = originality;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getParticipationType()
	 */
	@Override
	public int getParticipationType() {
		return this.participationType;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setParticipationType(int)
	 */
	@Override
	public void setParticipationType(int participationType) {
		this.participationType = participationType;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getPopularity()
	 */
	@Override
	public int getPopularity() {
		return this.popularity;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setPopularity(int)
	 */
	@Override
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getRecommender()
	 */
	@Override
	public String getRecommender() {
		return this.recommender;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setRecommender(java.lang.String)
	 */
	@Override
	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getScale()
	 */
	@Override
	public int getScale() {
		return this.scale;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setScale(int)
	 */
	@Override
	public void setScale(int scale) {
		this.scale = scale;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getSponsor()
	 */
	@Override
	public String getSponsor() {
		return this.sponsor;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setSponsor(java.lang.String)
	 */
	@Override
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getTags()
	 */
	@Override
	public String getTags() {
		return this.tags;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setTags(java.lang.String)
	 */
	@Override
	public void setTags(String tags) {
		this.tags = tags;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getWeb()
	 */
	@Override
	public String getWeb() {
		return web;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setWeb(java.lang.String)
	 */
	@Override
	public void setWeb(String web) {
		this.web = web;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getScenerySpot()
	 */
	@Override
	public String getScenerySpot() {
		return scenerySpot;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setScenerySpot(java.lang.String)
	 */
	@Override
	public void setScenerySpot(String scenerySpot) {
		this.scenerySpot = scenerySpot;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#getDepart()
	 */
	@Override
	public String getDepart() {
		return depart;
	}


	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IActivityObject#setDepart(java.lang.String)
	 */
	@Override
	public void setDepart(String depart) {
		this.depart = depart;
	}


}