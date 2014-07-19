package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ActivitiesComments
 *
 */
@Entity
@Table(name="activities_comments")
public class ActivitiesCommentsEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ActivitiesCommentsEntity() {
		super();
	}
	@Id
	private long id;
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	/**
	 * 
	 */
	@Column(name="activities_id")
	private long activitiesId;
	public long getActivitiesId(){
		return this.activitiesId;
	}
	
	public void setActivitiesId(long activitiesId){
		this.activitiesId = activitiesId;
	}
	/**
	 * 
	 */
	@Column(name="comments",length=4096)
	private String comments;
	public String getComments(){
		return this.comments;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	/**
	 * 
	 */
	@Column(name="interesting_rate")
	private int interestingRate;
	public long getInterestingRate(){
		return this.interestingRate;
	}
	
	public void setInterestingRate(int interestingRate){
		this.interestingRate = interestingRate;
	}
	/**
	 * 
	 */
	@Column(name="popularity")
	private int popularity;
	public long getPopularity(){
		return this.popularity;
	}
	
	public void setPopularity(int popularity){
		this.popularity = popularity;
	}
	/**
	 * 
	 */
	@Column(name="convenience")
	private int convenience;
	public long getConvenience(){
		return this.convenience;
	}
	
	public void setConvenience(int convenience){
		this.convenience = convenience;
	}

   
}
