package com.itravel.server.dal.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;


/**
 * The persistent class for the activities_user database table.
 * 
 */
@Entity
@Table(name="activities_user")
@NamedQueries(value = { 
		@NamedQuery(name="ActivitiesUserEntity.findAll", query="SELECT a FROM ActivitiesUserEntity a"),
		@NamedQuery(name="ActivitiesUserEntity.findByActivitiesId", query="SELECT a FROM ActivitiesUserEntity a where a.activitiesId = :activitiesId")
})




public class ActivitiesUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="activities_id")
	private int activitiesId;

	@Column(name="user_id")
	private int userId;
	
	

	public ActivitiesUserEntity() {
		
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(int activitiesId) {
		this.activitiesId = activitiesId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}