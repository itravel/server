package com.itravel.server.interfaces.dal.managers;

import com.itravel.server.interfaces.dal.IUser;

public interface IUserManager extends IManager<IUser> {
	IUser newUser();
	IUser getUserByUserName(String userName);

}
