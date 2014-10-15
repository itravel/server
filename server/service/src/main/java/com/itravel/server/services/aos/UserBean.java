package com.itravel.server.services.aos;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.UserEntity;

public class UserBean {
	
	private UserEntity entity = null;
	
	public UserBean() {
		this.entity = new UserEntity();
	}
	public UserBean(UserEntity entity){
		this.entity = entity;
	}

	public long getId() {
		return this.entity.getId();
	}

	public void setId(long id) {
		this.entity.setId(id);
	}

	public String getAvatar() {
		return this.entity.getAvatar();
	}

	public void setAvatar(String avatar) {
		this.entity.setAvatar(avatar);
	}

	public String getUserName() {
		return this.entity.getUserName();
	}

	public void setUserName(String userName) {
		this.entity.setUserName(userName);
	}
	
	public static final Function<UserBean,UserEntity> TO_ENTITY = new Function<UserBean,UserEntity>(){

		@Override
		public UserEntity apply(UserBean input) {
			// TODO Auto-generated method stub
			return input.entity;
		}};
}
