package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.OtpLogger;

public interface OtpLoggerRepository extends JpaRepository<OtpLogger, Long>{

}
