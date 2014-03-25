package com.itravel.server.dal.managers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		manager.close();
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
	public IUser create() {
		// TODO Auto-generated method stub
		return new UserEntity();
	}

	@Override
	public IUser create(String json) {
		// TODO Auto-generated method stub
		IUser user = null;
		try {
			user = mapper.readValue(json, UserEntity.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public IUser getUserByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		EntityManager manager = emf.createEntityManager();
		List<IUser> users = manager.createNamedQuery("UserEntity.findByPhone").setParameter("cellPhone", phoneNumber).getResultList();
		System.out.println(users);
		if(users.size()>0){
			return users.get(0);
		}
		return null;
	}

	public static final IUserManager getInstance(){
		return UserManagerHolder.INSTANCE;
	}
	private static final class UserManagerHolder{
		private static final IUserManager INSTANCE = new UserManager();
	}
	@Override
	public List<IUser> getRange(int offset, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
