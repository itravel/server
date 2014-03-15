package com.itravel.server.interfaces.dal;

public interface IUser {

	public abstract long getId();

	public abstract void setId(long id);

	public abstract String getAvatar();

	public abstract void setAvatar(String avatar);

	public abstract String getCellPhone();

	public abstract void setCellPhone(String cellPhone);

	public abstract int getCity();

	public abstract void setCity(int city);

	public abstract int getDistrict();

	public abstract void setDistrict(int district);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract double getLatitude();

	public abstract void setLatitude(double latitude);

	public abstract double getLongitude();

	public abstract void setLongitude(double longitude);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract int getProvince();

	public abstract void setProvince(int province);

	public abstract int getQq();

	public abstract void setQq(int qq);

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract String getWeibo();

	public abstract void setWeibo(String weibo);

}