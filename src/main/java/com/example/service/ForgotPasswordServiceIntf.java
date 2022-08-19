package com.example.service;

import java.util.Date;

import com.example.entities.Forgot_password_request;
import com.example.exceptionHandling.ResourceNotFoundException;

public interface ForgotPasswordServiceIntf {

	public Forgot_password_request getRequest(String token) throws ResourceNotFoundException;

	public void createForgotPasswordRequest(Long id, String token, Date expireAt);

	public void markRequestAsSuccess(Long id, String token) throws ResourceNotFoundException;

	public void markRequestAsExpire(Long id, String token) throws ResourceNotFoundException;

}
