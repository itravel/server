package web;

import java.util.List;

import com.itravel.server.interfaces.dal.IActivities;
import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.services.utils.ManagerFactory;

public final class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IActivitiesManager manager = ManagerFactory.getActivitiesManager();
		List<IActivities> activities = manager.getAvailableActivities(0, 0);
	}

}
