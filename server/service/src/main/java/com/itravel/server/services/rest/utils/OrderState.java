package com.itravel.server.services.rest.utils;


import com.google.common.base.Optional;
import com.itravel.admin.services.managers.OrderManager;
import com.itravel.server.dal.entities.OrderEntity;
import com.itravel.server.dal.managers.OrderEntityManager;
import com.itravel.server.services.aos.OrderBean;

public interface OrderState {
	
	void doAction(OrderManager manager);
	void abort(OrderManager manager);
	

	abstract class  AbstractOrderState implements OrderState{
		protected OrderBean bean;
		private OrderEntityManager manager = OrderEntityManager.newInstance();
		AbstractOrderState(OrderBean bean){
			this.bean = bean;
		}
		
		protected void persist(){
			OrderEntity entity = Optional.fromNullable(bean).transform(OrderBean.TO_ENTITY).get();
			manager.saveOrder(entity);
		}
		
		
	}
	/**
	 * 订单结束
	 * @author william.wangwm
	 *
	 */
	class OrderComplementeState extends AbstractOrderState {

		OrderComplementeState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}


		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}

	}
	/**
	 * 订单创建
	 * @author william.wangwm
	 *
	 */
	class OrderCreatedState extends AbstractOrderState {

		public OrderCreatedState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			manager.pay();
			
		}
		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * 付款成功
	 * @author william.wangwm
	 *
	 */
	class OrderPaySuccessState extends AbstractOrderState {

		OrderPaySuccessState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * 付款失败
	 * @author william.wangwm
	 *
	 */
	class OrderPayFailedState extends AbstractOrderState {

		OrderPayFailedState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * 付款超时
	 * @author william.wangwm
	 *
	 */
	class OrderPayTimeoutState extends AbstractOrderState {

		OrderPayTimeoutState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	/**
	 * 取消订单成功
	 * @author william.wangwm
	 *
	 */
	class OrderCancelSuccessState extends AbstractOrderState {

		OrderCancelSuccessState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 取消订单失败
	 * @author william.wangwm
	 *
	 */
	class OrderCancelFailedState extends AbstractOrderState {

		OrderCancelFailedState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}

		
		public void cancel(){
			
		}



		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 退款成功
	 * @author william.wangwm
	 *
	 */
	class OrderRefundSuccessState extends AbstractOrderState {

		OrderRefundSuccessState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 退款失败
	 * @author william.wangwm
	 *
	 */
	class OrderRefundFailedState extends AbstractOrderState {

		OrderRefundFailedState(OrderBean bean) {
			super(bean);
			// TODO Auto-generated constructor stub
		}




		@Override
		public void doAction(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void abort(OrderManager manager) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}
