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

	
	private long id;
	
	
	private long userId;

	
	private long activityId;

	
	private int actualPayment;

	
	private int adultNumber;

	
	private int amountPayable;

	
	private Date bookDate;

	
	private int childrenNumber;

	
	private String contactorMail;

	
	private String contactorName;

	
	private String contactorPhone;

	
	private Date gmtCreate;

	
	private Date gmtModified;

	private String remarks;

	private int status;

	
	private List<OrderTravelerEntity> orderTravelers;

	public OrderEntity() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name="users_id")
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	@Column(name="activity_id")
	public long getActivityId() {
		return this.activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}
	@Column(name="actual_payment")
	public int getActualPayment() {
		return this.actualPayment;
	}

	public void setActualPayment(int actualPayment) {
		this.actualPayment = actualPayment;
	}
	@Column(name="adult_number")
	public int getAdultNumber() {
		return this.adultNumber;
	}

	public void setAdultNumber(int adultNumber) {
		this.adultNumber = adultNumber;
	}
	@Column(name="amount_payable")
	public int getAmountPayable() {
		return this.amountPayable;
	}

	public void setAmountPayable(int amountPayable) {
		this.amountPayable = amountPayable;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="book_date")
	public Date getBookDate() {
		return this.bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	@Column(name="children_number")
	public int getChildrenNumber() {
		return this.childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}
	@Column(name="contactor_mail")
	public String getContactorMail() {
		return this.contactorMail;
	}

	public void setContactorMail(String contactorMail) {
		this.contactorMail = contactorMail;
	}
	@Column(name="contactor_name")
	public String getContactorName() {
		return this.contactorName;
	}

	public void setContactorName(String contactorName) {
		this.contactorName = contactorName;
	}
	@Column(name="contactor_phone")
	public String getContactorPhone() {
		return this.contactorPhone;
	}

	public void setContactorPhone(String contactorPhone) {
		this.contactorPhone = contactorPhone;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_create")
	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="gmt_modified")
	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	@Column(name="remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name="status")
	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	//bi-directional many-to-one association to OrderTravelerEntity
	@OneToMany(mappedBy="order")
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