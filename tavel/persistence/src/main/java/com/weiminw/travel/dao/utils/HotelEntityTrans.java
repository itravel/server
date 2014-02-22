package com.weiminw.travel.dao.utils;

import com.google.common.base.Function;
import com.weiminw.travel.dao.impls.pos.HotelEntity;
import com.weiminw.travel.dao.impls.pos.HotelLocationEntity;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.daos.IHotelLocation;

public final class HotelEntityTrans {
//	private static final class HotelEntity2IHotel implements Function<HotelEntity,IHotel>{
//
//		@Override
//		public IHotel apply(HotelEntity input) {
//			return Hotel.of(input.getId(), input.getName(), input.getDescription(), input.getProvince(), input.getCity(), input.getAddress(), input.getLatitude(), input.getLongitude(), input.getTelephone()) ;
//		}
//		
//	}
//	
//	private static final class IHotel2HotelEntity implements Function<IHotel,HotelEntity>{
//
//		@Override
//		public HotelEntity apply(IHotel input) {
//			HotelEntity entity = HotelEntity.of(input);
//			return entity;
//		}
//	
//	}
//	
//	private static final class HotelLocationEntity2IHotelLocation implements Function<HotelLocationEntity,IHotelLocation>{
//
//		@Override
//		public IHotelLocation apply(HotelLocationEntity input) {
//			return HotelLocation.of(
//					input.getId(),
//					input.getName(),
//					input.getLongitude(),
//					input.getLatitude());
//		}
//	
//	}
//	
//	
//	private static final HotelEntity2IHotel _HotelEntity2IHotel = new HotelEntity2IHotel();
//	private static final IHotel2HotelEntity _IHotel2HotelEntity = new IHotel2HotelEntity();
//	private static final HotelLocationEntity2IHotelLocation _HotelLocationEntity2IHotelLocation = new HotelLocationEntity2IHotelLocation();
//	
//	public static Function<HotelEntity,IHotel> getHotelEntity2IHotelFunc(){
//		return _HotelEntity2IHotel;
//	}
//	
//	public static Function<IHotel,HotelEntity> geIHotel2HotelEntityFunc(){
//		return _IHotel2HotelEntity;
//	}
//	
//	public static Function<HotelLocationEntity,IHotelLocation> getHotelLocationEntity2IHotelLocationFunc(){
//		return _HotelLocationEntity2IHotelLocation;
//	}
}
