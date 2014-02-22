package com.weiminw.travel.interfaces.managers;

import java.util.List;

import com.weiminw.travel.interfaces.daos.IRoomType;

public interface IRoomTypeManager {
	IRoomType getRoomTypeById(long id);
	List<IRoomType> getRoomTypeByHotelId(long hid);
}
