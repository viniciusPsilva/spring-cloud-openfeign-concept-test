package com.test.feign.client.teste.feign.client.service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class HystrixTokenClientFallback implements TokenClientService {

	@Override
	public ResponseEntity<JsonNode> obterToken(Map<String, ?> formParams) {
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("access_token", "Token service Indisponivel testes testes ");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(objectNode);
	}
}
