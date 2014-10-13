package com.itravel.server.dal.entities;

import java.util.BitSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.common.collect.Lists;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
public class ActivityEntity extends AbstractEventsEntity  {
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	};
	private static final long serialVersionUID = 1L;
	
	private long depart;
	
	private long destination;
	
	private String scenerySpot;

	private String contact;
	
	private UserEntity organizer;
	
	private int fee;
	
	private List<ActivityJourneyEntity> journey;
	
	private String tips;

	private BitSet availableTime;
	
	public ActivityEntity() {
		this.journey = Lists.newArrayList();
		this.availableTime = new BitSet();
		
	}

	/**
	 * 活动联系电话
	 */
	@Column(name="contact")
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * 目的地
	 */
	@Column(name="destination")
	public long getDestination() {
		return this.destination;
	}
	public void setDestination(long destination) {
		this.destination = destination;
	}

	/**
	 * 活动费用
	 */
	@Column(name="fee")
	public int getFee() {
		return this.fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	/**
	 * 活动景点
	 */
	@Column(name="scenery_spot")
	public String getScenerySpot() {
		return scenerySpot;
	}

	public void setScenerySpot(String scenerySpot) {
		this.scenerySpot = scenerySpot;
	}

	/**
	 * 出发地
	 */
	@Column(name="depart")
	public long getDepart() {
		return depart;
	}

	public void setDepart(long depart) {
		this.depart = depart;
	}
	/**
	 * 活动须知
	 */
	@Column(name="tips")

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	/**
	 * 旅行活动安排
	 */
	@OneToMany(cascade = CascadeType.ALL,mappedBy="activity")
	@OrderColumn(name="journey_order")
	public List<ActivityJourneyEntity> getJourney() {
		return this.journey;
	}

	public void setJourney(List<ActivityJourneyEntity> journey) {
		this.journey = journey;
	}
	
	public ActivityJourneyEntity addActivityJourney(ActivityJourneyEntity activityJourney) {
		this.journey.add(activityJourney);
		activityJourney.setActivity(this);

		return activityJourney;
	}

	public ActivityJourneyEntity removeActivityJourney(ActivityJourneyEntity activityJourney) {
		this.journey.remove(activityJourney);
		activityJourney.setActivity(null);

		return activityJourney;
	}

	/**
	 * 活动组织者
	 */
	@ManyToOne
	@JoinColumn(name="organizer")
	public UserEntity getOrganizer() {
		return organizer;
	}

	public void setOrganizer(UserEntity organizer) {
		this.organizer = organizer;
	}
	
	@Column(name="available_time")

	public long getAvailableTime() {
		if(availableTime.toLongArray().length <=0 ){
			return 0;
		}
		return availableTime.toLongArray()[0];
	}

	public void setAvailableTime(long availableTime) {
		this.availableTime = BitSet.valueOf(new long[]{availableTime});
	}

	
	public static void main(String[] args) {
		System.out.println(BitSet.valueOf(new long[]{1024L}));
	}




}