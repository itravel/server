package com.itravel.server.services.aos;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.entities.AreaEntity;
import com.itravel.server.dal.entities.UserEntity;

public class ActivityBean {
	
	private ActivityEntity entity = null;
	public ActivityBean() {
		this.entity = new ActivityEntity();
	}
	
	public ActivityBean(ActivityEntity entity) {
		this.entity = entity;
	}

	public long getId() {
		return this.entity.getId();
	}

	public ActivityBean setId(long id) {
		this.entity.setId(id);
		return this;
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

	public void setContent(String content) {
		this.entity.setContent(content);
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

	public String getContact() {
		return this.entity.getContact();
	}

	public void setContact(String contact) {
		this.entity.setContact(contact);
	}

	public AreaBean getDestination() {
		return new AreaBean(this.entity.getDestination());
	}
	public void setDestination(long destinationId) {
		AreaEntity destination = new AreaEntity();
		destination.setId(destinationId);
		this.entity.setDestination(destination);
	}

	
	public int getFee() {
		return this.entity.getFee();
	}

	public void setFee(int fee) {
		this.entity.setFee(fee);
	}
	public String getScenerySpot() {
		return this.entity.getScenerySpot();
	}

	public void setScenerySpot(String scenerySpot) {
		this.entity.setScenerySpot(scenerySpot);
	}

	public String getTips() {
		return this.entity.getTips();
	}

	public void setTips(String tips) {
		this.entity.setTips(tips);
	}

	public List<ActivityJourneyBean> getJourney() {
		return FluentIterable.from(this.entity.getJourney()).transform(ActivityJourneyBean.FROM_ENTITY).toList();
	}
//
//	public void setJourney(List<ActivityJourneyEntity> journey) {
//		this.journey = journey;??
//	}
//	
//	public ActivityJourneyEntity addActivityJourney(ActivityJourneyEntity activityJourney) {
//		this.journey.add(activityJourney);
//		activityJourney.setActivity(this);
//
//		return activityJourney;
//	}
//
//	public ActivityJourneyEntity removeActivityJourney(ActivityJourneyEntity activityJourney) {
//		this.journey.remove(activityJourney);
//		activityJourney.setActivity(null);
//
//		return activityJourney;
//	}
//
	public UserBean getOrganizer() {
		return new UserBean(this.entity.getOrganizer());
	}

	public void setOrganizer(long organizerId) {
		UserEntity organizer = new UserEntity();
		organizer.setId(organizerId);
		this.entity.setOrganizer(organizer);
		
	}
//	
//
	public long getAvailableTime() {
		return this.entity.getAvailableTime();
	}

	public void setAvailableTime(long availableTime) {
		this.entity.setAvailableTime(availableTime);
	}
	
	
	public int getDuration(){
		return (this.entity.getDuration()&0x000000ff);
	}
	
	public int getDurationTimeUnit(){
		return ((this.entity.getDuration()&0x0000ff00)>>8);
	}
	
	public void setDuration(short durationNumber,short durationTimeUnit){
		int _d = ((durationTimeUnit<<8)&0x0000ff00) & (durationNumber & 0x000000ff);
		this.entity.setDuration(_d);
	}
	
	
	public static final Function<ActivityBean,ActivityEntity> TO_ENTITY = new Function<ActivityBean,ActivityEntity>(){

		@Override
		public ActivityEntity apply(ActivityBean input) {
			return input.entity;
		}
		
	};
	
	public static final Function<ActivityEntity,ActivityBean> FROM_ENTITY = new Function<ActivityEntity,ActivityBean>(){

		@Override
		public ActivityBean apply(ActivityEntity input) {
			return new ActivityBean(input);
		}
		
	};
	
	public static void main(String[] args) {
		System.out.println(0x04ff);
	}
	
	
	
}
