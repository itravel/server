package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;


/**
 * The persistent class for the order_travelers database table.
 * 
 */
@Entity
@Table(name="order_travelers")
@NamedQuery(name="OrderTravelerEntity.findAll", query="SELECT o FROM OrderTravelerEntity o")
public class OrderTravelerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_create")
	private Date gmtCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_modified")
	private Date gmtModified;

	@Column(name="traveler_id")
	private String travelerId;

	@Column(name="traveler_name")
	private String travelerName;

	@Column(name="traveler_phone")
	private String travelerPhone;

	//bi-directional many-to-one association to OrderEntity
	@ManyToOne
	@JoinColumn(name="orders_id")
	private OrderEntity order;

	public OrderTravelerEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getTravelerId() {
		return this.travelerId;
	}

	public void setTravelerId(String travelerId) {
		this.travelerId = travelerId;
	}

	public String getTravelerName() {
		return this.travelerName;
	}

	public void setTravelerName(String travelerName) {
		this.travelerName = travelerName;
	}

	public String getTravelerPhone() {
		return this.travelerPhone;
	}

	public void setTravelerPhone(String travelerPhone) {
		this.travelerPhone = travelerPhone;
	}

	public OrderEntity getOrder() {
		return this.order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

}