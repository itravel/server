package com.itravel.server.services.aos;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.ActivityJourneyEntity;

public class ActivityJourneyBean {
	public ActivityJourneyBean(ActivityJourneyEntity input) {
		// TODO Auto-generated constructor stub
	}

	private ActivityJourneyEntity entity;
	
	public static final Function<ActivityJourneyEntity,ActivityJourneyBean> TO_BEAN = new Function<ActivityJourneyEntity,ActivityJourneyBean>(){

		@Override
		public ActivityJourneyBean apply(ActivityJourneyEntity input) {
			// TODO Auto-generated method stub
			return new ActivityJourneyBean(input) ;
		}};
}
