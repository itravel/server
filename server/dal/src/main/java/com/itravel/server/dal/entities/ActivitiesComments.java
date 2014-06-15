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
	 * �������ID
	 */
	@Column(name="activities_id")
	private long activitiesId;
	
	/**
	 * �û�����
	 */
	@Column(name="comments",length=4096)
	private String comments;
	
	/**
	 * Ȥζ������
	 */
	@Column(name="interesting_rate")
	private int interestingRate;
	
	/**
	 * ����
	 */
	@Column(name="popularity")
	private int popularity;
	
	/**
	 * ��ͨ������
	 */
	@Column(name="convenience")
	private int convenience;
	

   
}
