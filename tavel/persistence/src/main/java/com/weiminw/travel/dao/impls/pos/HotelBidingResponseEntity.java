package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.weiminw.travel.interfaces.daos.IHotelBidingResponse;

import java.math.BigInteger;


/**
 * The persistent class for the hotel_biding_response database table.
 * 
 */
@Entity
@Table(name="hotel_biding_response")
@NamedQuery(name="HotelBidingResponseEntity.findAll", query="SELECT h FROM HotelBidingResponseEntity h")
public class HotelBidingResponseEntity implements Serializable,IHotelBidingResponse {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="hotel_biding_id")
	private long hotelBidingId;

	@Column(name="seller_id")
	private long sellerId;
	
	

	public HotelBidingResponseEntity() {
	}

	public long getId() {
		return this.id;
	}

	public IHotelBidingResponse setId(long id) {
		this.id = id;
		return this;
	}

	public long getHotelBidingId() {
		return this.hotelBidingId;
	}

	public IHotelBidingResponse setHotelBidingId(long hotelBidingId) {
		this.hotelBidingId = hotelBidingId;
		return this;
	}

	public long getSellerId() {
		return this.sellerId;
	}

	public IHotelBidingResponse setSellerId(long sellerId) {
		this.sellerId = sellerId;
		return this;
	}


	@Override
	public long getHotelId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IHotelBidingResponse setHotelId(long hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

}