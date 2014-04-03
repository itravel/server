package com.itravel.server.interfaces.dal.managers;

import com.itravel.server.interfaces.dal.IUser;

public interface IUserManager extends IDaoManager<IUser> {
	IUser getUserByUserName(String userName);

	IUser getUserByPhoneNumber(String phoneNumber);

	

}
