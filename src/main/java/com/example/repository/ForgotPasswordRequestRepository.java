package com.example.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Forgot_password_request;




@Repository
public interface ForgotPasswordRequestRepository extends JpaRepository<Forgot_password_request, Long> {

	Optional<Forgot_password_request> getByTokenOrderByIdDesc(String token);

	Optional<Forgot_password_request> getByTokenAndIdOrderByIdDesc(String token, Long id);



}
