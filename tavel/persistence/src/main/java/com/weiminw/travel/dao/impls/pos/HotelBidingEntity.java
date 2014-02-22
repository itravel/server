package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.weiminw.travel.interfaces.daos.IHotelBiding;

import java.util.Date;


/**
 * The persistent class for the hotel_biding database table.
 * 
 */
@Entity
@Table(name="hotel_biding")
@NamedQuery(name="HotelBidingEntity.findAll", query="SELECT h FROM HotelBidingEntity h")
public class HotelBidingEntity implements Serializable, IHotelBiding {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_id")
	private long userId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="check_in")
	private Date checkIn;

	@Temporal(TemporalType.DATE)
	@Column(name="check_out")
	private Date checkOut;

	@Temporal(TemporalType.DATE)
	@Column(name="expire_time")
	private Date expireTime;

	@Column(name="high_price")
	private double highPrice;

	@Column(name="low_price")
	private double lowPrice;

	@Column(name="room_num")
	private int roomNum;

	@Column(name="send_phone")
	private byte sendPhone;

	private byte status;

	public HotelBidingEntity() {
		
	}
	
	

	public HotelBidingEntity(long id, long userId,Date checkIn, Date checkOut,
			Date expireTime, double highPrice, double lowPrice, int roomNum,
			byte sendPhone, byte status) {
		super();
		this.id = id;
		this.userId = userId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.expireTime = expireTime;
		this.highPrice = highPrice;
		this.lowPrice = lowPrice;
		this.roomNum = roomNum;
		this.sendPhone = sendPhone;
		this.status = status;
	}



	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getId()
	 */
	@Override
	public long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setId(long)
	 */
	@Override
	public IHotelBiding setId(long id) {
		this.id = id;
		return this;
	}

	public long getUserId() {
		return userId;
	}



	public IHotelBiding setUserId(long userId) {
		this.userId = userId;
		return this;
	}



	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getCheckIn()
	 */
	@Override
	public Date getCheckIn() {
		return this.checkIn;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setCheckIn(java.util.Date)
	 */
	@Override
	public IHotelBiding setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getCheckOut()
	 */
	@Override
	public Date getCheckOut() {
		return this.checkOut;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setCheckOut(java.util.Date)
	 */
	@Override
	public IHotelBiding setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getExpireTime()
	 */
	@Override
	public Date getExpireTime() {
		return this.expireTime;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setExpireTime(java.util.Date)
	 */
	@Override
	public IHotelBiding setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getHighPrice()
	 */
	@Override
	public double getHighPrice() {
		return this.highPrice;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setHighPrice(double)
	 */
	@Override
	public IHotelBiding setHighPrice(double highPrice) {
		this.highPrice = highPrice;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getLowPrice()
	 */
	@Override
	public double getLowPrice() {
		return this.lowPrice;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setLowPrice(double)
	 */
	@Override
	public IHotelBiding setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getRoomNum()
	 */
	@Override
	public int getRoomNum() {
		return this.roomNum;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setRoomNum(int)
	 */
	@Override
	public IHotelBiding setRoomNum(int roomNum) {
		this.roomNum = roomNum;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getSendPhone()
	 */
	@Override
	public byte getSendPhone() {
		return this.sendPhone;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setSendPhone(byte)
	 */
	@Override
	public IHotelBiding setSendPhone(byte sendPhone) {
		this.sendPhone = sendPhone;
		return this;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#getStatus()
	 */
	@Override
	public byte getStatus() {
		return this.status;
	}

	/* (non-Javadoc)
	 * @see com.weiminw.travel.persistence.impls.pos.IHotelBiding#setStatus(byte)
	 */
	@Override
	public IHotelBiding setStatus(byte status) {
		this.status = status;
		return this;
	}

}