package com.itravel.server.interfaces.dal.managers;

import java.util.Collection;
import java.util.List;

import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.IUser;

public interface IActivitiesManager extends IDaoManager<IActivities> {
	List<IActivities> getAvailableActivities(int start,int count);
}
