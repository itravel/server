package com.itravel.server.services.rest.utils;

import com.itravel.server.dal.entities.OrderEntity;

public interface OrderState {
	
	void handle(String action,OrderEntity entity);
	
	/**
	 * 订单结束
	 * @author william.wangwm
	 *
	 */
	class OrderComplementeState implements OrderState {

		@Override
		
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
			return ;
		}
	}
	/**
	 * 订单创建
	 * @author william.wangwm
	 *
	 */
	class OrderCreatedState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
		}
		private void pay(){
			
		}
		private void cancel(){
			
		}
		
	}
	/**
	 * 付款成功
	 * @author william.wangwm
	 *
	 */
	class OrderPaySuccessState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
		}
		
		public void refund(){
			
		}
		
	}
	/**
	 * 付款失败
	 * @author william.wangwm
	 *
	 */
	class OrderPayFailedState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
		}
		
		private void pay(){
			
		}
		
	}
	/**
	 * 付款超时
	 * @author william.wangwm
	 *
	 */
	class OrderPayTimeoutState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
		}
		
		
	}
	
	/**
	 * 取消订单成功
	 * @author william.wangwm
	 *
	 */
	class OrderCancelSuccessState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 取消订单失败
	 * @author william.wangwm
	 *
	 */
	class OrderCancelFailedState implements OrderState {

		@Override
		public void handle(String action,OrderEntity entity){
			// TODO Auto-generated method stub
			
		}
		
		public void cancel(){
			
		}
		
	}
	
	/**
	 * 退款成功
	 * @author william.wangwm
	 *
	 */
	class OrderRefundSuccessState implements OrderState {

		@Override
		public void handle(String action, OrderEntity entity) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 退款失败
	 * @author william.wangwm
	 *
	 */
	class OrderRefundFailedState implements OrderState {

		@Override
		public void handle(String action, OrderEntity entity) {
			// TODO Auto-generated method stub
			
		}
		
		public void refund(){
			
		}
		
	}
	
	
}
