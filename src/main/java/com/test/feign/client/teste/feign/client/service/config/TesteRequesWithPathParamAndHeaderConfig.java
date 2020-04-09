package com.test.feign.client.teste.feign.client.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class TesteRequesWithPathParamAndHeaderConfig {
	
	
	@Value("${api.test.client_secret}")
	public String clientSecret;
	
	@Value("${api.test.Authorization}")
	public String token;
	
	@Value("${api.test.x-itau-flowID}")
	public String flowId;
	
	@Value("${api.test.x-itau-correlationID}")
	public String correlationId;
	
	
	public static final String CLIENT_SECRET = "client_secret";
	public static final String AUTHORIZATION = "Authorization";
	public static final String X_ITAU_FLOW_ID ="x-itau-flowID";
	public static final String X_ITAU_CORRELATION_ID = "x-itau-correlationID";	
	
	@Bean(name = "testeGetRequestInterceptor")
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				
				template.header(CLIENT_SECRET, clientSecret);
				template.header(AUTHORIZATION, token);
				template.header(X_ITAU_FLOW_ID, flowId);
				template.header(X_ITAU_CORRELATION_ID, correlationId);				
				
			}
		};
	}

}
