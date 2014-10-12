package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="UserEntityss.findAll", query="SELECT u FROM UserEntityss u")
public class UserEntityss implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String avatar;
	private String cellPhone;
	private int city;
	private String email;
	private String password;
	private int qq;
	private int role;
	private String userName;
	private String weibo;

	public UserEntityss() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


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


	public int getCity() {
		return this.city;
	}

	public void setCity(int city) {
		this.city = city;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getQq() {
		return this.qq;
	}

	public void setQq(int qq) {
		this.qq = qq;
	}


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


	public String getWeibo() {
		return this.weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

}