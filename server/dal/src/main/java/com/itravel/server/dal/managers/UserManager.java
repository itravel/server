package com.itravel.server.dal.managers;

import javax.persistence.EntityManager;

import com.itravel.server.dal.entities.UserEntity;
import com.itravel.server.interfaces.dal.IUser;
import com.itravel.server.interfaces.dal.managers.IUserManager;

public class UserManager extends AbstractManager implements IUserManager {
	
	@Override
	public boolean save(IUser user) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public boolean remove(IUser user) {
		EntityManager manager = emf.createEntityManager();
		manager.remove(user);
		return true;
	}

	

	@Override
	public IUser get(long userId) {
		EntityManager manager = emf.createEntityManager();
		IUser user = manager.find(UserEntity.class, userId);
		manager.close();
		return user;
	}

	@Override
	public IUser getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		IUser user = manager.find(UserEntity.class, 2L);
		manager.close();
		return user;
	}

	@Override
	public IUser newUser() {
		// TODO Auto-generated method stub
		return new UserEntity();
	}

	

}
