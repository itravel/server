package com.weiminw.travel.interfaces.daos;

import java.util.Date;

public interface IHotelBiding {

	public abstract long getId();

	public abstract IHotelBiding setId(long id);
	
	public abstract long getUserId();
	
	public abstract IHotelBiding setUserId(long userId);

	public abstract Date getCheckIn();

	public abstract IHotelBiding setCheckIn(Date checkIn);

	public abstract Date getCheckOut();

	public abstract IHotelBiding setCheckOut(Date checkOut);

	public abstract Date getExpireTime();

	public abstract IHotelBiding setExpireTime(Date expireTime);

	public abstract double getHighPrice();

	public abstract IHotelBiding setHighPrice(double highPrice);

	public abstract double getLowPrice();

	public abstract IHotelBiding setLowPrice(double lowPrice);

	public abstract int getRoomNum();

	public abstract IHotelBiding setRoomNum(int roomNum);

	public abstract byte getSendPhone();

	public abstract IHotelBiding setSendPhone(byte sendPhone);

	public abstract byte getStatus();

	public abstract IHotelBiding setStatus(byte status);

}