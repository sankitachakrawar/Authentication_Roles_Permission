package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.ErrorLoggerEntity;



public interface ErrorLoggerRepository extends JpaRepository<ErrorLoggerEntity, Long> {
}
