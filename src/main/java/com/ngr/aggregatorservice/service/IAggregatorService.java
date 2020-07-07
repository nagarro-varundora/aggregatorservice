package com.ngr.aggregatorservice.service;

import org.springframework.web.client.ResourceAccessException;

import com.ngr.aggregatorservice.error.APIError;
import com.ngr.aggregatorservice.model.OrderDetails;

public interface IAggregatorService {
	OrderDetails getOrderDetails(int id) throws ResourceAccessException, APIError;
}
