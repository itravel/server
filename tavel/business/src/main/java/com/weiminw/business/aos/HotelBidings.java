package com.weiminw.business.aos;

import java.util.Date;

import com.weiminw.travel.dao.impls.pos.HotelBidingEntity;
import com.weiminw.travel.interfaces.daos.IHotelBiding;

public final class HotelBidings {
	public static IHotelBiding of(){
		
		return new HotelBidingEntity();
	}

	public static IHotelBiding of(long userId, Date checkIn, Date checkOut,
			int roomNum, double lowPrice, double highPrice, double lnt,
			double lat) {
		// TODO Auto-generated method stub
		IHotelBiding biding = new HotelBidingEntity();
		biding.setUserId(userId)
		.setCheckIn(checkIn)
		.setCheckOut(checkOut)
		.setRoomNum(roomNum)
		.setLowPrice(lowPrice)
		.setHighPrice(highPrice);
		return biding;
	}
}
