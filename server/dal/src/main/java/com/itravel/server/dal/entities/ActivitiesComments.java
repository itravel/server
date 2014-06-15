package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ActivitiesComments
 *
 */
@Entity
@Table(name="activities_comments")
public class ActivitiesComments implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public ActivitiesComments() {
		super();
	}
	@Id
	private long id;
	
	/**
	 * 关联活动的ID
	 */
	@Column(name="activities_id")
	private long activitiesId;
	
	/**
	 * 用户评论
	 */
	@Column(name="comments",length=4096)
	private String comments;
	
	/**
	 * 趣味性评级
	 */
	@Column(name="interesting_rate")
	private int interestingRate;
	
	/**
	 * 人气
	 */
	@Column(name="popularity")
	private int popularity;
	
	/**
	 * 交通方便性
	 */
	@Column(name="convenience")
	private int convenience;
	

   
}
