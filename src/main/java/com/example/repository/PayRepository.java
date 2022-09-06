package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Orders;

public interface PayRepository extends JpaRepository<Orders, Long>{



}
