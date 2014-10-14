package com.itravel.server.services.aos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.itravel.server.dal.entities.AreaEntity;

public class AreaBean {
	private AreaEntity entity;
	public AreaBean() {
		this.entity = new AreaEntity();
	}
	public AreaBean(AreaEntity destination) {
		this.entity = destination;
	}
	
	

	public long getId() {
		return this.entity.getId();
	}

	public void setId(long id) {
		this.entity.setId(id);
	}


	public String getAbbName() {
		return this.entity.getAbbName();
	}

	public void setAbbName(String abbName) {
		this.entity.setAbbName(abbName);
	}


	public String getName() {
		return this.entity.getName();
	}

	public void setName(String name) {
		this.entity.setName(name);
	}

	public byte getType() {
		return this.entity.getType();
	}

	public void setType(byte type) {
		this.entity.setType(type);
	}


}
