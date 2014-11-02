package com.itravel.admin.services.managers;

import com.itravel.server.services.aos.OrderBean;
import com.itravel.server.services.rest.utils.OrderState;

public final class OrderManager {
	OrderState state;
	private OrderBean bean;

	private OrderManager(OrderBean bean){
		this.bean = bean;
		this.state = new OrderState.OrderCreatedState();
	}
	public void pay(){
		
	}
	public void cancelPay(){
		
	}
	
	public void refund(){
		
	}

	public static OrderManager from(OrderBean bean) {
		// TODO Auto-generated method stub
		return null;
	}
}
