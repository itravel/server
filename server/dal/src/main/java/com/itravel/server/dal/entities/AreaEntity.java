package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@Table(name="area")
@NamedQuery(name="AreaEntity.findAll", query="SELECT a FROM AreaEntity a")
public class AreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String abbName;
	private String name;
	private long parentId;
	private byte type;
	private String description;
	private String comments;

	public AreaEntity() {
		this.abbName = "";
		this.name = "";
		
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="abb_name")
	public String getAbbName() {
		return this.abbName;
	}

	public void setAbbName(String abbName) {
		if(this.abbName!=null)
			this.abbName = abbName;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if(name != null)
			this.name = name;
	}


	@Column(name="parent_id")
	public long getParentId() {
		return this.parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}


	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "AreaEntity [id=" + id + ", abbName=" + abbName + ", name="
				+ name + ", parentId=" + parentId + ", type=" + type + "]";
	}
	
	

}