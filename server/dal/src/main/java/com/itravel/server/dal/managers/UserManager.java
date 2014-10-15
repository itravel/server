package com.itravel.server.dal.managers;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.UserEntity;

public class UserManager extends AbstractManager {
	
	public UserEntity getUserById(long id){
		EntityManager manager = emf.createEntityManager();
		UserEntity user = manager.find(UserEntity.class, id);
		manager.close();
		return user;
				
	}
	
	public UserEntity getUserByUserName(String userName){
		EntityManager manager = emf.createEntityManager();
		UserEntity user = manager.createQuery("select U from UserEntity U where U.userName = :userName",UserEntity.class).setParameter("userName", userName).getSingleResult();
		manager.close();
		return user;
	}
	
	public boolean saveUser(UserEntity entity){
		EntityManager manager = emf.createEntityManager();
		if(entity.getId() >= 0 ){
			UserEntity _user = manager.find(UserEntity.class, entity.getId());
			if(_user!=null){
				manager.merge(entity);
				
			}
			else {
				manager.persist(entity);
			}
		}
		else {
			manager.persist(entity);
		}
		manager.close();
		return true;
	}
}
