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

	
	private long id;

	
	private Date gmtCreate;

	
	private Date gmtModified;

	
	private String travelerId;

	
	private String travelerName;

	
	private String travelerPhone;

	
	private OrderEntity order;

	public OrderTravelerEntity() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_create")
	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_modified")
	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	@Column(name="traveler_id")
	public String getTravelerId() {
		return this.travelerId;
	}

	public void setTravelerId(String travelerId) {
		this.travelerId = travelerId;
	}
	@Column(name="traveler_name")
	public String getTravelerName() {
		return this.travelerName;
	}

	public void setTravelerName(String travelerName) {
		this.travelerName = travelerName;
	}
	@Column(name="traveler_phone")
	public String getTravelerPhone() {
		return this.travelerPhone;
	}

	public void setTravelerPhone(String travelerPhone) {
		this.travelerPhone = travelerPhone;
	}
	//bi-directional many-to-one association to OrderEntity
	@ManyToOne
	@JoinColumn(name="orders_id")
	public OrderEntity getOrder() {
		return this.order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

}