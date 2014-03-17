package com.itravel.server.interfaces.dal.managers;

public interface IManager<T> {
	public boolean save(T entity);
	public boolean remove(T entity);
	public T get(long id);
	
}
