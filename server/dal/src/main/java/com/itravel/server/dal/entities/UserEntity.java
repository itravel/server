package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.itravel.server.interfaces.dal.IUser;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries(value = { 
		@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u"),
		@NamedQuery(name="UserEntity.findActivitiesUsers", query="SELECT u FROM UserEntity u , ActivitiesUserEntity au where  u.id = au.userId and au.activitiesId = :activitiesId"),
		@NamedQuery(name="UserEntity.findByPhone", query="SELECT u FROM UserEntity u where u.cellPhone = :cellPhone"),
		@NamedQuery(name="UserEntity.findByUserName", query="SELECT u FROM UserEntity u where u.userName = :userName")
})

public class UserEntity implements Serializable,IUser {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String avatar;

	@Column(name="cell_phone")
	private String cellPhone;

	private int city;

	private int district;

	private String email;

	private double latitude;

	private double longitude;

	private String password;

	private int province;

	private int qq;

	@Column(name="user_name")
	private String userName;

	private String weibo;

	public UserEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public int getCity() {
		return this.city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getDistrict() {
		return this.district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProvince() {
		return this.province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getQq() {
		return this.qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWeibo() {
		return this.weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}