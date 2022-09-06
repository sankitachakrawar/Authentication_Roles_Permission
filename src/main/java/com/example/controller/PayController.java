package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.OrderResponse;
import com.example.entities.Orders;
import com.example.repository.PayRepository;
import com.example.repository.UserRepository;
import com.example.service.PayService;
import com.razorpay.Order;

@RestController
@RequestMapping("/api")
public class PayController {

	@Autowired
	private PayRepository payRepository;
	
	@Autowired
	private UserRepository userRepository;


	
	@Autowired
	private PayService payService;

	@PostMapping("/createOrder")
	public OrderResponse createOrder(@RequestBody Orders orderRequest) throws Exception {
		
		OrderResponse orderResponse=new OrderResponse();
		System.out.println("Response>>"+orderResponse);
		Order order=payService.createRazorPayOrder(orderRequest.getAmount(),orderRequest.getToken());
		System.out.println("order>>"+orderRequest.getAmount());
		
		String orderId=order.get("id");
		orderResponse.setRazorPayOrderId(orderId);
		orderResponse.setPgName("RazorPay");
		
	
		return orderResponse;
	}
	
}







//@PostMapping("/createOrder")
//public Order createRazorPayOrder(@RequestBody Map<String, Object> data, String amount) throws Exception {
//	int amt=Integer.parseInt(data.get("amount").toString());
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
//		  
//		Orders orderRequest=new Orders();
//		orderRequest.setAmount(order.get("amount")+"");
//		orderRequest.setStatus("created");
//		orderRequest.setReceipt(order.get("receipt"));
//		orderRequest.setOrderId(order.get("id"));
//		orderRequest.setAmount_due(order.get("amount_due"));
//		orderRequest.setAmount_paid(order.get("amount_paid"));
//		
//		
//		this.payRepository.save(orderRequest);
//		
//			return order;
//		
//	}
//





















