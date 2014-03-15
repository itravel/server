package com.itravel.server.interfaces.dal.managers;

import com.itravel.server.interfaces.dal.IUser;

public interface IUserManager {
	IUser newUser();
	boolean addUser(IUser user);
	boolean removeUser(IUser user);
	boolean removeUser(long userId);
	IUser getUser(long userId);
	IUser getUserByUserName(String userName);

}
