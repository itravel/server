package com.weiminw.travel.dao.impls.pos;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weiminw.travel.interfaces.daos.ISeller;



/**
 * The persistent class for the seller database table.
 * 
 */
@Entity
@Table(name="seller")
@NamedQueries(value = { 
		@NamedQuery(name="SellerEntity.findAll", query="SELECT u FROM SellerEntity u"),
		@NamedQuery(name="SellerEntity.findAllByHid", query="SELECT u FROM SellerEntity u where u.hid = :hid"),
		@NamedQuery(name="SellerEntity.findAllByName", query="SELECT u FROM SellerEntity u where u.name = :name"),
})
public class SellerEntity implements Serializable,ISeller {
	private static final long serialVersionUID = 1L;
	static final ObjectMapper m = new ObjectMapper();
	public static final String QUERY_BY_HID = "SellerEntity.findAllByHid";
	public static final String QUERY_BY_NAME = "SellerEntity.findAllByName"; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Column(name="cell_phone")
	private String cellPhone;

	@Column(name="cloud_push_ck")
	private String cloudPushCk;

	@Column(name="cloud_push_tag")
	private String cloudPushTag;

	@Column(name="cloud_push_uk")
	private String cloudPushUk;

	@Column(name="fix_telephone")
	private String fixTelephone;

	private long hid;

	private String identify;

	@Column(name="identify_type")
	private byte identifyType;

	private String name;

	private String password;

	public SellerEntity() {
	}

	public long getId() {
		return this.id;
	}

	public ISeller setId(int id) {
		this.id = id;
		return this;
	}

	public String getCellPhone() {
		return this.cellPhone;
	}

	public ISeller setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
		return this;
	}

	public String getCloudPushCk() {
		return this.cloudPushCk;
	}

	public ISeller setCloudPushCk(String cloudPushCk) {
		this.cloudPushCk = cloudPushCk;
		return this;
	}

	public String getCloudPushTag() {
		return this.cloudPushTag;
	}

	public ISeller setCloudPushTag(String cloudPushTag) {
		this.cloudPushTag = cloudPushTag;
		return this;
	}

	public String getCloudPushUk() {
		return this.cloudPushUk;
	}

	public ISeller setCloudPushUk(String cloudPushUk) {
		this.cloudPushUk = cloudPushUk;
		return this;
	}

	public String getFixTelephone() {
		return this.fixTelephone;
	}

	public ISeller setFixTelephone(String fixTelephone) {
		this.fixTelephone = fixTelephone;
		return this;
	}

	public long getHid() {
		return this.hid;
	}

	public ISeller setHid(long hid) {
		this.hid = hid;
		return this;
	}

	public String getIdentify() {
		return this.identify;
	}

	public ISeller setIdentify(String identify) {
		this.identify = identify;
		return this;
	}

	public byte getIdentifyType() {
		return this.identifyType;
	}

	public ISeller setIdentifyType(byte identifyType) {
		this.identifyType = identifyType;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public ISeller setName(String name) {
		this.name = name;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public ISeller setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String toString() {
		
		try {
			return m.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	
	
	

}