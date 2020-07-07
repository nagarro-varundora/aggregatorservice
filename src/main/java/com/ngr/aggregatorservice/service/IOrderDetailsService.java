package com.ngr.aggregatorservice.service;

import java.util.List;

import org.springframework.web.client.ResourceAccessException;

import com.ngr.aggregatorservice.model.Order;

public interface IOrderDetailsService {
	
	List<Order> getOrderDetails(int id) throws ResourceAccessException;
}
