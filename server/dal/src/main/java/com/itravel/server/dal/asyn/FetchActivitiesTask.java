package com.itravel.server.dal.asyn;

import java.util.List;
import java.util.concurrent.Callable;

import com.itravel.server.dal.managers.AttractionsManager;
import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;

public class FetchActivitiesTask implements Callable<List<IActivities>> {

	private int offset;
	private int count;
	private IActivitiesManager manager;

	public FetchActivitiesTask(int offset, int count, IActivitiesManager manager) {
		// TODO Auto-generated constructor stub
		this.offset = offset;
		this.count = count;
		this.manager = manager;
	}

	@Override
	public List<IActivities> call() throws Exception {
		// TODO Auto-generated method stub
		return this.manager.getRange(offset, count);
	}

}
