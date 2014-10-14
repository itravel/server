package com.itravel.server.services.aos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.ActivityCommentEntity;
import com.itravel.server.dal.entities.UserEntity;

public class ActivityCommentBean {

	private ActivityCommentEntity entity;
	
	public ActivityCommentBean() {
		// TODO Auto-generated constructor stub
		this.entity = new ActivityCommentEntity();
	}
	public ActivityCommentBean( ActivityCommentEntity entity){
		this.entity = entity;
	}
	
	public long getId() {
		return this.entity.getId();
	}

	public void setId(long id) {
		this.entity.setId(id);
	}


	public long getActivitiesId() {
		return this.entity.getActivitiesId();
	}

	public void setActivitiesId(long activitiesId) {
		this.entity.setActivitiesId(activitiesId);
	}

	public String getComments() {
		return this.entity.getComments();
	}

	public void setComments(String comments) {
		this.entity.setComments(comments);
	}

	public UserBean getUser() {
		return new UserBean(this.entity.getUser());
	}


	public void setUser(long user_id) {
		UserEntity user = new UserEntity();
		user.setId(user_id);
		this.entity.setUser(user);
	}
	
	public static final Function<ActivityCommentEntity,ActivityCommentBean> FROM_ENTITY = new Function<ActivityCommentEntity,ActivityCommentBean>(){

		@Override
		public ActivityCommentBean apply(ActivityCommentEntity input) {
			
			return new ActivityCommentBean(input);
		}};
	
}
