package com.itravel.server.interfaces.dal.managers;

import java.util.List;

import com.itravel.server.interfaces.dal.ITravelNote;

public interface IDaoManager<T> {
	public T create();
	public T create(String json);
	public boolean save(T entity);
	public boolean remove(T entity);
	public T get(long id);
	public long size();
	public List<T> getRange(int offset, int count);
	
}
