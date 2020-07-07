package com.ngr.aggregatorservice.service;

import org.springframework.web.client.ResourceAccessException;

import com.ngr.aggregatorservice.error.APIError;
import com.ngr.aggregatorservice.model.UserDetails;

public interface IUserService {
	
	UserDetails getUserDetails(int id) throws ResourceAccessException, APIError;
}
