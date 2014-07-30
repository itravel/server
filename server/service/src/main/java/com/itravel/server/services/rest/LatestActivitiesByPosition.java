package com.itravel.server.services.rest;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.ActivityEntity;
import com.itravel.server.dal.managers.ActivityManager;
import com.itravel.server.interfaces.dal.IFilter;

@Path("/latestActivitiesByPosition")
public class LatestActivitiesByPosition {
	protected ActivityManager activityManager = new ActivityManager();
	protected ObjectMapper objectMapper = new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	private static Logger logger = LogManager.getLogger(LatestActivitiesByPosition.class);
	@GET
	@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
	public Response f(@QueryParam (value = "days") @DefaultValue("14") int days,
			@QueryParam	 (value = "pages") @DefaultValue("0") int page,
			@QueryParam (value = "useId") @DefaultValue("0") long userId,
			@QueryParam (value = "longitude") @DefaultValue("0.0") double longitude,
			@QueryParam (value = "latitude") @DefaultValue("0.0") double latitude
			) throws JsonProcessingException{
		Date currentTime = new Date();
		Date timeBegin = currentTime;
		Date timeEnd = currentTime;
		timeBegin = DateUtils.addDays(timeBegin, 0-days);
		timeEnd = DateUtils.addDays(timeEnd, days);
		String result = getInfoFromBaiduMap(longitude,latitude);
		JsonNode rootNode = null;
		try{
			
			rootNode = objectMapper.readTree(result);
		}catch(Exception e){
			rootNode = null;
			System.out.println(e);
		}
		String cityName = rootNode.path("result").path("addressComponent").path("city").toString();
		cityName = cityName.replace("\"", "");
		List<ActivityEntity> activites = activityManager.getLatestActivities(timeBegin,timeEnd,page, 15);
		String activityJsonStr="";
		try {
			activityJsonStr = objectMapper.writeValueAsString(activites);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		return Response.ok().entity(activityJsonStr).build();
	}

	private String getInfoFromBaiduMap(double longitude,double latitude){
		//String cityName="";
		String rspStr = "";
		String location = String.valueOf(latitude)+","+String.valueOf(longitude);
		System.out.println(location);
		try{
			HttpClient client = HttpClients.createDefault();
			URI posUrl = new URIBuilder("http://api.map.baidu.com/geocoder/v2/")
			.setParameter("ak", "vnWOlOtrAtGQYENrRmwr0KjK")
			.setParameter("location",location)
			.setParameter("output", "json")
			.build();

			HttpGet get = new HttpGet(posUrl);
			HttpResponse rsp = client.execute(get);
			rspStr = EntityUtils.toString(rsp.getEntity());
			return rspStr;
		}catch(Exception e){
			System.out.println(e);
			return rspStr;
		}
		//return cityName;
	}
	
	
	
}
