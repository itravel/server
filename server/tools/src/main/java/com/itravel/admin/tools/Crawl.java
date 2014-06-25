package com.itravel.admin.tools;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

import com.itravel.server.dal.entities.EventCrawlOriginEntity;

public class Crawl {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("dal");
		EntityManager manager = emf.createEntityManager();
		
		HttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://trip.elong.com/beijing/jieqing/");
		HttpResponse rsp = client.execute(get);
		String rspStr = EntityUtils.toString(rsp.getEntity(),"utf-8");
		Document doc = Jsoup.parse(rspStr);
		Elements ele = doc.select("div[class=jj-sum]");
		ele.select("br").remove();
		manager.getTransaction().begin();
		for(Element el:ele.first().children()){
			EventCrawlOriginEntity entity = new EventCrawlOriginEntity();
			entity.setTitle(el.text());
			String ss = el.nextSibling().toString();
			entity.setAbstractContent(ss);
			System.out.println(new String(ss.getBytes("GBK"),"UTF-8"));
			entity.setEventDate(el.nextSibling().nextSibling().toString());
			entity.setEventAddress(el.nextSibling().nextSibling().nextSibling().toString());
			System.out.println(entity);
			
			manager.persist(entity);
//			System.out.println(el.text());
//			System.out.println(el.nextSibling());
//			System.out.println(el.nextSibling().nextSibling());
//			System.out.println(el.nextSibling().nextSibling().nextSibling());
//			System.out.println(el.child(1).nextSibling());
		}
		manager.getTransaction().commit();
	}
}
