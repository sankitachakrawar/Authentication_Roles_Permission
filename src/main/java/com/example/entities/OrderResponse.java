package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String razorPayOrderId;
	
	private String pgName;

	public String getRazorPayOrderId() {
		return razorPayOrderId;
	}
	public void setRazorPayOrderId(String razorPayOrderId) {
		this.razorPayOrderId = razorPayOrderId;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public OrderResponse() {
		super();
		
	}
	public OrderResponse(String secret_key, String razorPayOrderId, String secret_id, String pgName) {
		super();
		this.razorPayOrderId = razorPayOrderId;
		this.pgName = pgName;
	}
	
}
