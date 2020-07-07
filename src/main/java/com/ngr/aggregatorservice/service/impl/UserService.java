package com.ngr.aggregatorservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.ngr.aggregatorservice.error.APIError;
import com.ngr.aggregatorservice.errorhandler.RestTemplateResponseErrorHandler;
import com.ngr.aggregatorservice.model.UserDetails;
import com.ngr.aggregatorservice.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private RestTemplateResponseErrorHandler responseErrorHandler;

	@Autowired
	private Environment env;

	@Override
	public UserDetails getUserDetails(int id) throws APIError {
		try {
			String userserviceUrl = env.getProperty("userservice.api.endpoint");
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(responseErrorHandler);
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			ResponseEntity<UserDetails> response = restTemplate.getForEntity(userserviceUrl + id, UserDetails.class);
			if (response.getStatusCode() == HttpStatus.OK)
				return response.getBody();
			else
				throw new APIError(response.getStatusCodeValue());
		} catch (ResourceAccessException ex) {
			throw ex;
		}
	}

}
