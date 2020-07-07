package com.ngr.aggregatorservice.errorhandler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().is5xxServerError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		// No implementation for now
	}

}
