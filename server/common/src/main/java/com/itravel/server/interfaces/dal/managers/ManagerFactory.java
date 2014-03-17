package com.itravel.server.interfaces.dal.managers;

import java.util.ServiceLoader;



public final class ManagerFactory {
	private static ServiceLoader<IUserManager> serviceLoader = ServiceLoader.load(IUserManager.class);
	private static ServiceLoader<IActivitiesManager> serviceLoader4ActivitiesManager = ServiceLoader.load(IActivitiesManager.class);
	private static ServiceLoader<ITravelNoteManager> serviceLoader4TravelNoteManager = ServiceLoader.load(ITravelNoteManager.class);
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
}
