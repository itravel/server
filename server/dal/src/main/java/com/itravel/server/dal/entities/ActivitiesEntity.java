package com.itravel.server.dal.entities;

import com.itravel.server.dal.entities.UpcomingEventsEntity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static javax.persistence.FetchType.EAGER;

/**
 * Entity implementation class for Entity: Activities
 *
 */
@Entity
@Table(name="activities")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class ActivitiesEntity extends UpcomingEventsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	public ActivitiesEntity() {
		super();
		this.fee=0;
		this.tags="";
		this.type=0;
		this.scale=16;
		this.interestingRate=5;
		this.popularity = 5;
		this.convenience = 5;
	}
	/**
	 * 费用
	 */
	@Column(name="fee")
	private long fee;
	
	/**
	 * 标记
	 */
	@Column(name="tags",length=256)
	private String tags;
	
	/**
	 * �����
	 */
	@Column(name="activity_type",length=256)
	private int type;
	
	/**
	 * 规模
	 */
	@Column(name="scale")
	private int scale;
	
	/**
	 * 
	 */
	/*@JoinTable(inverseJoinColumns = @JoinColumn(name = "id", referencedColumnName = "ID", columnDefinition = "user_id"), joinColumns = @JoinColumn(name = "id", referencedColumnName = "id", columnDefinition = "activities_id"))
	@OneToMany(fetch = EAGER)
	private List<UserEntity> participants;*/
	
	/**
	 * 有趣程度
	 */
	@Column(name="interesting_rate")
	private int interestingRate;
	
	/**
	 * 流行程度
	 */
	@Column(name="popularity")
	private int popularity;
	
	/**
	 * 方便性
	 */
	@Column(name="convenience")
	private int convenience;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}

	public long getFee() {
		return fee;
	}

	public void setFee(long fee) {
		this.fee = fee;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	/*public List<UserEntity> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserEntity> participants) {
		this.participants = participants;
	}*/

	public int getInterestingRate() {
		return interestingRate;
	}

	public void setInterestingRate(int interestingRate) {
		this.interestingRate = interestingRate;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public int getConvenience() {
		return convenience;
	}

	public void setConvenience(int convenience) {
		this.convenience = convenience;
	}

	
}
