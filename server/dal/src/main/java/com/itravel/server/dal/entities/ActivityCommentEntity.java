package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the activities_comments database table.
 * 
 */
@Entity
@Table(name="activities_comments")
@NamedQuery(name="ActivityCommentEntity.findAll", query="SELECT a FROM ActivityCommentEntity a")
public class ActivityCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private long activitiesId;
	private String comments;
	private UserEntity user;
	
	

	public ActivityCommentEntity() {
		
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}


	@Column(name="activities_id")
	public long getActivitiesId() {
		return this.activitiesId;
	}

	public void setActivitiesId(long activitiesId) {
		this.activitiesId = activitiesId;
	}

	@Column(name="comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public UserEntity getUser() {
		if(this.user == null)
			this.user = new UserEntity();
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	


	

}