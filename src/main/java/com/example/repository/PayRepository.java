package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entities.Orders;
import com.razorpay.Order;

public interface PayRepository extends JpaRepository<Orders, Long>{

	Orders findByOrderId(String orderId);

}
