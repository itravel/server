package com.weiminw.business.managers;

import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.weiminw.travel.dao.impls.MySqlPersistence;
import com.weiminw.travel.dao.impls.pos.SellerEntity;
import com.weiminw.travel.dao.impls.pos.UserEntity;
import com.weiminw.travel.dao.utils.AccountEntityTrans;
import com.weiminw.travel.interfaces.daos.ISeller;
import com.weiminw.travel.interfaces.daos.IUser;
import com.weiminw.travel.interfaces.managers.IAccountManager;

public class AccountManager implements IAccountManager {
	private final  MySqlPersistence<IUser> userPersistence =MySqlPersistence.create();
	private final  MySqlPersistence<ISeller> sellerPersistence =MySqlPersistence.create();
	private final static AccountManager manager = new AccountManager();
	@Override
	public IUser getUserById(long id) {
		Preconditions.checkArgument(id > 0);
		IUser entity = userPersistence.getPersistenceObject(UserEntity.class, id);
		return Optional.fromNullable(entity).or(IUser.NONE);
	}

	@Override
	public List<ISeller> getSellerByHid(long id) {
		Map.Entry<String, Long> _HID_QUERY = Maps.immutableEntry("hid", id);
		List<ISeller> sellers = this.sellerPersistence.getPersistenceObjects(SellerEntity.QUERY_BY_HID,_HID_QUERY );
		return sellers;
	}

	@Override
	public IUser addUser(IUser user) {
		Preconditions.checkNotNull(user);
		userPersistence.insertPersistenceObjects(user);
		return user;
	}

	@Override
	public ISeller addSeller(ISeller seller) {
		// TODO Auto-generated method stub
		Preconditions.checkNotNull(seller);
		sellerPersistence.insertPersistenceObjects(seller);
		return seller;
	}

	public static AccountManager create() {
		// TODO Auto-generated method stub
		return manager;
	}

	public ISeller getSellerById(long id) {
		// TODO Auto-generated method stub
		Preconditions.checkArgument(id >0 );
		return sellerPersistence.getPersistenceObject(SellerEntity.class, id);
	}

	public IUser getUserByUserName(String name) {
		Preconditions.checkNotNull(name);
		Map.Entry<String, String> nameEntry = Maps.immutableEntry("name", name);
		this.userPersistence.getPersistenceObjects(UserEntity.QUERY_BY_NAME, nameEntry);
		return null;
	}

	public ISeller getsellerByName(String name) {
		Preconditions.checkNotNull(name);
		Map.Entry<String, String> nameEntry = Maps.immutableEntry("name", name);
		sellerPersistence.getPersistenceObjects(SellerEntity.QUERY_BY_NAME, nameEntry);
		return null;
	}

}
