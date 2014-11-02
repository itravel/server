package com.itravel.server.services.aos;

import java.util.Date;

import com.google.common.base.Function;
import com.itravel.server.dal.entities.OrderEntity;
import com.itravel.server.services.rest.utils.OrderState;
import com.itravel.server.services.rest.utils.OrderState.OrderCreatedState;

public class OrderBean {
	private OrderEntity entity;
	
	private OrderBean(OrderEntity entity){
		this.entity = entity;
	}
	
	public OrderBean(){
		this.entity = new OrderEntity();
		entity.setId(System.nanoTime());
	}
	
	public long getId() {
		return this.entity.getId();
	}

	public void setId(long id) {
		this.entity.setId(id);
	}

	public long getUserId() {
		return this.entity.getUserId();
	}

	public void setUserId(long userId) {
		this.entity.setUserId(userId);
	}
	public long getActivityId() {
		return this.entity.getActivityId();
	}

	public void setActivityId(long activityId) {
		this.entity.setActivityId(activityId);
	}
	public int getActualPayment() {
		return this.entity.getActualPayment();
	}

	public void setActualPayment(int actualPayment) {
		this.entity.setActualPayment(actualPayment);
	}
	public int getAdultNumber() {
		return this.entity.getAdultNumber();
	}

	public void setAdultNumber(int adultNumber) {
		this.entity.setAdultNumber(adultNumber);
	}
	public int getAmountPayable() {
		return this.entity.getAmountPayable();
	}

	public void setAmountPayable(int amountPayable) {
		this.entity.setAmountPayable(amountPayable);
	}
	public Date getBookDate() {
		return this.entity.getBookDate();
	}

	public void setBookDate(Date bookDate) {
		this.entity.setBookDate(bookDate);
	}
	public int getChildrenNumber() {
		return this.entity.getChildrenNumber();
	}

	public void setChildrenNumber(int childrenNumber) {
		this.setChildrenNumber(childrenNumber);
	}
	public String getContactorMail() {
		return this.entity.getContactorMail();
	}

	public void setContactorMail(String contactorMail) {
		this.entity.setContactorMail(contactorMail);
	}
	public String getContactorName() {
		return this.entity.getContactorName();
	}

	public void setContactorName(String contactorName) {
		this.entity.setContactorName(contactorName);
	}
	public String getContactorPhone() {
		return this.entity.getContactorPhone();
	}

	public void setContactorPhone(String contactorPhone) {
		this.entity.setContactorPhone(contactorPhone);
	}
	
	public String getRemarks() {
		return this.entity.getRemarks();
	}

	public void setRemarks(String remarks) {
		this.entity.setRemarks(remarks);
	}
	public int getStatus() {
		return this.entity.getStatus();
	}

	public void setStatus(int status) {
		this.entity.setStatus(status);
	}

	/*public List<OrderTravelerEntity> getOrderTravelers() {
		return this.orderTravelers;
	}

	public void setOrderTravelers(List<OrderTravelerEntity> orderTravelers) {
		this.orderTravelers = orderTravelers;
	}

	public OrderTravelerEntity addOrderTraveler(OrderTravelerEntity orderTraveler) {
		getOrderTravelers().add(orderTraveler);
		orderTraveler.setOrder(this);

		return orderTraveler;
	}

	public OrderTravelerEntity removeOrderTraveler(OrderTravelerEntity orderTraveler) {
		getOrderTravelers().remove(orderTraveler);
		orderTraveler.setOrder(null);

		return orderTraveler;
	}*/
	
	public OrderState getOrderState(){
		switch(this.getStatus()){
		case 1: return new OrderState.OrderCreatedState(this);
		default:return new OrderState.OrderCreatedState(this);
		}
	}
	public static Function<OrderBean,OrderEntity> TO_ENTITY = new Function<OrderBean,OrderEntity>(){

		@Override
		public OrderEntity apply(OrderBean input) {
			// TODO Auto-generated method stub
			return input.entity;
		}
		
	};
	
	public static Function<OrderEntity,OrderBean> FROM_ENTITY = new Function<OrderEntity,OrderBean>(){

		@Override
		public OrderBean apply(OrderEntity input) {
			// TODO Auto-generated method stub
			return new OrderBean(input);
		}
		
	};
}
