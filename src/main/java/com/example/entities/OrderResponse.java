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
	//private String secret_key;
	private String razorPayOrderId;
	//private String secret_id;
	private String pgName;
//	public String getSecret_key() {
//		return secret_key;
//	}
//	public void setSecret_key(String secret_key) {
//		this.secret_key = secret_key;
//	}
	public String getRazorPayOrderId() {
		return razorPayOrderId;
	}
	public void setRazorPayOrderId(String razorPayOrderId) {
		this.razorPayOrderId = razorPayOrderId;
	}
//	public String getSecret_id() {
//		return secret_id;
//	}
//	public void setSecret_id(String secret_id) {
//		this.secret_id = secret_id;
//	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public OrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderResponse(String secret_key, String razorPayOrderId, String secret_id, String pgName) {
		super();
		//this.secret_key = secret_key;
		this.razorPayOrderId = razorPayOrderId;
		//this.secret_id = secret_id;
		this.pgName = pgName;
	}
	
	
	
}
