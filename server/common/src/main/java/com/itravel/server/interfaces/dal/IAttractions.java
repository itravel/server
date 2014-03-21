package com.itravel.server.interfaces.dal;

public interface IAttractions {

	public abstract long getId();

	public abstract void setId(long id);

	public abstract String getAddress();

	public abstract void setAddress(String address);

	public abstract int getCityCode();

	public abstract void setCityCode(int cityCode);

	public abstract String getCityName();

	public abstract void setCityName(String cityName);

	public abstract String getDescription();

	public abstract void setDescription(String description);

	public abstract double getLatitude();

	public abstract void setLatitude(double latitude);

	public abstract double getLongitude();

	public abstract void setLongitude(double longitude);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getPictures();

	public abstract void setPictures(String pictures);

}