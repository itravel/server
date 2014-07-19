package com.itravel.server.services.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.itravel.server.dal.entities.ActivitiesEntity;
import com.itravel.server.dal.filters.ActivitiesDBFilter;
import com.itravel.server.dal.repos.UpcomingEventsDBRepository;
import com.itravel.server.interfaces.dal.IFilter;
import com.itravel.server.interfaces.dal.repos.IDataRepository;
import com.itravel.server.services.aos.LatestActivitiesAOS;
@Path("/latestActivitiesByPosition")
public class LatestActivitiesByPosition {
	private static IDataRepository<ActivitiesEntity> dataRepo = new UpcomingEventsDBRepository();
	private static ObjectMapper om = new ObjectMapper();
	@GET
	@Produces(MediaType.APPLICATION_JSON +";charset=utf-8")
	public Response f(@QueryParam (value = "days") @DefaultValue("14") int days,
			@QueryParam (value = "pages") @DefaultValue("0") int page,
			@QueryParam (value = "useId") @DefaultValue("0") long userId,
			@QueryParam (value = "longitude") @DefaultValue("0.0") double longitude,
			@QueryParam (value = "latitude") @DefaultValue("0.0") double latitude
			) throws JsonProcessingException{
		Date currentTime = new Date();
		Date timeBegin = currentTime;
		Date timeEnd = currentTime;
		timeBegin = DateUtils.addDays(timeBegin, 0-days);
		timeEnd = DateUtils.addDays(timeEnd, days);
		//System.out.print("longitude:\t");
		//System.out.println(longitude);
		//System.out.print("latitude:\t");
		//System.out.println(latitude);
		String result = getInfoFromBaiduMap(longitude,latitude);
		//System.out.println(result);
		JsonNode rootNode = null;
		try{
			
			rootNode = om.readTree(result);
		}catch(Exception e){
			rootNode = null;
			System.out.println(e);
		}
		String cityName = rootNode.path("result").path("addressComponent").path("city").toString();
		cityName = cityName.replace("\"", "");
		List<LatestActivitiesAOS> aosEntities = null;
		if(cityName==null || cityName.length()==0){
			aosEntities = Lists.newArrayList();
		}else{
			IFilter<ActivitiesEntity> filter = ActivitiesDBFilter.createTimeFilterByCityName(timeBegin, timeEnd, page,cityName);
			List<ActivitiesEntity> entities = dataRepo.filterBy(filter);
			aosEntities  = getAos(entities);
		}
		return Response.ok().entity(om.writeValueAsString(aosEntities)).build();
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
	
	
	private List<LatestActivitiesAOS> getAos(List<ActivitiesEntity> entities){
		List<LatestActivitiesAOS> aosEntities = new ArrayList<LatestActivitiesAOS>();
		for(int i=0;i<entities.size();i++){
			ActivitiesEntity tmpEntity = entities.get(i);
			LatestActivitiesAOS latestActivityAos = new LatestActivitiesAOS(tmpEntity);
			aosEntities.add(latestActivityAos);
		}
		return aosEntities;
	}
	
}
