package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private long id;

	private String avatar;

	
	private String cellPhone;

	private long city;

	private String email;

	private String password;

	private int qq;

	private int role;

	private String userName;

	private String weibo;

	public UserEntity() {
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name="avatar")
	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Column(name="cell_phone")
	public String getCellPhone() {
		return this.cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	@Column(name="city")
	public long getCity() {
		return this.city;
	}

	public void setCity(long city) {
		this.city = city;
	}
	@Column(name="email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="qq")
	public int getQq() {
		return this.qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}

	@Column(name="role")
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	@Column(name="user_name")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="weibo")
	public String getWeibo() {
		return this.weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

}