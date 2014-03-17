package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.itravel.server.interfaces.dal.ITravelNote;


/**
 * The persistent class for the travel_notes database table.
 * 
 */
@Entity
@Table(name="travel_notes")
@NamedQuery(name="TravelNoteEntity.findAll", query="SELECT t FROM TravelNoteEntity t")
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
	private int userId;

	@Column(name="user_name")
	private String userName;

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

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#getPictures()
	 */
	@Override
	public String getPictures() {
		return this.pictures;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setPictures(java.lang.String)
	 */
	@Override
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

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
	public int getUserId() {
		return this.userId;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.ITravelNote#setUserId(int)
	 */
	@Override
	public void setUserId(int userId) {
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

}