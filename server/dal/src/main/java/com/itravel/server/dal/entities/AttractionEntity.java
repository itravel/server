package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.itravel.server.interfaces.dal.IAttractions;


/**
 * The persistent class for the attractions database table.
 * 
 */
@Entity
@Table(name="attractions")
@NamedQuery(name="AttractionEntity.findAll", query="SELECT a FROM AttractionEntity a")
public class AttractionEntity implements Serializable, IAttractions {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String address;

	@Column(name="city_code")
	private int cityCode;

	@Column(name="city_name")
	private String cityName;

	private String description;

	private double latitude;

	private double longitude;

	private String name;

	private String pictures;

	public AttractionEntity() {
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getId()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setId(int)
	 */
	@Override
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getAddress()
	 */
	@Override
	public String getAddress() {
		return this.address;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setAddress(java.lang.String)
	 */
	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getCityCode()
	 */
	@Override
	public int getCityCode() {
		return this.cityCode;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setCityCode(int)
	 */
	@Override
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getCityName()
	 */
	@Override
	public String getCityName() {
		return this.cityName;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setCityName(java.lang.String)
	 */
	@Override
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getDescription()
	 */
	@Override
	public String getDescription() {
		return this.description;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getLatitude()
	 */
	@Override
	public double getLatitude() {
		return this.latitude;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setLatitude(double)
	 */
	@Override
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getLongitude()
	 */
	@Override
	public double getLongitude() {
		return this.longitude;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setLongitude(double)
	 */
	@Override
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#getPictures()
	 */
	@Override
	public String getPictures() {
		return this.pictures;
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setPictures(java.lang.String)
	 */
	@Override
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}