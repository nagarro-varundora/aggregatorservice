package com.ngr.aggregatorservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.ngr.aggregatorservice.error.APIError;
import com.ngr.aggregatorservice.model.OrderDetails;
import com.ngr.aggregatorservice.service.impl.AggregatorService;

@ControllerAdvice
@RestController
@RequestMapping(value = "/orderdetails")
public class AggregatorController {
	
	@Autowired
	private AggregatorService aggregatorService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable("id") int userId) throws ResourceAccessException, APIError{
		return ResponseEntity.ok(aggregatorService.getOrderDetails(userId));
	}
	
	@ExceptionHandler({ResourceAccessException.class})
	private ResponseEntity<?> resourceAccessExceptionHandler(ResourceAccessException ex){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
	}
	
	@ExceptionHandler({APIError.class})
	private ResponseEntity<String> apiErrorHandler(APIError e){
		if(e.getHttpCode() == HttpStatus.NOT_FOUND.value())
				return ResponseEntity.notFound().build(); 
			else
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Third party API error code : " + e.getHttpCode());
	}

}
