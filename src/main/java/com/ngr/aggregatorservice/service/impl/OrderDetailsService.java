package com.ngr.aggregatorservice.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.ngr.aggregatorservice.errorhandler.RestTemplateResponseErrorHandler;
import com.ngr.aggregatorservice.model.Order;
import com.ngr.aggregatorservice.service.IOrderDetailsService;

@Service
public class OrderDetailsService implements IOrderDetailsService {

	@Autowired
	private RestTemplateResponseErrorHandler responseErrorHandler;

	@Autowired
	Environment env;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Order> getOrderDetails(int id) {
		try {
			String orderServiceUrl = env.getProperty("orderservice.api.endpoint");
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(responseErrorHandler);
			restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
			ResponseEntity<Map> response = restTemplate.getForEntity(orderServiceUrl + id, Map.class);
			HashMap<Object, Object> body = (HashMap<Object, Object>) response.getBody();
			return (List<Order>) body.get("orders");
		} catch (ResourceAccessException ex) {
			throw ex;
		}
	}
}
