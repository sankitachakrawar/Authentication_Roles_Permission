package com.example.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="orders")
public class Orders {

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="orderId")
	private String orderId;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="amount_paid")
	private int amount_paid;
	
	@Column(name="amount_due")
	private int amount_due;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="receipt")
	private String receipt;
	
	@Column(name="status")
	private String status;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private Date createdAt;
	
	@Column(name="paymentId")
	private String paymentId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity uid;
	
	private String signature;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public int getAmount_paid() {
		return amount_paid;
	}

	public void setAmount_paid(int amount_paid) {
		this.amount_paid = amount_paid;
	}

	public int getAmount_due() {
		return amount_due;
	}

	public void setAmount_due(int amount_due) {
		this.amount_due = amount_due;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public UserEntity getUid() {
		return uid;
	}

	public void setUid(UserEntity uid) {
		this.uid = uid;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Long id, String orderId, String amount, int amount_paid, int amount_due, String currency,
			String receipt, String status, Date createdAt, String paymentId, UserEntity uid, String signature) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.amount_paid = amount_paid;
		this.amount_due = amount_due;
		this.currency = currency;
		this.receipt = receipt;
		this.status = status;
		this.createdAt = createdAt;
		this.paymentId = paymentId;
		this.uid = uid;
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderId=" + orderId + ", amount=" + amount + ", amount_paid=" + amount_paid
				+ ", amount_due=" + amount_due + ", currency=" + currency + ", receipt=" + receipt + ", status="
				+ status + ", createdAt=" + createdAt + ", paymentId=" + paymentId + ", uid=" + uid + ", signature="
				+ signature + "]";
	}	
	
}
