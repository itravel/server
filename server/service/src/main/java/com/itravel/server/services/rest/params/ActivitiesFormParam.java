package com.itravel.server.services.rest.params;

import java.text.ParseException;

import javax.ws.rs.FormParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivitiesFormParam {
	private static final Logger logger = LogManager
			.getLogger(ActivitiesFormParam.class);

	/**
	 * 标题
	 */
	@FormParam(value = "title")
	String title;// 必填

	/**
	 * 内容
	 */
	@FormParam(value = "content")
	String content;// 必填

	/**
	 * 开始时间
	 */
	@FormParam(value = "startTime")
	String startTime;// 必填

	/**
	 * 结束时间
	 */
	@FormParam(value = "endTime")
	String endTime;// 必填

	
	/**
	 * 出发地
	 */
	@FormParam(value = "from")
	private String from;

	/**
	 * 目的地
	 */
	@FormParam(value = "destination")
	private String destination;

	/**
	 * 活动景点
	 */
	@FormParam(value = "scenerySpot")
	private String scenerySpot;


	/**
	 * 活动图片
	 */
	@FormParam(value = "images")
	private String images;

	/**
	 * 活动联系人
	 */
	@FormParam(value = "contact")
	private String contact;

	/**
	 * 活动推荐人
	 */
	@FormParam(value = "recommender")
	private String recommender;

	/**
	 * 活动主办方
	 */
	@FormParam(value = "sponsor")
	private String sponsor;

	/**
	 * 活动标签
	 */
	@FormParam(value = "tags")
	private String tags;

	/**
	 * 活动参与类型
	 */
	@FormParam(value = "participationType")
	private int participationType;

	/**
	 * 活动规模
	 */
	@FormParam(value = "scale")
	private int scale;

	/**
	 * 活动费用
	 */
	@FormParam(value = "fee")
	private int fee;

	/**
	 * 活动名气
	 */
	@FormParam(value = "popularity")
	private int popularity;

	/**
	 * 活动交通方便度
	 */
	@FormParam(value = "convenience")
	private int convenience;

	/**
	 * 活动独特性
	 */
	@FormParam(value = "originality")
	private int originality;

	/**
	 * 活动网页地址
	 */
	@FormParam(value = "web")
	private String web;

	public int validate() {
		logger.debug(this);
		if (StringUtils.isEmpty(this.title)) {
			return -1;
		}
		if (StringUtils.isEmpty(this.content)) {
			return -2;
		}
		if (StringUtils.isEmpty(this.startTime)) {
			return -3;
		}
		if (StringUtils.isEmpty(this.endTime)) {
			return -4;
		}
		if (StringUtils.isEmpty(this.from)) {
			return -5;
		}
		if (StringUtils.isEmpty(this.destination)) {
			return -6;
		}
		if (StringUtils.isEmpty(this.scenerySpot)) {
			return -7;
		}
		if (StringUtils.isEmpty(this.images)) {
			return -8;
		}
		if (StringUtils.isEmpty(this.tags)) {
			return -9;
		}
		if (this.participationType < 0) {
			return -10;
		}
		if (this.popularity < 0) {
			return -11;
		}
		if (this.originality < 0) {
			return -12;
		}
		if (this.convenience < 0) {
			return -13;
		}

		try {
			DateUtils.parseDateStrictly(this.startTime, "yyyy-MM-dd");
			DateUtils.parseDateStrictly(this.endTime, "yyyy-MM-dd");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -14;
		}

		return 1;

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getFromAddress() {
		return from;
	}

	public void setFromAddress(String fromAddress) {
		this.from = fromAddress;
	}

	public String getDestinationCity() {
		return destination;
	}

	public void setDestinationCity(String destinationCity) {
		this.destination = destinationCity;
	}

	public String getDestinationAddress() {
		return scenerySpot;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.scenerySpot = destinationAddress;
	}



	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRecommender() {
		return recommender;
	}

	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getParticipationType() {
		return participationType;
	}

	public void setParticipationType(int participationType) {
		this.participationType = participationType;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
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

	public int getOriginality() {
		return originality;
	}

	public void setOriginality(int originality) {
		this.originality = originality;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}
}
