package com.itravel.server.interfaces.dal.managers;

import java.util.Collection;
import java.util.List;

import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IUser;

public interface IActivitiesManager {
	IActivities newActivities();
	boolean add(IActivities activities);
	boolean remove(IActivities activities);
	IActivities get(long id);
	List<IActivities> getAvailableActivities(int start,int count);
}
