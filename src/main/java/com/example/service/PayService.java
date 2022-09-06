package com.example.service;




import java.util.Map;

import com.razorpay.Order;



public interface PayService {

	Order createRazorPayOrder(String amount,String token) throws Exception;

	//Order createRazorPayOrder(BigInteger amount) throws Exception;

	//Order createRazorPayOrder(String amount, Principal principal) throws Exception;

	//Order createRazorPayOrder(Map<String, Object> data, Principal principal) throws Exception;

}
