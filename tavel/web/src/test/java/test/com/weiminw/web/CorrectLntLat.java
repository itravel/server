package test.com.weiminw.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Maps;
import com.weiminw.business.managers.HotelManager;
import com.weiminw.travel.dao.impls.pos.HotelEntity;
import com.weiminw.travel.interfaces.daos.IHotel;
import com.weiminw.travel.interfaces.managers.IHotelManager;

public class CorrectLntLat {
	public class ResultWrapper {
		public String status;
		public Map<String,Object> result;
	}
	static CloseableHttpClient httpclient = HttpClients.createDefault();
	public static final Logger logger_info = LogManager.getLogger("info");
	public static final Logger logger_error = LogManager.getLogger("error");
	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException  {
		/*IHotelManager manager = HotelManager.create();
		for(IHotel hotel:manager.getHotels()){
			HotelEntity po = (HotelEntity) hotel;
			try {
				Map<String,Double> lntlat = getLngLat(hotel.getAddress());
				if(lntlat!=null){
					po.setLatitude(lntlat.get("lng"));
					po.setLatitude(lntlat.get("lat"));
//					manager.updateHotel(hotel)(po);
					logger_info.info("id = "+hotel.getId()+",lng = "+lntlat.get("lng")+",lat = "+lntlat.get("lat"));
				}
				else {
					logger_error.info("id = "+hotel.getId()+",address = "+hotel.getName());
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
//		HotelEntity po = (HotelEntity) manager.getHotelById(10000893L);
//		Map<String,Double> lntlat = getLngLat(po.getName());
//		po.setLongtitude(lntlat.get("lng"));
//		po.setLatitude(lntlat.get("lat"));
//		System.out.println(po);
//		manager.updateHotel(po);
		
		
	}
	
	public static Map<String,Double> getLngLat (String address) throws URISyntaxException, ClientProtocolException, IOException{
		
		URIBuilder builder = new URIBuilder("http://api.map.baidu.com/geocoder");
		builder.addParameter("output", "json").addParameter("key", "vnWOlOtrAtGQYENrRmwr0KjK").addParameter("address", address);
		HttpGet getMethod = new HttpGet(builder.build());
		HttpResponse response = httpclient.execute(getMethod);
		String responseString = EntityUtils.toString(response.getEntity(), "GBK");
		System.out.println(responseString);
//		Gson gson = new Gson();
//		Map<String,Object> result = gson.fromJson(responseString,ResultWrapper.class).result;
//		
//		if (result == null||result.isEmpty()){
//			return null;
//		}
//		Map<String,Double> lntlat = (Map<String,Double>)gson.fromJson(responseString,ResultWrapper.class).result.get("location");
//		return lntlat;
		return null;
	}

}
