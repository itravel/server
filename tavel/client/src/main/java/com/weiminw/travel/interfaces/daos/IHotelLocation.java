package com.weiminw.travel.interfaces.daos;

public interface IHotelLocation {
	public long getId();
	public String getName();
	public double getLongitude();
	public double getLatitude();
	public IHotelLocation setId(long id);
	public IHotelLocation setName(String name);
	public IHotelLocation setLongitude(double longitude);
	public IHotelLocation setLatitude(double latitude);
	public static final IHotelLocation NONE = new Null();
	public static final class Null implements IHotelLocation {

		@Override
		public long getId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "";
		}

		@Override
		public double getLongitude() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public double getLatitude() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public IHotelLocation setId(long id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IHotelLocation setName(String name) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public IHotelLocation setLongitude(double longitude) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public IHotelLocation setLatitude(double latitude) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
		
	}
}
