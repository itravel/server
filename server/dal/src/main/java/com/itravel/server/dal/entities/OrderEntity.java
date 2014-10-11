package com.itravel.server.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="OrderEntity.findAll", query="SELECT o FROM OrderEntity o")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="users_id")
	private long userId;

	@Column(name="activity_id")
	private long activityId;

	@Column(name="actual_payment")
	private int actualPayment;

	@Column(name="adult_number")
	private int adultNumber;

	@Column(name="amount_payable")
	private int amountPayable;

	@Temporal(TemporalType.DATE)
	@Column(name="book_date")
	private Date bookDate;

	@Column(name="children_number")
	private int childrenNumber;

	@Column(name="contactor_mail")
	private String contactorMail;

	@Column(name="contactor_name")
	private String contactorName;

	@Column(name="contactor_phone")
	private String contactorPhone;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_create")
	private Date gmtCreate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_modified")
	private Date gmtModified;

	private String remarks;

	private int status;

	//bi-directional many-to-one association to OrderTravelerEntity
	@OneToMany(mappedBy="order")
	private List<OrderTravelerEntity> orderTravelers;

	public OrderEntity() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

	public int getActualPayment() {
		return this.actualPayment;
	}

	public void setActualPayment(int actualPayment) {
		this.actualPayment = actualPayment;
	}

	public int getAdultNumber() {
		return this.adultNumber;
	}

	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}

	public int getAmountPayable() {
		return this.amountPayable;
	}

	public void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}

	public Date getBookDate() {
		return this.bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public int getChildrenNumber() {
		return this.childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public String getContactorMail() {
		return this.contactorMail;
	}

	public void setContactorMail(String contactorMail) {
		this.contactorMail = contactorMail;
	}

	public String getContactorName() {
		return this.contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}

	public String getContactorPhone() {
		return this.contactorPhone;
	}

	public void setContactorPhone(String contactorPhone) {
		this.contactorPhone = contactorPhone;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<OrderTravelerEntity> getOrderTravelers() {
		return this.orderTravelers;
	}

	public void setOrderTravelers(List<OrderTravelerEntity> orderTravelers) {
		this.orderTravelers = orderTravelers;
	}

	public OrderTravelerEntity addOrderTraveler(OrderTravelerEntity orderTraveler) {
		getOrderTravelers().add(orderTraveler);
		orderTraveler.setOrder(this);

		return orderTraveler;
	}

	public OrderTravelerEntity removeOrderTraveler(OrderTravelerEntity orderTraveler) {
		getOrderTravelers().remove(orderTraveler);
		orderTraveler.setOrder(null);

		return orderTraveler;
	}

}