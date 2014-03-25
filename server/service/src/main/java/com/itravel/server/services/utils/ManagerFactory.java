package com.itravel.server.services.utils;

import java.util.ServiceLoader;

import com.itravel.server.interfaces.dal.managers.IActivitiesManager;
import com.itravel.server.interfaces.dal.managers.IAttractionsManager;
import com.itravel.server.interfaces.dal.managers.ITravelNoteManager;
import com.itravel.server.interfaces.dal.managers.IUserManager;



public final class ManagerFactory {
	private static ServiceLoader<IUserManager> serviceLoader = ServiceLoader.load(IUserManager.class);
	private static ServiceLoader<IActivitiesManager> serviceLoader4ActivitiesManager = ServiceLoader.load(IActivitiesManager.class);
	private static ServiceLoader<ITravelNoteManager> serviceLoader4TravelNoteManager = ServiceLoader.load(ITravelNoteManager.class);
	private static ServiceLoader<IAttractionsManager> serviceLoader4AttractionManager = ServiceLoader.load(IAttractionsManager.class);
	public static IUserManager getUserManager() {
		for(IUserManager service : serviceLoader)
        {
			return service;
        }
		
		return null;
	}
	
	public static IActivitiesManager getActivitiesManager() {
		for(IActivitiesManager service : serviceLoader4ActivitiesManager)
        {
			return service;
        }
		return null;
	}
	
	
	public static ITravelNoteManager getTravelNoteManager() {
		for(ITravelNoteManager service : serviceLoader4TravelNoteManager)
        {
			return service;
        }
		return null;
	}
	
	public static void main(String[] args) {
		
	}

	public static IAttractionsManager getAttractionManager() {
		// TODO Auto-generated method stub
		for(IAttractionsManager service : serviceLoader4AttractionManager)
        {
			return service;
        }
		return null;
	}
}
