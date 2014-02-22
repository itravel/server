package com.weiminw.travel.interfaces.managers;

import com.weiminw.travel.interfaces.daos.IHotelBiding;
import com.weiminw.travel.interfaces.daos.IHotelBidingResponse;

public interface IHotelBidingManager {

	//	private static IHotelBidingManager manager = new HotelBidingManager();
	public IHotelBiding addHotelBiding(IHotelBiding hotelBiding);

	public IHotelBidingResponse addHotelBidingResponse(
			IHotelBidingResponse hotelBidingResponse);

	public IHotelBiding getHotelBidingById(long id);

}