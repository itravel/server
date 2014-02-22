package com.weiminw.travel.interfaces.managers;

import java.util.List;

import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.daos.IHotelLocation;

public interface IHotelManager {
	IHotel getHotelById(long id);
	List<IHotelLocation> getHotels();
	boolean updateHotel(IHotel hotel);
	List<IHotelLocation> getHotelLocation(double lnt,double lat,int radius,int start, int count);
}
