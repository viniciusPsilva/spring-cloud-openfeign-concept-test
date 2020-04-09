package com.test.feign.client.teste.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.feign.client.teste.feign.client.domain.model.Payload;
import com.test.feign.client.teste.feign.client.domain.model.ResponsePayload;
import com.test.feign.client.teste.feign.client.service.config.AnaliseDeFraudeFeignCustomHeaderConfig;

@FeignClient(name = "analiseDeFraude", url = "${url.api.test.post.analise}", configuration= AnaliseDeFraudeFeignCustomHeaderConfig.class, fallback = HystrixAnaliseDeFraudeClientFallback.class)
public interface AnaliseDeFraudeClientService {

	@PostMapping
	ResponseEntity<ResponsePayload> analiseDeFraude(@RequestBody Payload payload);
}
