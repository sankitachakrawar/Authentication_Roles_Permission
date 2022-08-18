package com.example.exceptionHandling;

import java.io.IOException;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import com.example.dto.ErrorResponseDto;
import com.example.entities.ErrorLoggerEntity;
import com.example.entities.MethodEnum;
import com.example.repository.ErrorLoggerRepository;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	
	@Autowired
	ErrorLoggerRepository errorLoggerRepository;
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception, final HttpServletRequest request) {
		ExceptionResponse error=new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.callerURL(request.getRequestURI());
		return error;
		
	}
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorResponseDto handleAccessDeniedException(final AccessDeniedException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Access Denied");
		error.setMsgKey("accessDenied");
		return error;

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ResponseEntity <List<String>> handleValidationException(final MethodArgumentNotValidException exception) {

		List<String> details = new ArrayList<>();

		for (ObjectError error : exception.getBindingResult().getAllErrors()) {

			details.add(error.getDefaultMessage());

		}

		return new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody ErrorResponseDto handleMethodNotSupportException(final HttpRequestMethodNotSupportedException exception) {

		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Invalid request, Please check URL");
		error.setMsgKey("invalidRequest");
		return error;

	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponseDto handleException(final Exception exception, HttpServletRequest request) throws IOException {

		ErrorLoggerEntity errorRequest = new ErrorLoggerEntity();
		errorRequest.setBody(request instanceof StandardMultipartHttpServletRequest ? null : request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		errorRequest.setHost(InetAddress.getLoopbackAddress().getHostAddress());
		errorRequest.setMessage(exception.getMessage());
		errorRequest.setMethod(Enum.valueOf(MethodEnum.class, request.getMethod()));
		errorRequest.setToken(request.getHeader("Authorization"));
		errorRequest.setUrl(request.getRequestURI());
		
		errorLoggerRepository.save(errorRequest);
		
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("Something Went Wrong");
		error.setMsgKey("somethingWentWrong");
		return error;

	}
	
}
