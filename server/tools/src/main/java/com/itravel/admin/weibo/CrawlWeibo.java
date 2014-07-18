package com.itravel.admin.weibo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlWeibo {

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException {
		// TODO Auto-generated method stub
		HttpClient client = HttpClients.createDefault();
		URI authUri = new URIBuilder("https://api.weibo.com/oauth2/authorize")
		
		.setParameter("client_id", "2617199813")
		.setParameter("response_type", "code")
		.setParameter("redirect_uri", "http://api.weibo.com/2/friendships/friends.json")
		.build();
		HttpGet get = new HttpGet(authUri);
		HttpResponse rsp = client.execute(get);
		String rspStr = EntityUtils.toString(rsp.getEntity(),"utf-8");
		System.out.println(rspStr);
		URI uri = new URIBuilder("http://api.weibo.com/2/friendships/friends.json")
		
		.setParameter("source", "979389387")
		.setParameter("uid", "2617199813").build();
		get = new HttpGet(uri);
		get.addHeader("Authorization", "OAuth2 2.00NOVHrCBv6REBf60ed50369z8uuWB");
		
//		HttpResponse rsp = client.execute(get);
//		String rspStr = EntityUtils.toString(rsp.getEntity(),"utf-8");
//		System.out.println(rspStr);
	}

}
