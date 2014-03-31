package com.itravel.server.interfaces.dal.managers;

import java.util.List;

import com.itravel.server.interfaces.dal.IAttractions;

public interface IAttractionsManager extends IManager<IAttractions> {

	boolean batchSave(List<IAttractions> list);
	
	List<IAttractions> getByLngLat(int start,int count,double longitude, double latitude);
	
	List<IAttractions> getByCity(int start,int count,int cityCode);

}
