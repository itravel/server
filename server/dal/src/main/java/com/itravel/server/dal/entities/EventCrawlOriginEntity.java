package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The persistent class for the event_craw_origin database table.
 * 
 */
@Entity
@Table(name="event_crawl_origin")
@NamedQuery(name="EventCrawOrigin.findAll", query="SELECT e FROM EventCrawlOriginEntity e")
public class EventCrawlOriginEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Lob
	private String abstractContent;

	private String eventAddress;

	private String eventDate;

	private String title;

	public EventCrawlOriginEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAbstractContent() {
		return this.abstractContent;
	}

	public void setAbstractContent(String abstractContent) {
		this.abstractContent = abstractContent;
	}

	public String getEventAddress() {
		return this.eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public String getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

}