package com.weiminw.tools;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.weiminw.business.aos.Hotel;
import com.weiminw.business.managers.HotelManager;
import com.weiminw.travel.dao.impls.pos.HotelEntity;
import com.weiminw.travel.dao.impls.pos.HotelLocationEntity;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.managers.IHotelManager;

/**
 * 根据酒店名获取百度的经纬度，并存储
 * @author weiminw
 *
 */
public class LngLatCorrects {
	private static final class BaiduGeoHelper {
		private static final HttpClient client = HttpClients.createDefault();
		private static final String requestPath = "http://api.map.baidu.com/geocoder/v2/";
		private static final String output = "json";
		private static final String ak = "vnWOlOtrAtGQYENrRmwr0KjK";
		private final IHotel hotel;
		private static final Gson gson = new Gson();
		private static final HotelManager geoManager = new HotelManager();
		public BaiduGeoHelper(IHotel hotel) {
			this.hotel = hotel;
		}
		
		private Map result(String ss) throws ClientProtocolException, IOException, URISyntaxException{
			URI uri= this.makeURI(ss);
			HttpGet getMethod = new HttpGet(uri);
			HttpResponse response = client.execute(getMethod);
			response.getEntity();
			String json = EntityUtils.toString(response.getEntity(),"UTF-8");
			Map map = gson.fromJson(json, Map.class);
			if(map == null){
				return null;
			}
			else {
				if(!map.containsKey("result")){
					return null;
				}
				else {
					Map result = (Map) map.get("result");
					if(!result.containsKey("confidence")){
						return null;
					}
					else {
						return map;
					}
				}
			}
		}
		public IHotel get(){
			try{
				double confidence = 0.0;
				Map map = null;
				Map map1 = this.result(this.hotel.getAddress());
				Map map2 = this.result(this.hotel.getName());
				if(map1!=null){
					double tmp = (Double) ((Map)map1.get("result")).get("confidence");
					if(tmp >confidence){
						confidence = tmp;
						map = map1;
					}
				}
				if(map2!=null){
					double tmp = (Double) ((Map)map2.get("result")).get("confidence");
							if(tmp >confidence){
								confidence = tmp;
								map = map2;
							}
					
				}
				if(map.containsKey("result")){
					Map result = (Map) map.get("result");
					result.put("name", this.hotel.getName());
					if(result!=null&&result.containsKey("location")){
						Map<Double,Double> location = (Map) result.get("location");
						if(location!=null){
							double lng = (double) location.get("lng");
							double lat = (double) location.get("lat");
							System.out.println(map);
							IHotel newhotel = hotel.setLongitude(lng).setLatitude(lat);
							return newhotel;
						}
						else {
							System.err.println(this.hotel);
						}
					}
					
				}
			}
			catch(Exception e){
				System.err.println(this.hotel.getId()+""+e);
			}
			return null;
		}
		private URI makeURI(String address) throws URISyntaxException{
				URIBuilder uriBuilder = new URIBuilder(this.requestPath);
				uriBuilder.addParameter("output", this.output)
				.addParameter("ak", this.ak)
				.addParameter("address", address)
				.addParameter("city", hotel.getCityName());
			return uriBuilder.build();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IHotelManager manager = HotelManager.create();
		List<String> ids = Lists.newArrayList();
		try {
			ids = Resources.readLines(Resources.getResource("hotels.txt"), Charsets.UTF_8);
//			ids = Files.readLines(new File("/hotels.txt"), );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		long hotelId = 10000442;
		for(int i = 10000 ;i<10001;i ++) {
			IHotel hotel = manager.getHotelById(Long.valueOf(ids.get(i)));
			
			if(hotel!=IHotel.NONE){
				hotel = new BaiduGeoHelper(hotel).get();
				if(hotel != null)
					manager.updateHotel(hotel);
				
			}
			
			
		}
	}

}
