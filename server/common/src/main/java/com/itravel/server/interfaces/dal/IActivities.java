package com.itravel.server.interfaces.dal;

import java.util.Collection;
import java.util.Date;

public interface IActivities {
	public long getId() ;

	public void setId(long id) ;

	public String getDescription() ;

	public void setDescription(String description) ;

	public Date getEndTime();

	public void setEndTime(Date endTime);

	public double getLatitude();

	public void setLatitude(double latitude) ;

	public double getLongitude() ;

	public void setLongitude(double longitude) ;

	public String getName() ;

	public void setName(String name) ;

	public Date getStartTime() ;

	public void setStartTime(Date startTime) ;

	public String getUserAvatar() ;

	public void setUserAvatar(String userAvatar) ;

	public long getUserId() ;

	public void setUserId(long userId) ;

	public String getUserName() ;

	public void setUserName(String userName) ;

	public int getStatus() ;

	public void setStatus(int status) ;
	
	public void setUsers(Collection<IUser> users);
	
	public Collection<IUser> getUsers();
	
	public void addUser(IUser user);
}
