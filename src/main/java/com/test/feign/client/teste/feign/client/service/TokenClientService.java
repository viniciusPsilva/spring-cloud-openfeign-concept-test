package com.test.feign.client.teste.feign.client.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.feign.client.teste.feign.client.service.config.TokenFeignCustomHeaderConfig;

import feign.Headers;

@FeignClient(name = "test", url = "localhost:9000/api/post/token", configuration=TokenFeignCustomHeaderConfig.class, fallback = HystrixTokenClientFallback.class)
public interface TokenClientService {
	
	@PostMapping
	ResponseEntity<JsonNode> obterToken(@RequestBody Map<String, ?> formParams);
}
