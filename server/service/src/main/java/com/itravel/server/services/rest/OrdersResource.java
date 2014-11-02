package com.itravel.server.services.rest;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.itravel.server.dal.entities.OrderEntity;
import com.itravel.server.dal.entities.OrderTravelerEntity;
import com.itravel.server.dal.managers.OrderEntityManager;

@Path("/orders")
public class OrdersResource {
	public static final ObjectMapper mapper = new ObjectMapper();
	private static final Logger logger = LogManager
			.getLogger(OrdersResource.class);
	private static final OrderJsonParser parser = new OrderJsonParser();
	private static final OrderEntityManager manager = new OrderEntityManager();

	@Path("/")
	@POST
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response createOrder(String basicOrderInfo) {
		if(basicOrderInfo == null||basicOrderInfo.isEmpty()){
			return Response.serverError().build();
		}
		JsonNode rootNode;
		try {
			rootNode = mapper.readValue(basicOrderInfo, JsonNode.class);
			long userId = rootNode.get("userId").longValue();
			long activityId = rootNode.get("activityId").longValue();
			OrderEntity order = new OrderEntity();
			Date now = new Date();
			order.setGmtCreate(now);
			order.setGmtModified(now);
			order.setId(System.nanoTime());
			order.setActivityId(activityId);
			order.setUserId(userId);
			String orderJson = "";
			orderJson = mapper.writeValueAsString(order);

			// logger.error(e);
			// return Response.serverError().entity(e.getMessage()).build();

			return Response.ok().entity(orderJson).build();
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			return Response.serverError().entity(e1.getMessage()).build();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			return Response.serverError().entity(e1.getMessage()).build();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error(e1);
			return Response.serverError().entity(e1.getMessage()).build();
		}

	}

	@Path("/")
	@PUT
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateOrder(String order) {
		OrderEntity orderEntity = Optional.of(order).transform(parser).get();
		boolean success = manager.saveOrder(orderEntity);
		if (success) {
			return Response.ok().entity(orderEntity).build();
		}
		return Response.serverError().build();
	}
	
	@Path("user/{userId}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getOrderByUserId(@PathParam("userId") long userId){
		if(userId<=0){
			return Response.status(Status.NOT_FOUND).build();
		}
		List<OrderEntity> userOrders = manager.getOrdersByUser(userId);
		String json;
		try {
			json = mapper.writeValueAsString(userOrders);
			return Response.ok().entity(json).build();
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return Response.serverError().entity(e.getMessage()).build();
		}
		
	}
	
	@Path("/{orderId}/status")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response changeOrderStatus(@PathParam("orderId") long orderId,@FormParam("action") String action){
		
		return Response.ok().build();
	}
	private static class OrderJsonParser implements
			Function<String, OrderEntity> {

		public OrderJsonParser() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public OrderEntity apply(String input) {
			// TODO Auto-generated method stub
			try {
				JsonNode rootNode = mapper.readValue(input, JsonNode.class);
				return this.build(rootNode);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		private OrderEntity build(JsonNode rootNode) {
			OrderEntity orderEntity = new OrderEntity();
			long id = rootNode.get("id").longValue();
			long userId = rootNode.get("userId").longValue();
			long activityId = rootNode.get("activityId").longValue();
			int adultNumber = rootNode.get("adultNumber").intValue();
			int childrenNumber = rootNode.get("childrenNumber").intValue();
			// bootdate
			Date bookDate = new Date();
			try {
				String _bookDate = rootNode.get("bookDate").textValue();
				if(_bookDate!=null&&!_bookDate.isEmpty())
					bookDate = DateUtils.parseDate(_bookDate, "yyyy-MM-dd");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
			int amountPayable = rootNode.get("amountPayable").intValue();
			int actualPayment = rootNode.get("actualPayment").intValue();
			String contactorName = rootNode.get("contactorName").textValue();
			String contactorPhone = rootNode.get("contactorPhone").textValue();
			String contactorMail = rootNode.get("contactorMail").textValue();

			orderEntity.setId(id);
			orderEntity.setUserId(userId);
			orderEntity.setActivityId(activityId);
			orderEntity.setAdultNumber(adultNumber);
			orderEntity.setChildrenNumber(childrenNumber);
			orderEntity.setAmountPayable(amountPayable);
			orderEntity.setActualPayment(actualPayment);
			orderEntity.setContactorName(contactorName);
			orderEntity.setContactorPhone(contactorPhone);
			orderEntity.setContactorMail(contactorMail);
			orderEntity.setBookDate(bookDate);
			orderEntity.setGmtCreate(new Date());
			orderEntity.setGmtModified(new Date());
			
			Iterator<JsonNode> orderTravelers = rootNode.get("orderTravelers")
					.elements();
			while (orderTravelers.hasNext()) {
				JsonNode orderTraveler = orderTravelers.next();
				long tid = orderTraveler.get("id").longValue();
				String travelerId = orderTraveler.get("travelerId").textValue();
				String travelerName = orderTraveler.get("travelerName")
						.textValue();
				String travelerMail = orderTraveler.get("travelerMail")
						.textValue();
				String travelerPhone = orderTraveler.get("travelerPhone")
						.textValue();

				OrderTravelerEntity travelerEntity = new OrderTravelerEntity();
				travelerEntity.setId(tid);
				travelerEntity.setTravelerId(travelerId);
				travelerEntity.setTravelerName(travelerName);
				travelerEntity.setTravelerPhone(travelerPhone);
				travelerEntity.setOrder(orderEntity);
				orderEntity.addOrderTraveler(travelerEntity);
			}

			return orderEntity;
		}
	}
	
}
