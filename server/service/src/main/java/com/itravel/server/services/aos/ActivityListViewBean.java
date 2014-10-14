package com.itravel.server.services.aos;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.ActivityEntity;

public class ActivityListViewBean {
	private ActivityEntity entity = null;
	
	public ActivityListViewBean() {
		this.entity = new ActivityEntity();
	}
	public ActivityListViewBean(ActivityEntity entity) {
		this.entity = entity;
	}
	
	public long getId() {
		return this.entity.getId();
	}

	public String getTitle() {
		return this.entity.getTitle();
	}

	public void setTitle(String title) {
		this.entity.setTitle(title);
	}

	public String getContent() {
		return this.entity.getContent();
	}


	public String getImage() {
		if(this.entity.getImage() == null||this.entity.getImage().isEmpty()){
			return "";
		}
		else if(this.entity.getImage().startsWith("/images")){
			return this.entity.getImage();
		}
		else {
			return "/images/"+this.entity.getImage();
		}
	}

	public void setImage(String image) {
		this.entity.setImage(image); 
	}
	
	
	public AreaBean getDestination() {
		return new AreaBean(this.entity.getDestination());
	}

	
	public int getFee() {
		return this.entity.getFee();
	}

	public String getScenerySpot() {
		return this.entity.getScenerySpot();
	}

	public UserBean getOrganizer() {
		return new UserBean(this.entity.getOrganizer());
	}
	
	public static final Function<ActivityEntity,ActivityListViewBean> FROM_ENTITY = new Function<ActivityEntity,ActivityListViewBean>(){

		@Override
		public ActivityListViewBean apply(ActivityEntity input) {
			return new ActivityListViewBean(input);
		}};
	
}
