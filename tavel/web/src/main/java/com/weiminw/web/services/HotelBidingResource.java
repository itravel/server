package com.weiminw.web.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.weiminw.business.aos.HotelBidingResponses;
import com.weiminw.business.aos.HotelBidings;
import com.weiminw.business.managers.AccountManager;
import com.weiminw.business.managers.HotelBidingManager;
import com.weiminw.business.managers.HotelManager;
import com.weiminw.business.managers.PublishManager;
import com.weiminw.travel.dao.impls.pos.HotelBidingEntity;
import com.weiminw.travel.interfaces.daos.IHotelBiding;
import com.weiminw.travel.interfaces.daos.IHotelBidingResponse;
import com.weiminw.travel.interfaces.daos.IHotelLocation;
import com.weiminw.travel.interfaces.daos.IPublishManager;
import com.weiminw.travel.interfaces.daos.ISeller;
import com.weiminw.travel.interfaces.daos.IUser;
import com.weiminw.travel.interfaces.managers.IHotelBidingManager;
import com.weiminw.web.services.json.JsonConverter;

@Path("/hotels/biding")
public class HotelBidingResource {
	@Context
	UriInfo uriInfo;
	HotelManager hotelManager = HotelManager.create();
	AccountManager accountManager = AccountManager.create();
	IHotelBidingManager hotelBidManager = HotelBidingManager.create();
	IPublishManager publishManager = PublishManager.create();
	
	private final static Logger logger = LogManager.getLogger(HotelBidingResource.class);
	private final Stopwatch stopwatch = Stopwatch.createUnstarted();
	
	@POST
	@Consumes("application/x-www-form-urlencoded") 
	public Response createHotelBiding(
			@FormParam("userId") long userId,/*�û�id*/
			@FormParam("lowPrice") double lowPrice,/*��ͼ۸�*/
			@FormParam("highPrice") double highPrice,/*��߼۸�*/
			@FormParam("checkIn") Date checkIn,/*��סʱ��*/
			@FormParam("checkOut") Date checkOut,/*���ʱ��*/
			@FormParam("roomNum") int roomNum, /*��������*/
			@FormParam("lnt") double lnt,/*�û�ָ������*/
			@FormParam("lat") double lat /*�û�ָ��ά�� */) {
		//�����ܱ߾Ƶ�
		List<IHotelLocation> hotels = this.hotelManager.getHotelLocation(lnt, lat, 5, 0, 50);
		logger.debug("�ҵ����� "+hotels.size()+" �ҾƵ�");
		//�����ܱ߾Ƶ��Ӧ������
		stopwatch.start();	
		IHotelBiding hotelBiding = HotelBidings.of(userId,checkIn,checkOut,roomNum,lowPrice,highPrice,lnt,lat) ;
		for(IHotelLocation hotel:hotels){
			List<ISeller> sellers = this.accountManager.getSellerByHid(hotel.getId());
			for(ISeller seller:sellers){
				publishManager.publish(seller,hotelBiding);
			}
		}
		return Response.created(uriInfo.getRequestUriBuilder().path(String.valueOf(hotelBiding.getId())).build()).build();
		
	}
	@GET
	@Path("{id}")
	public Response getHotelBiding(@PathParam("id") long id){
		IHotelBiding biding = this.hotelBidManager.getHotelBidingById(id);
		logger.debug(JsonConverter.convertToJson(new HotelBidingEntity()));
		return Response.ok(JsonConverter.convertToJson(biding)).build();
	}
	@POST
	@Path("{id}/response")
	@Consumes("application/x-www-form-urlencoded") 
	public Response createHotelBidingRsp(
			@PathParam("id") long bidingId,
			@FormParam("seller_id") long sellerId,
			@FormParam("hotel_id") long hotelId
			){
		
		IHotelBidingResponse hotelBidingResponse = HotelBidingResponses.of(bidingId,sellerId,hotelId);
		return Response.created(this.uriInfo.getRequestUriBuilder().path(String.valueOf(hotelBidingResponse.getId())).build()).build();
	}
	
//	@GET
//	@Path("{id}/response/{responseId}")
//	public Response getHotelBidingRsp(@PathParam("id") long bidingId,@PathParam("responseId") long responseId){
//		this.hotelBidManager.getHotelBidingResponse(long bidingId,long)
//	}
}
