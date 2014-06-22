package com.itravel.admin.tools;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://trip.elong.com/chengdu/jieqing/");
		HttpResponse rsp = client.execute(get);
		String rspStr = EntityUtils.toString(rsp.getEntity(),"utf-8");
		Document doc = Jsoup.parse(rspStr);
		Elements ele = doc.select("div[class=jj-sum]");
		ele.select("br").remove();
		System.out.println(ele);
		for(Element el:ele.first().children()){
			System.out.println(el.nextSibling());
//			System.out.println(el.child(1).nextSibling());
		}
	}
}
