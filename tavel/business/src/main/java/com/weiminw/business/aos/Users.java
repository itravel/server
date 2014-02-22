package com.weiminw.business.aos;

import com.weiminw.travel.dao.impls.pos.UserEntity;
import com.weiminw.travel.interfaces.daos.IUser;

public final class Users {

	
	public static IUser of() {
		return new UserEntity();
	}
	

	public static IUser of(String name, String nick, String password,
			String cellPhone, String cloudPushCk, String cloudPushUk,
			String cloudPushTag) {
		// TODO Auto-generated method stub
		IUser user = new UserEntity();
		user.setName(name);
		user.setNick(nick);
		user.setPassword(password);
		user.setCloudPushCk(cloudPushCk);
		user.setCloudPushUk(cloudPushUk);
		user.setCloudPushTag(cloudPushTag);
		return user;
	
	}
}