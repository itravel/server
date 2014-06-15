package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIME;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

/**
 * Entity implementation class for Entity: UpcomingEvents
 *
 */

@MappedSuperclass
public abstract class UpcomingEvents implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public UpcomingEvents() {
		super();
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	
	@Column(name="gmt_create")
	@Temporal(TIMESTAMP)
	private Date gmt_create;
	
	@Column(name="gmt_modified")
	@Temporal(TIMESTAMP)
	private Date gmt_modified;
	
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
	
	@Column(name="longitude")
	private double longitude;
	
	@Column(name="latitude")
	private double latitude;
	
	@Column(name="address")
	private String address;
	
	@Column(name = "images",length=4096)
	private String images;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	
	
}
