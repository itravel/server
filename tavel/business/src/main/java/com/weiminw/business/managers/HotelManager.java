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
	 * ͨ���Ƶ�ID��ȡ�Ƶ���Ϣ
	 */
	public IHotel getHotelById(long id) {
		Preconditions.checkArgument(id>0);
		IHotel hotel = persistence4HotelEntity.getPersistenceObject(HotelEntity.class, id);
//		IHotel hotel = Optional.fromNullable(entity).transform(HotelEntityTrans.getHotelEntity2IHotelFunc()).or(IHotel.NONE);
		return hotel;
	}
	
	/**
	 * ���ݾƵ�Id��ȡ�Ƶ�λ����Ϣ
	 * @param id
	 * @return
	 */
	public IHotelLocation getHotelLocationInfoById(long id){
		Preconditions.checkArgument(id >0);
		IHotelLocation hotelLocation = persistence4HotelLocationEntity.getPersistenceObject(HotelLocationEntity.class, id);
		return hotelLocation;
	}
	
	/**
	 * ��ȡȫ���Ƶ�
	 */
	public List<IHotelLocation> getHotels() {
		List<IHotelLocation> hotelLocations = persistence4HotelLocationEntity.getPersistenceObjects("HotelLocationEntity.findAll");
		return hotelLocations;
	}
	/**
	 * ����HotelManagerʵ��
	 * @return
	 */
	public final static HotelManager create() {
		// TODO Auto-generated method stub
		return manager;
	}
	
	/**
	 * ���¾Ƶ�����
	 */
	public boolean updateHotel(IHotel hotel) {
//		Preconditions.checkNotNull(hotel);
//		HotelEntity entity = Optional.of(hotel).transform(HotelEntityTrans.geIHotel2HotelEntityFunc()).get();
//		return persistence4HotelEntity.updatePersistenceObject(entity);
		return false;
	}

	/**
	 * ͨ����γ�Ȼ�ȡ�ܱ߾Ƶ�λ����Ϣ
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
