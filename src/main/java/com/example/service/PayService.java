package com.example.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.example.entities.Orders;
import com.razorpay.Order;

public interface PayService {

	Order createRazorPayOrder(String amount, HttpServletRequest request) throws Exception;

	List<Orders> getAllOrders();
	
	Orders executePayment(Orders obj);
	
}





//Orders updatePayment(@RequestBody Map<String, Object> data);