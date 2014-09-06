package com.itravel.server.services.aos;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.itravel.server.dal.entities.ActivityImageEntity;
import com.itravel.server.dal.entities.TagEntity;


public final class ActivityAO {
	/**
	 * 活动id
	 */
	private long id;
	
	/**
	 * 创建时间
	 */
	private Date gmt_create;
	
	/**
	 * 修改时间
	 */
	private Date gmt_modified;
	/**
	 * 活动标题
	 */
	private String title;
	/**
	 * 活动内容
	 */
	private String content;
	
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	
	private Date endTime;
	
	/**
	 * 出发地
	 */
	private String depart;
	
	

	/**
	 * 目的地
	 */
	private String destination;
	
	/**
	 * 活动景点
	 */
	private String scenerySpot;

	
	/**
	 * 活动联系人
	 */
	private String contact;
	
	/**
	 * 活动推荐人
	 */
	private String recommender;
	
	/**
	 * 活动主办方
	 */
	private String sponsor;
	
	/**
	 * 活动标签
	 */
	private List<String> tags;
	
	
	/**
	 * 活动参与类型
	 */
	private int participationType;

	/**
	 * 活动规模
	 */
	private int scale;
	
	/**
	 * 活动费用
	 */
	private int fee;
	
	/**
	 * 活动名气
	 */
	private int popularity;

	/**
	 * 活动交通方便度
	 */
	private int convenience;
	

	/**
	 * 活动独特性
	 */
	private int originality;

	private String web;
	
	private String journey;

	private String tips;

	/**
	 * 活动图片
	 */
	private List<String> images;
	
}
