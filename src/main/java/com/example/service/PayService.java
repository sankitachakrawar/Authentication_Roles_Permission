package com.example.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;

import com.example.entities.Orders;
import com.razorpay.Order;

public interface PayService {

	Order createRazorPayOrder(String amount, HttpServletRequest request) throws Exception;

	List<Orders> getAllOrders();
	
	Orders updatePayment(@RequestBody Map<String, Object> data);
	
}
