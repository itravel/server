package com.weiminw.business.managers;

import com.google.common.base.Preconditions;
import com.weiminw.travel.dao.IPersistence;
import com.weiminw.travel.dao.impls.MySqlPersistence;
import com.weiminw.travel.dao.impls.pos.HotelBidingEntity;
import com.weiminw.travel.interfaces.daos.IHotelBiding;
import com.weiminw.travel.interfaces.daos.IHotelBidingResponse;
import com.weiminw.travel.interfaces.managers.IHotelBidingManager;

public final class HotelBidingManager implements IHotelBidingManager {
	private static IPersistence<IHotelBiding> persistence4Biding = MySqlPersistence.create();
	private static IHotelBidingManager manager = new HotelBidingManager();
	/* (non-Javadoc)
	 * @see com.weiminw.business.managers.IHotelBidingManager#addHotelBiding(com.weiminw.travel.interfaces.daos.IHotelBiding)
	 */
	public IHotelBiding  addHotelBiding(IHotelBiding hotelBiding){
		return null;
	}
	public static IHotelBidingManager create() {
		// TODO Auto-generated method stub
		return manager;
	}
	/* (non-Javadoc)
	 * @see com.weiminw.business.managers.IHotelBidingManager#addHotelBidingResponse(com.weiminw.travel.interfaces.daos.IHotelBidingResponse)
	 */
	public IHotelBidingResponse addHotelBidingResponse(IHotelBidingResponse hotelBidingResponse) {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.weiminw.business.managers.IHotelBidingManager#getHotelBidingById(long)
	 */
	public IHotelBiding getHotelBidingById(long id) {
		// TODO Auto-generated method stub
		Preconditions.checkArgument(id > 0);
		IHotelBiding biding = persistence4Biding.getPersistenceObject(HotelBidingEntity.class, id);
		return biding;
	}
}
