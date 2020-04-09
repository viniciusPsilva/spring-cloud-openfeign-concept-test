package com.test.feign.client.teste.feign.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.test.feign.client.teste.feign.client.domain.model.ResponsePayload;
import com.test.feign.client.teste.feign.client.service.config.TesteRequesWithPathParamAndHeaderConfig;

@FeignClient(name = "testeGet", url="${url.api.test.get}", configuration=TesteRequesWithPathParamAndHeaderConfig.class)
public interface TesteRequesWithPathParamAndHeader {

	@GetMapping("/{id}")
	ResponsePayload getTest(@PathVariable("id") String id);
}
