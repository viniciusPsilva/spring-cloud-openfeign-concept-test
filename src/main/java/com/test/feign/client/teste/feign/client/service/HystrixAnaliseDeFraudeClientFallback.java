package com.test.feign.client.teste.feign.client.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.test.feign.client.teste.feign.client.domain.model.Payload;
import com.test.feign.client.teste.feign.client.domain.model.ResponsePayload;

@Component
public class HystrixAnaliseDeFraudeClientFallback implements AnaliseDeFraudeClientService{

	@Override
	public ResponseEntity<ResponsePayload> analiseDeFraude(Payload payload) {
		ResponsePayload response = ResponsePayload.builder().conclusao("Fraude Indisponivel teste 14:51").build();
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
	}
}
