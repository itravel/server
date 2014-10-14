package com.itravel.server.dal.managers;

import java.util.List;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.OrderEntity;

public class OrderManager extends AbstractManager {
	public boolean saveOrder(OrderEntity orderEntity){
		EntityManager manager = this.emf.createEntityManager();
		manager.getTransaction().begin();
		if(orderEntity.getId() <= 0){
			manager.persist(orderEntity);
		}
		else {
			OrderEntity order = manager.find(OrderEntity.class, orderEntity.getId());
			if(order == null){
				manager.persist(orderEntity);
			}
			else {
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
}
