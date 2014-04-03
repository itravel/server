package com.itravel.server.interfaces.dal.managers;

import java.util.List;

public interface ISpatialSearchManager<T> {
	public List<T> getByLatLnt(double latitude,double longitude);
	public List<T> getByLatLnt(double latitude,double longitude,int radius);
	
}
