package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.weiminw.travel.interfaces.daos.IHotelLocation;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@Table(name="hotel")
@NamedQuery(name="HotelLocationEntity.findAll", query="SELECT h FROM HotelLocationEntity h")
public class HotelLocationEntity implements Serializable,IHotelLocation{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private double latitude;
	private double longitude;

	private HotelLocationEntity(long id, String name, double latitude,
			double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public HotelLocationEntity() {
	}
	
	public static HotelLocationEntity of(IHotelLocation hotelLocation) {
		return new HotelLocationEntity(
				hotelLocation.getId(),
				hotelLocation.getName(),
				hotelLocation.getLatitude(),
				hotelLocation.getLongitude()
			);
	}

	public long getId() {
		return this.id;
	}

	public IHotelLocation setId(long id) {
		this.id = id;
		return this;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public IHotelLocation setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}


	public double getLongitude() {
		return this.longitude;
	}

	public IHotelLocation setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}


	public String getName() {
		return this.name;
	}

	public IHotelLocation setName(String name) {
		this.name = name;
		return this;
	}


	

}