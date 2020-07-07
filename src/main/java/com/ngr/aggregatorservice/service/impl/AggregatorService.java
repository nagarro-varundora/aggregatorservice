package com.ngr.aggregatorservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.ngr.aggregatorservice.error.APIError;
import com.ngr.aggregatorservice.model.Order;
import com.ngr.aggregatorservice.model.OrderDetails;
import com.ngr.aggregatorservice.model.UserDetails;
import com.ngr.aggregatorservice.service.IAggregatorService;

@Service
public class AggregatorService implements IAggregatorService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDetailsService orderService;
	
	@Override
	public OrderDetails getOrderDetails(int id) throws ResourceAccessException, APIError{
			UserDetails userDetails = userService.getUserDetails(id);
			List<Order> orders = orderService.getOrderDetails(id);
			
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrders(orders);
			orderDetails.setUserDetails(userDetails);
			return orderDetails;	
	}

}
