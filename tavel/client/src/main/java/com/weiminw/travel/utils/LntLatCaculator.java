package com.weiminw.travel.utils;

public final class LntLatCaculator {
	/**
	 * 地球半径，单位为米
	 */
	private static final double EARTH_RADIUS = 6378.137*1000; 
	/**
	 * 求弧长
	 * @param d
	 * @return
	 */
	private static final double rad(double d)   
	{   
	     return d * Math.PI / 180.0;   
	}    
	  
	/**   
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米   
	 */   
	public static long GetDistance(double lat1, double lng1, double lat2, double lng2)   
	{   
	    double radLat1 = rad(lat1);   
	    double radLat2 = rad(lat2);   
	    double a = radLat1 - radLat2;   
	    double b = rad(lng1) - rad(lng2);   
	    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));   
	    s = s * EARTH_RADIUS;   
	    return Math.abs(Math.round(s * 10000) / 10000);   
	}   
}
