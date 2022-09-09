package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entities.OrderResponse;
import com.example.entities.Orders;
import com.example.repository.OrderResponseRepository;
import com.example.service.PayService;
import com.razorpay.Order;

@RestController
@RequestMapping("/api")
public class PayController {
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private OrderResponseRepository orderResponseRepository;

	@PostMapping("/orders")
	public OrderResponse createOrder(@RequestBody Orders orderRequest,HttpServletRequest request) throws Exception {
		
		OrderResponse orderResponse=new OrderResponse();
		System.out.println("Response>>"+orderResponse);
		Order order=payService.createRazorPayOrder(orderRequest.getAmount(),request);
		System.out.println("order>>"+orderRequest.getAmount());
		
		String orderId=order.get("id");
		orderResponse.setRazorPayOrderId(orderId);
		orderResponse.setPgName("RazorPay");
		
		OrderResponse orderResponse2=orderResponseRepository.save(orderResponse);
	
		return orderResponse2;
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders(){
		
		return new ResponseEntity<>(this.payService.getAllOrders(),HttpStatus.OK);
		
	}

	@PostMapping("/success")
	public ResponseEntity<?> executePayment(@RequestBody Orders orders,HttpServletRequest request)throws Exception{
		
		payService.executePayment(orders);
		System.out.println("orders>>"+orders);
		return new ResponseEntity<>(orders,HttpStatus.OK);
		
	}
	
}













//@PostMapping("/capture")
//public Payment captureOrder(String paymentId) throws Exception {
//
//	RazorpayClient razorpay = new RazorpayClient("rzp_test_R9M7Z1BLAK9M6i","rgjrq8doiivTeAdDhl4yzOK6");
//	paymentId = "pay_29QQoUBi66xm2f";
//	Orders orderRequest=new Orders();
//	JSONObject paymentRequest = new JSONObject();
//	paymentRequest.put("amount", orderRequest.getAmount());
//	paymentRequest.put("currency", "INR");
//        
//	Payment payment = razorpay.Payments.capture(paymentId, paymentRequest);
//	System.out.println("payment>>" +payment);
//	
//	//this.payRepository.save(orderRequest);
//
//	return payment;
//
//}








//@PostMapping("/update")
//public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data){
//
//
//	this.payService.updatePayment(data);
//	
//	return new ResponseEntity<>("success",HttpStatus.OK);
//}
