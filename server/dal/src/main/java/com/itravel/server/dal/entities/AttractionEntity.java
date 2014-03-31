package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.itravel.server.interfaces.dal.IAttractions;


/**
 * The persistent class for the attractions database table.
 * 
 */
@Entity
@Table(name="attractions")
@NamedQueries(value = { 
		@NamedQuery(name="AttractionEntity.findAll", query="SELECT a FROM AttractionEntity a"),
		@NamedQuery(name = "AttractionEntity.findByCity", query = "SELECT a FROM AttractionEntity a where a.cityCode = :cityCode ") 
})
public class AttractionEntity implements Serializable, IAttractions {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String address;

	@Column(name="city_code")
	private int cityCode;

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
	public Collection<String> getPictures() {
		if(this.pictures == null || this.pictures.isEmpty()){
			return Sets.newHashSet();
		}
		return Sets.newHashSet(this.pictures.split(","));
	}

	/* (non-Javadoc)
	 * @see com.itravel.server.dal.entities.IAttractions#setPictures(java.lang.String)
	 */
	@Override
	public void setPictures(Collection<String> pictures) {
		this.pictures = Joiner.on(",").join(pictures);
	}
	
	

	@Override
	public void addPicture(String picture) {
		// TODO Auto-generated method stub
		Collection<String> pics = this.getPictures();
		pics.add(picture);
		this.pictures = Joiner.on(",").join(pics);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}