package com.itravel.server.services.aos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.itravel.server.dal.entities.OrderTravelerEntity;

public class OrderTravelerBean {
	private OrderTravelerEntity entity;
	
	public OrderTravelerBean() {
		// TODO Auto-generated constructor stub
		this.entity = new OrderTravelerEntity();
		
	}
	
	public OrderTravelerBean(OrderTravelerEntity entity){
		this.entity = entity;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.entity.getId();
	}

	public void setId(long id) {
		this.entity.setId(id);
	}
	
	public String getTravelerId() {
		return this.entity.getTravelerId();
	}

	public void setTravelerId(String travelerId) {
		this.entity.setTravelerId(travelerId);
	}

	public String getTravelerName() {
		return this.entity.getTravelerName();
	}

	public void setTravelerName(String travelerName) {
		this.entity.setTravelerName(travelerName);
	}
	@Column(name="traveler_phone")
	public String getTravelerPhone() {
		return this.entity.getTravelerPhone();
	}

	public void setTravelerPhone(String travelerPhone) {
		this.entity.setTravelerPhone(travelerPhone); 
	}
	//bi-directional many-to-one association to OrderEntity
	/*@ManyToOne
	@JoinColumn(name="orders_id")
	public OrderEntity getOrder() {
		return this.order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}*/

}
