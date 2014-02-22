package com.weiminw.web.listeners;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;













import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.weiminw.business.managers.HotelManager;
import com.weiminw.business.spatial.HotelSpatial;
import com.weiminw.travel.interfaces.managers.IHotelManager;

/**
 * Application Lifecycle Listener implementation class ApplicatoinShutdownListener
 *
 */
public final class HotelApplicationContextListener implements ServletContextListener {
	private final static Logger logger = LogManager.getLogger(HotelApplicationContextListener.class);
    private final static ExecutorService executors = Executors.newFixedThreadPool(10);
	/**
     * Default constructor. 
     */
    public HotelApplicationContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
    	HotelManager dao = HotelManager.create();
    	HotelSpatial.initIndex(dao.getHotels());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
    	
    }
	
}
