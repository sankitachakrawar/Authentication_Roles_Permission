package com.example.serviceImpl;

import java.awt.desktop.UserSessionEvent;

import java.security.Principal;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Orders;
import com.example.entities.UserEntity;
import com.example.repository.PayRepository;
import com.example.repository.UserRepository;
import com.example.service.PayService;
import com.example.utils.JwtTokenUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class PayServiceImpl implements PayService{

	//private RazorpayClient client;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PayRepository payRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Order createRazorPayOrder(String amount,String token) throws Exception {
		
		int amt=Integer.parseInt(amount);
		RazorpayClient razorpayClient=new RazorpayClient("rzp_test_R9M7Z1BLAK9M6i","rgjrq8doiivTeAdDhl4yzOK6");
		UserEntity entity =new UserEntity();
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("amount", amt*100);
		jsonObject.put("currency","INR");
		jsonObject.put("receipt","txn_123456");
		jsonObject.put("payment_capture",1);
		
		System.out.println("data>> "+jsonObject);
		
		Order order=razorpayClient.Orders.create(jsonObject);
		System.out.println("order>>"+order);
		  
		Orders orderRequest=new Orders();
		orderRequest.setAmount(order.get("amount")+"");
		orderRequest.setStatus("created");
		orderRequest.setReceipt(order.get("receipt"));
		orderRequest.setOrderId(order.get("id"));
		orderRequest.setAmount_due(order.get("amount_due"));
		orderRequest.setAmount_paid(order.get("amount_paid"));
		orderRequest.setCurrency(order.get("currency"));
		
		String email=null;
		email=jwtTokenUtil.getEmailFromToken(token);
		
		UserEntity userEntity = userRepository.getUserByEmail(email);
		orderRequest.setUid(userEntity);
			System.out.println("email>>"+email);
		
		this.payRepository.save(orderRequest);
		
			return order;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public Order createRazorPayOrder(String amount,Principal principal) throws Exception {
//	int amt=Integer.parseInt(amount);
//		RazorpayClient razorpayClient=new RazorpayClient("rzp_test_R9M7Z1BLAK9M6i","rgjrq8doiivTeAdDhl4yzOK6");
//		
//		JSONObject jsonObject=new JSONObject();
//		jsonObject.put("amount", amt*100);
//		jsonObject.put("currency","INR");
//		jsonObject.put("receipt","txn_123456");
//		jsonObject.put("payment_capture",1);
//		
//		System.out.println("data>> "+jsonObject);
//		
//		Order order=razorpayClient.Orders.create(jsonObject);
//		System.out.println("order>>"+order);
//		  String name = principal.getName();
//		OrderRequest orderRequest=new OrderRequest();
//		orderRequest.setAmount(order.get("amount")+"");
//		orderRequest.setStatus("created");
//		orderRequest.setReceipt(order.get("receipt"));
//		orderRequest.setOrderId(order.get("id"));
//		orderRequest.setUserEntity(order.get(name));
//		
//		this.payRepository.save(orderRequest);
//		
//			return order;
//		
//	}

	
}
