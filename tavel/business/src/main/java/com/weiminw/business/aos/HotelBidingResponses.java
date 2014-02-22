package com.weiminw.business.aos;

import com.weiminw.travel.dao.impls.pos.HotelBidingResponseEntity;
import com.weiminw.travel.interfaces.daos.IHotelBidingResponse;

public final class HotelBidingResponses {
	public static IHotelBidingResponse of(long bidingId,long sellerId,long hotelId){
		IHotelBidingResponse response = new HotelBidingResponseEntity();
		response.setHotelBidingId(bidingId);
		response.setSellerId(sellerId);
		response.setHotelId(hotelId);
		return response;
	}
}
