package com.itravel.server.services.aos;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.ActivityJourneyEntity;

public class ActivityJourneyBean {
	private ActivityJourneyEntity entity;

	public ActivityJourneyBean(ActivityJourneyEntity entity) {
		this.entity = entity;
	}

	public ActivityJourneyBean() {
		this.entity = new ActivityJourneyEntity();
	}

	public long getActivity() {
		return this.entity.getActivity().getId();
	}

	public String getTitle() {
		return this.entity.getTitle();
	}

	public String getContent() {
		return this.entity.getContent();
	}

	public String getImage() {
		return this.entity.getImage();
	}

	public void setActivity(long activityId) {
		this.entity.getActivity().setId(activityId);
	}

	public void setTitle(String title) {
		this.entity.setTitle(title);
	}

	public void setContent(String content) {
		this.entity.setContent(content);
	}

	public void setImage(String image) {
		this.entity.setImage(image);
	}

	public static final Function<ActivityJourneyEntity, ActivityJourneyBean> FROM_ENTITY = new Function<ActivityJourneyEntity, ActivityJourneyBean>() {

		@Override
		public ActivityJourneyBean apply(ActivityJourneyEntity input) {
			// TODO Auto-generated method stub
			return new ActivityJourneyBean(input);
		}
	};
	public static final Function<ActivityJourneyBean, ActivityJourneyEntity> TO_ENTITY = new Function<ActivityJourneyBean, ActivityJourneyEntity>() {

		@Override
		public ActivityJourneyEntity apply(ActivityJourneyBean input) {
			// TODO Auto-generated method stub
			return input.entity;
		}
	};
}
