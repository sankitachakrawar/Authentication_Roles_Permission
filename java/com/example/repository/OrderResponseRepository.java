package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.OrderResponse;

public interface OrderResponseRepository extends JpaRepository<OrderResponse, Long>{

}
