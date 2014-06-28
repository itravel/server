package com.itravel.server.interfaces.dal.repos;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import com.itravel.server.interfaces.dal.IFilter;


public interface IDataRepository<T> {

	List<T> filterBy(IFilter<T> cityFilter);
	void persist(T entity);
	void persistAll(List<T> entities);
}
