package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public abstract class AbstractEventsEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public AbstractEventsEntity() {
		super();
		Date now=new Date();
		this.gmt_create = now;
		this.gmt_modified = now;
		this.title = "";
		this.content = "";
		
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = IDENTITY)
	private long id;
	
	@Column(name="gmt_create")
	@Temporal(TIMESTAMP)
	@JsonIgnore
	private Date gmt_create;
	@JsonIgnore
	@Column(name="gmt_modified")
	@Temporal(TIMESTAMP)
	private Date gmt_modified;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="image")
	private String image;

	@Column(name="duration")
	private int duration;
	
	
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		if(this.image == null||this.image.isEmpty()){
			return "";
		}
		else if(this.image.startsWith("/images")){
			return image;
		}
		else {
			return "/images/"+image;
		}
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	


}
