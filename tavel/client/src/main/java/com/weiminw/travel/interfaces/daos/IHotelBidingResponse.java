package com.weiminw.travel.interfaces.daos;

public interface IHotelBidingResponse {
	public long getId();
	public IHotelBidingResponse setId(long id);
	
	public long getSellerId();
	public IHotelBidingResponse setSellerId(long sellerId);

	public long getHotelId();
	public IHotelBidingResponse setHotelId(long hotelId);
	
	public long getHotelBidingId();
	public IHotelBidingResponse setHotelBidingId(long bidingId);
}
