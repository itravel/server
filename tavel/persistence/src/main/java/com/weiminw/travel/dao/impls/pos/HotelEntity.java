package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.weiminw.travel.interfaces.daos.IHotel;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@Table(name="hotel")
@NamedQuery(name="HotelEntity.findAll", query="SELECT h FROM HotelEntity h")
public class HotelEntity implements Serializable,IHotel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String address;

	private int city;

	private String description;

	private double latitude;

	private double longitude;

	private String name;

	private String pictures;

	private int province;

	private String telephone;
	
	
	public static HotelEntity of(IHotel hotel){
		return new HotelEntity(
				hotel.getId(),
				hotel.getAddress(),
				hotel.getCity(),
				hotel.getDescription(),
				hotel.getLatitude(),
				hotel.getLongitude(),
				hotel.getName(),
				null,
				hotel.getProvince(),
				hotel.getTelephone()
				);
	}

	private HotelEntity(long id, String address, int city, String description,
			double latitude, double longitude, String name, String pictures,
			int province, String telephone) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.pictures = pictures;
		this.province = province;
		this.telephone = telephone;
	}
	
	public HotelEntity(){
		
	}
	

	public long getId() {
		return this.id;
	}

	public IHotel setId(long id) {
		this.id = id;
		return this;
	}

	public String getAddress() {
		return this.address;
	}

	public IHotel setAddress(String address) {
		this.address = address;
		return this;
	}

	public int getCity() {
		return this.city;
	}

	public IHotel setCity(int city) {
		this.city = city;
		return this;
	}

	public String getDescription() {
		return this.description;
	}

	public IHotel setDescription(String description) {
		this.description = description;
		return this;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public IHotel setLatitude(double latitude) {
		this.latitude = latitude;
		return this;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public IHotel setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public IHotel setName(String name) {
		this.name = name;
		return this;
	}

	public String getPictures() {
		return this.pictures;
	}

	public IHotel setPictures(String pictures) {
		this.pictures = pictures;
		return this;
	}

	public int getProvince() {
		return this.province;
	}

	public IHotel setProvince(int province) {
		this.province = province;
		return this;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public IHotel setTelephone(String telephone) {
		this.telephone = telephone;
		return this;
	}

	@Override
	public String getCityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProvinceName() {
		// TODO Auto-generated method stub
		return null;
	}

}