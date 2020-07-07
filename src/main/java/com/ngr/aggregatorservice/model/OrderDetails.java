package com.ngr.aggregatorservice.model;

import java.util.List;

public class OrderDetails {

	private UserDetails userDetails;
	private List<Order> orders;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
