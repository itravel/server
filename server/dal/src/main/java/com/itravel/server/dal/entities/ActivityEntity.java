package com.itravel.server.dal.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IUser;

import java.util.Collection;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
@NamedQueries({
	@NamedQuery(name="ActivityEntity.findAll", query="SELECT a FROM ActivityEntity a"),
	@NamedQuery(name="ActivityEntity.findAvailable", query="SELECT a FROM ActivityEntity a where a.endTime > :endTime and  a.status = 1"),
})

public class ActivityEntity implements Serializable,IActivities {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time")
	private Date endTime;

	private double latitude;

	private double longitude;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time")
	private Date startTime;

	@Column(name="user_avatar")
	private String userAvatar;

	@Column(name="user_id")
	private long userId;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="status")
	private int status;
	
	@Column(name = "pics")
	private String pics;

	@ManyToMany(targetEntity = UserEntity.class)
	@JoinTable(
		name = "activities_user",
		joinColumns = @JoinColumn( name = "activities_id"),
		inverseJoinColumns = @JoinColumn( name = "user_id" )
	)
	private Collection<IUser> users;
	
	public ActivityEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserAvatar() {
		return this.userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public void setUsers(Collection<IUser> users) {
		// TODO Auto-generated method stub
		this.users = users;
	}

	@Override
	public Collection<IUser> getUsers() {
		// TODO Auto-generated method stub
		return this.users;
	}
	
	public void addUser(IUser user){
		this.users.add(user);
	}

	@Override
	public void addActivitiesPic(String picPath) {
		// TODO Auto-generated method stub
		Collection<String> pics = this.getActivitiesPics();
		pics.add(picPath);
		this.pics = Joiner.on(",").join(pics);
	}
	
	@Override
	public void addActivitiesPics(String... picPath){
		Set newPicsPath = Sets.newHashSet(picPath);
		Collection<String> pics = this.getActivitiesPics();
		pics.addAll(newPicsPath);
		this.pics = Joiner.on(",").join(pics);
	}

	@Override
	public Collection<String> getActivitiesPics() {
		// TODO Auto-generated method stub
		return Sets.newHashSet(Splitter.on(",").split(this.pics));
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	
	

}