package com.itravel.server.interfaces.dal.managers;

import java.util.Collection;

public interface ISpatialIndexManager<T> {
	public abstract void addIndex(T poi);
	public abstract void addIndex(Collection<T> pois);
	public abstract void deleteIndex(T poi);
	public abstract void deleteIndex(Collection<T> pois);
	public abstract void initIndex();
 
}
