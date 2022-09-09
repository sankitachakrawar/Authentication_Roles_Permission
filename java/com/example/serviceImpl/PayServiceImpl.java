package com.example.serviceImpl;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entities.Orders;
import com.example.repository.PayRepository;
import com.example.repository.UserRepository;
import com.example.service.PayService;
import com.example.utils.JwtTokenUtil;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Service
public class PayServiceImpl implements PayService{

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private PayRepository payRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Order createRazorPayOrder(String amount, HttpServletRequest request) throws Exception {
		
		int amt=Integer.parseInt(amount);
		RazorpayClient razorpayClient=new RazorpayClient("rzp_test_R9M7Z1BLAK9M6i","rgjrq8doiivTeAdDhl4yzOK6");
		//UserEntity entity =new UserEntity();
		
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
		orderRequest.setPaymentId(null);
		
		//final String requestTokenHeader = request.getHeader("Authorization");
		//String email = null;
		//String jwtToken = null;
		
		//jwtToken = requestTokenHeader.substring(7);
		//email = jwtTokenUtil.getEmailFromToken(jwtToken);
	
		//UserEntity userEntity = userRepository.getUserByEmail(email);
		//orderRequest.setUid(userEntity);
		
		this.payRepository.save(orderRequest);
		
			return order;
	}

	@Override
	public List<Orders> getAllOrders() {

		return payRepository.findAll();
	}

	@Override
	public Orders executePayment(Orders obj) {
		
		Orders object=this.payRepository.findByOrderId(obj.getOrderId());
		System.out.println("order1>>"+obj.getOrderId());
		object.setPaymentId(obj.getPaymentId());
		object.setSignature(obj.getSignature());
		
		Orders order2=this.payRepository.save(object);
		System.out.println("Order2>>"+order2);
		return order2;

	}


}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



//	Orders order=new Orders();
//	order.setOrderId(orders.getOrderId());
//	order.setPaymentId(orders.getPaymentId());
//	order.setSignature(orders.getSignature());
	
	//payRepository.findByOrderId(orders.getOrderId());
		

	

	

	
//@Override
//public Orders updatePayment(@RequestBody Map<String, Object> data) {
//
//	Orders order=this.payRepository.findByOrderId(data.get("orderId").toString());
//	order.setPaymentId((String) data.get("paymentId"));
//	order.setStatus((String) data.get("status"));
//	this.payRepository.save(order);
//	System.out.println(data);
//	return order;
//}	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

	
