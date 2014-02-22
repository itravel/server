package com.weiminw.business.managers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.weiminw.business.spatial.HotelSpatial;
import com.weiminw.travel.dao.impls.MySqlPersistence;
import com.weiminw.travel.dao.impls.pos.HotelEntity;
import com.weiminw.travel.dao.impls.pos.HotelLocationEntity;
import com.weiminw.travel.dao.utils.HotelEntityTrans;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.daos.IHotelLocation;
import com.weiminw.travel.interfaces.managers.IHotelManager;

public class HotelManager implements IHotelManager{
	private final static MySqlPersistence<IHotel> persistence4HotelEntity = MySqlPersistence.create();
	private final static MySqlPersistence<IHotelLocation> persistence4HotelLocationEntity = MySqlPersistence.create();
	private final static Logger logger = LogManager.getLogger(HotelManager.class);
	private final static HotelManager manager = new HotelManager();
	
//	static {
//		HotelSpatial.initIndex(manager.getHotels());
//	}
		
	/**
	 * 通过酒店ID获取酒店信息
	 */
	public IHotel getHotelById(long id) {
		Preconditions.checkArgument(id>0);
		IHotel hotel = persistence4HotelEntity.getPersistenceObject(HotelEntity.class, id);
//		IHotel hotel = Optional.fromNullable(entity).transform(HotelEntityTrans.getHotelEntity2IHotelFunc()).or(IHotel.NONE);
		return hotel;
	}
	
	/**
	 * 根据酒店Id获取酒店位置信息
	 * @param id
	 * @return
	 */
	public IHotelLocation getHotelLocationInfoById(long id){
		Preconditions.checkArgument(id >0);
		IHotelLocation hotelLocation = persistence4HotelLocationEntity.getPersistenceObject(HotelLocationEntity.class, id);
		return hotelLocation;
	}
	
	/**
	 * 获取全部酒店
	 */
	public List<IHotelLocation> getHotels() {
		List<IHotelLocation> hotelLocations = persistence4HotelLocationEntity.getPersistenceObjects("HotelLocationEntity.findAll");
		return hotelLocations;
	}
	/**
	 * 创建HotelManager实例
	 * @return
	 */
	public final static HotelManager create() {
		// TODO Auto-generated method stub
		return manager;
	}
	
	/**
	 * 更新酒店数据
	 */
	public boolean updateHotel(IHotel hotel) {
//		Preconditions.checkNotNull(hotel);
//		HotelEntity entity = Optional.of(hotel).transform(HotelEntityTrans.geIHotel2HotelEntityFunc()).get();
//		return persistence4HotelEntity.updatePersistenceObject(entity);
		return false;
	}

	/**
	 * 通过经纬度获取周边酒店位置信息
	 */
	@Override
	public List<IHotelLocation> getHotelLocation(double lnt, double lat,
			int radius,int start, int count) {
		List<Long> hotelIds = HotelSpatial.search(lnt, lat, radius);
		List<IHotelLocation> hotels = Lists.newArrayList();
		for(long id:hotelIds){
			hotels.add(this.getHotelLocationInfoById(id));
		}
		return FluentIterable.from(hotels).skip(start).limit(count).toList();
	}


}
