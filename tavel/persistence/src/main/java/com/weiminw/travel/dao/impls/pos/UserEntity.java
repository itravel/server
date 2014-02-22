package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.weiminw.travel.interfaces.daos.IUser;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQueries(value = { 
		@NamedQuery(name="UserEntity.findAll", query="SELECT u FROM UserEntity u"),
		@NamedQuery(name="UserEntity.findAllByName", query="SELECT u FROM UserEntity u where u.name = :name"),
})
public class UserEntity implements Serializable,IUser {
	private static final long serialVersionUID = 1L;

	public static String QUERY_BY_NAME = "UserEntity.findAllByName";

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="cell_phone")
	private String cellPhone;

	@Column(name="cloud_push_ck")
	private String cloudPushCk;

	@Column(name="cloud_push_tag")
	private String cloudPushTag;

	@Column(name="cloud_push_uk")
	private String cloudPushUk;

	private String name;

	private String nick;

	private String password;

	
	private UserEntity(long id, String cellPhone, String cloudPushCk,
			String cloudPushTag, String cloudPushUk, String name, String nick,
			String password) {
		super();
		this.id = id;
		this.cellPhone = cellPhone;
		this.cloudPushCk = cloudPushCk;
		this.cloudPushTag = cloudPushTag;
		this.cloudPushUk = cloudPushUk;
		this.name = name;
		this.nick = nick;
		this.password = password;
	}

	public UserEntity() {
	}
	
	public static UserEntity of(IUser user){
		return new UserEntity(
				user.getId(),
				user.getCellPhone(),
				user.getCloudPushCk(),
				user.getCloudPushTag(),
				user.getCloudPushUk(),
				user.getName(),
				user.getNick(),
				user.getPassword()
				);
	}

	public long getId() {
		return this.id;
	}

	public IUser setId(long id) {
		this.id = id;
		return this;
	
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public IUser setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		return this;
	}

	public String getCloudPushCk() {
		return this.cloudPushCk;
	}

	public IUser setCloudPushCk(String cloudPushCk) {
		this.cloudPushCk = cloudPushCk;
		return this;
	}

	public String getCloudPushTag() {
		return this.cloudPushTag;
	}

	public IUser setCloudPushTag(String cloudPushTag) {
		this.cloudPushTag = cloudPushTag;
		return this;
	}

	public String getCloudPushUk() {
		return this.cloudPushUk;
	}

	public IUser setCloudPushUk(String cloudPushUk) {
		this.cloudPushUk = cloudPushUk;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public IUser setName(String name) {
		this.name = name;
		return this;
	}

	public String getNick() {
		return this.nick;
	}

	public IUser setNick(String nick) {
		this.nick = nick;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public IUser setPassword(String password) {
		this.password = password;
		return this;
	}

}