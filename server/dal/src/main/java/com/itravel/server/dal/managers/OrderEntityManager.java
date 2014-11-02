package com.itravel.server.dal.managers;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.OrderEntity;

public class OrderEntityManager extends AbstractManager {
	private static final class OrderManagerHolder {
		private static OrderEntityManager orerManager = new OrderEntityManager();
	}
	public static OrderEntityManager newInstance() {
		return OrderManagerHolder.orerManager;
	}
	public boolean saveOrder(OrderEntity orderEntity){
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(orderEntity.getId() <= 0){
			orderEntity.setGmtCreate(new Date());
			orderEntity.setGmtModified(new Date());
			manager.persist(orderEntity);
		}
		else {
			OrderEntity order = manager.find(OrderEntity.class, orderEntity.getId());
			if(order == null){
				orderEntity.setGmtCreate(new Date());
				orderEntity.setGmtModified(new Date());
				manager.persist(orderEntity);
			}
			else {
				orderEntity.setGmtModified(new Date());
				manager.merge(orderEntity);
			}
		}
		manager.getTransaction().commit();
		manager.close();
		return true;
	}
	
	public List<OrderEntity> getOrdersByUser(long userId){
		EntityManager manager = this.emf.createEntityManager();
		List<OrderEntity> orders  = manager.createQuery("select o from OrderEntity o where o.userId = :userId order by o.id desc", OrderEntity.class).setParameter("userId", userId).getResultList();
		System.out.println(orders);
		manager.close();
		return orders;
	}

	public OrderEntity get(long id){
		EntityManager manager = this.emf.createEntityManager();
		OrderEntity entity = manager.find(OrderEntity.class, id);
		manager.close();
		return entity;
		
	}
	public void truncate() {
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		manager.createNativeQuery("truncate table orders").executeUpdate();
		manager.getTransaction().commit();
		manager.close();
	}
	
}
