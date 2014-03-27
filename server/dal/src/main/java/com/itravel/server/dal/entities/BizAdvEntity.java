package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * The persistent class for the biz_adv database table.
 * 
 */
@Entity
@Table(name="biz_adv")
@NamedQuery(name="BizAdvEntity.findAll", query="SELECT b FROM BizAdvEntity b")
public class BizAdvEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="businesses_id")
	private int businessesId;

	@Column(name="businesses_name")
	private String businessesName;

	private byte category;

	private String description;

	private String name;

	private String pictures;

	public BizAdvEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusinessesId() {
		return this.businessesId;
	}

	public void setBusinessesId(int businessesId) {
		this.businessesId = businessesId;
	}

	public String getBusinessesName() {
		return this.businessesName;
	}

	public void setBusinessesName(String businessesName) {
		this.businessesName = businessesName;
	}

	public byte getCategory() {
		return this.category;
	}

	public void setCategory(byte category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictures() {
		return this.pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

}