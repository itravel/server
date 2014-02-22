package com.weiminw.travel.interfaces.daos;

import java.io.Serializable;

import com.google.common.base.Strings;

public interface IHotel extends Serializable {
	public abstract long getId();

	public abstract IHotel setId(long id);

	public abstract String getAddress();

	public abstract IHotel setAddress(String address);

	public abstract int getCity();

	public abstract IHotel setCity(int city);
	
	public abstract String getCityName();

	public abstract String getDescription();

	public abstract IHotel setDescription(String description);

	public abstract double getLatitude();

	public abstract IHotel setLatitude(double latitude);

	public abstract double getLongitude();

	public abstract IHotel setLongitude(double longitude);

	public abstract String getName();

	public abstract IHotel setName(String name);


	public abstract int getProvince();

	public abstract IHotel setProvince(int province);
	
	public abstract String getProvinceName();

	public abstract String getTelephone();

	public abstract IHotel setTelephone(String telephone);
	
	
	public final static IHotel NONE = new Null();

	static final class Null implements IHotel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3773043675054478957L;
		public Null() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public long getId() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public IHotel setId(long id) {
			// TODO Auto-generated method stub
			return this;
			
		}
		@Override
		public String getAddress() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public IHotel setAddress(String address) {
			// TODO Auto-generated method stub
			return this;
			
		}
		@Override
		public int getCity() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public IHotel setCity(int city) {
			// TODO Auto-generated method stub
			return this;
			
		}
		@Override
		public String getCityName() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public IHotel setDescription(String description) {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public double getLatitude() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public IHotel setLatitude(double latitude) {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public double getLongitude() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public IHotel setLongitude(double longitude) {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public IHotel setName(String name) {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public int getProvince() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public IHotel setProvince(int province) {
			// TODO Auto-generated method stub
			return this;
		}
		@Override
		public String getProvinceName() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getTelephone() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public IHotel setTelephone(String telephone) {
			// TODO Auto-generated method stub
			return this;
		}
		
		

		
		

	};
}
