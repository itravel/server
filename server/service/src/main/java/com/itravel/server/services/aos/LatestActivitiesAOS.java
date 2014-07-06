package com.itravel.server.services.aos;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

import org.apache.commons.lang3.time.DateUtils;

import com.itravel.server.dal.entities.ActivitiesEntity;

public class LatestActivitiesAOS {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="abstract_content")
	private String abstractContent;
	
	@Column(name="start_time")
	@Temporal(DATE)
	private Date startTime;
	
	@Column(name="end_time")
	@Temporal(DATE)
	private Date endTime;
	
	@Column(name="city_code")
	private int cityCode;
	
	@Column(name="fee")
	private long fee;
	
	@Column(name = "images",length=4096)
	private String images;

	@Column(name="address")
	private String address;
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="activity_type",length=256)
	private int type;
	
	@Column(name="scale")
	private int scale;
	
	@Column(name="tags",length=256)
	private String tags;
	
	@Column(name="convenience")
	private int convenience;
	
	@Column(name="interesting_rate")
	private int interestingRate;
	
	@Column(name="popularity")
	private int popularity;
	
	@Column(name="gmt_create")
	@Temporal(TIMESTAMP)
	private Date gmt_create;
	
	@Column(name="gmt_modified")
	@Temporal(TIMESTAMP)
	private Date gmt_modified;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAbstractContent() {
		return abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public int getCityCode() {
		return cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	
	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}
	
	public String getImage() {
		return images;
	}

	public void setImage(String images) {
		this.images = images;
	}
	
	public String getAdress() {
		return address;
	}

	public void setAdress(String address) {
		this.address = address;
	}


	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}
	
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public int getConvenience() {
		return convenience;
	}

	public void setConvenience(int convenience) {
		this.convenience = convenience;
	}
	
	public int getInterestingRate() {
		return interestingRate;
	}

	public void setInterestingRate(int interestingRate) {
		this.interestingRate = interestingRate;
	}
	
	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
	public Date getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}

	public Date getGmt_modified() {
		return gmt_modified;
	}

	public void setGmt_modified(Date gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	
	public int getStatus(){
		Date now = new Date();
		Date now7Later = new Date();
		now7Later = DateUtils.addDays(now, 7);
		int status = 0x00;
		long nowNum = now.getTime();
		long now7LaterNum = now7Later.getTime();
		long startTimeNum = this.startTime.getTime();
		long endTimeNum = this.endTime.getTime();
		if(endTimeNum < nowNum){
			status = 0x10;
			//已经结束
		}else if( startTimeNum > now7LaterNum){
			status = 0x20;
			//计划中，未开始
		}else{
			status = 0x30;
			//todo
		}
		return status;
	}
	public LatestActivitiesAOS(ActivitiesEntity entity){
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.abstractContent = entity.getAbstractContent();
		this.startTime = entity.getStartTime();
		this.endTime = entity.getEndTime();
		this.cityCode = entity.getCityCode();
		this.fee = entity.getFee();
		this.images = entity.getImages();
		this.address = entity.getAddress();
		this.longitude = entity.getLongitude();
		this.latitude = entity.getLatitude();
		this.type = entity.getType();
		this.scale = entity.getScale();
		this.tags = entity.getTags();
		this.convenience = entity.getConvenience();
		this.interestingRate = entity.getInterestingRate();
		this.popularity = entity.getPopularity();
		this.gmt_create = entity.getGmt_create();
		this.gmt_modified = entity.getGmt_modified();
	}

}
