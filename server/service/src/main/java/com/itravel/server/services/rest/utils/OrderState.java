package com.itravel.server.services.rest.utils;

public interface OrderState {
	
	OrderState handle(String action);
	
	class OrderComplementeState implements OrderState {

		@Override
		public OrderState handle(String action) {
			// TODO Auto-generated method stub
			return this;
		}
		
	}
	class OrderCreatedState implements OrderState {

		@Override
		public OrderState handle(String action) {
			// TODO Auto-generated method stub
			return new ;
		}
		
	}
	
	class OrderPaySuccessState implements OrderState {

		@Override
		public OrderState handle(String action) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class OrderPayFailedState implements OrderState {

		@Override
		public OrderState handle(String action) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class OrderPayTimeoutState implements OrderState {

		@Override
		public OrderState handle(String action) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class Order
}
