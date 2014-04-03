package com.itravel.server.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.itravel.server.dal.spatial.ActivitiesSpatialManager;
import com.itravel.server.dal.spatial.AttractionsSpatialManager;

/**
 * Application Lifecycle Listener implementation class SpatialIndexInitializationListener
 *
 */
@WebListener
public class SpatialIndexInitializationListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SpatialIndexInitializationListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    	ActivitiesSpatialManager.getInstance().initIndex();
    	AttractionsSpatialManager.getInstance().initIndex();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }
	
}
