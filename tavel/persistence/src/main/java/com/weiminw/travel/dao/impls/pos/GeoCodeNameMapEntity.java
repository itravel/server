package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the geo_code_name_map database table.
 * 
 */
@Entity
@Table(name="geo_code_name_map")
@NamedQueries(value = { 
		
		@NamedQuery(name="GeoCodeNameMapEntity.findAll", query="SELECT g FROM GeoCodeNameMapEntity g"),
		@NamedQuery(name="GeoCodeNameMapEntity.findProvince", query="SELECT g FROM GeoCodeNameMapEntity g where g.provinceCode = :provinceCode group by g.provinceCode ")	,	
		@NamedQuery(name="GeoCodeNameMapEntity.findCity", query="SELECT g FROM GeoCodeNameMapEntity g where g.cityCode = :cityCode group by g.cityCode ")		
		
})
public class GeoCodeNameMapEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="city_abb_name")
	private String cityAbbName;

	@Column(name="city_code")
	private int cityCode;

	@Column(name="city_name")
	private String cityName;

	@Column(name="district_abb_name")
	private String districtAbbName;

	@Column(name="district_code")
	private int districtCode;

	@Column(name="district_name")
	private String districtName;

	@Column(name="province_code")
	private int provinceCode;

	@Column(name="province_name")
	private String provinceName;

	public GeoCodeNameMapEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCityAbbName() {
		return this.cityAbbName;
	}

	public void setCityAbbName(String cityAbbName) {
		this.cityAbbName = cityAbbName;
	}

	public int getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictAbbName() {
		return this.districtAbbName;
	}

	public void setDistrictAbbName(String districtAbbName) {
		this.districtAbbName = districtAbbName;
	}

	public int getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getProvinceCode() {
		return this.provinceCode;
	}

	public void setProvinceCode(int provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return this.provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}