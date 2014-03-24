package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.itravel.server.interfaces.dal.ITravelNote;


/**
 * The persistent class for the travel_notes database table.
 * 
 */
@Entity
@Table(name="travel_notes")
@NamedQueries(value = { 
		@NamedQuery(name="TravelNoteEntity.findAll", query="SELECT t FROM TravelNoteEntity t"),
		@NamedQuery(name = "TravelNoteEntity.findByTimeRange", query = "SELECT t FROM TravelNoteEntity t where t.created between :from and :to"),
		
})
public class TravelNoteEntity implements Serializable,ITravelNote {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private int city;

	private String content;

	private String destination;

	private String pictures;

	private int province;

	private String title;

	@Column(name="user_avatar")
	private String userAvatar;

	@Column(name="user_id")
	private long userId;

	@Column(name="user_name")
	private String userName;

	@Column(name="latitude")
	private double latitude;

	@Column(name="longitude")
	private double longitude;
	
	@Column(name = "created")
	@Temporal(TemporalType.DATE)
	private Date created;
	
	@Column(name = "modified")
	@Temporal(TemporalType.DATE)
	private Date modified;
	
	public TravelNoteEntity() {
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getId()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setId(int)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getCity()
	 */
	@Override
	public int getCity() {
		return this.city;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setCity(int)
	 */
	@Override
	public void setCity(int city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getContent()
	 */
	@Override
	public String getContent() {
		return this.content;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setContent(java.lang.String)
	 */
	@Override
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getDestination()
	 */
	@Override
	public String getDestination() {
		return this.destination;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setDestination(java.lang.String)
	 */
	@Override
	public void setDestination(String destination) {
		this.destination = destination;
	}

//	/* (non-Javadoc)
//	 * @see com.itravel.server.dal.entities.ITravelNote#getPictures()
//	 */
//	@Override
//	public String getPictures() {
//		return this.pictures;
//	}
//
//	/* (non-Javadoc)
//	 * @see com.itravel.server.dal.entities.ITravelNote#setPictures(java.lang.String)
//	 */
//	@Override
//	public void setPictures(String pictures) {
//		this.pictures = pictures;
//	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getProvince()
	 */
	@Override
	public int getProvince() {
		return this.province;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setProvince(int)
	 */
	@Override
	public void setProvince(int province) {
		this.province = province;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getTitle()
	 */
	@Override
	public String getTitle() {
		return this.title;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getUserAvatar()
	 */
	@Override
	public String getUserAvatar() {
		return this.userAvatar;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setUserAvatar(java.lang.String)
	 */
	@Override
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getUserId()
	 */
	@Override
	public long getUserId() {
		return this.userId;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setUserId(int)
	 */
	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getUserName()
	 */
	@Override
	public String getUserName() {
		return this.userName;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}


	@Override
	public void setLatitude(double latitude) {
		// TODO Auto-generated method stub
		this.latitude = latitude;
	}

	@Override
	public void setLongitude(double longitude) {
		// TODO Auto-generated method stub
		this.longitude = longitude;
	}
	
	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getPictures()
	 */
	@Override
	public Collection<String> getPictures() {
		if(this.pictures == null){
			return ImmutableSet.of();
		}
		return Sets.newHashSet(this.pictures.split(","));
	}

	@Override
	public void setPictures(String... pictures) {
		// TODO Auto-generated method stub
		this.pictures = Joiner.on(",").join(pictures);
	}

	@Override
	public void addPicture(String picture) {
		// TODO Auto-generated method stub
		Collection<String> pictures = this.getPictures();
		pictures.add(picture);
		this.pictures = Joiner.on(",").join(pictures);
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

}