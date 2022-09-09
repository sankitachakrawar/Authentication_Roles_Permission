package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.SPRequestEntity;

public interface AuthRepository extends JpaRepository<SPRequestEntity, Long>{

}
