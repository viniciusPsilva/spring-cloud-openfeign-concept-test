package com.test.feign.client.teste.feign.client.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.feign.client.teste.feign.client.domain.model.Payload;
import com.test.feign.client.teste.feign.client.domain.model.ResponsePayload;
import com.test.feign.client.teste.feign.client.service.AnaliseDeFraudeClientService;
import com.test.feign.client.teste.feign.client.service.TesteRequesWithPathParamAndHeader;
import com.test.feign.client.teste.feign.client.service.TokenClientService;

@Controller
@RequestMapping(path = "/feign")
public class FeignClientTestController {

	@Autowired
	private TokenClientService httpTokenClientService;
	
	@Autowired
	private AnaliseDeFraudeClientService httpAnaliseDeFraudeClientService;
	
	@Autowired
	private TesteRequesWithPathParamAndHeader TesteRequesWithPathParamAndHeader;
	
	@PostMapping(value = "/post/token")
	public ResponseEntity<String> getToken(@RequestBody Payload payload) {
		System.out.println("==============");
		System.out.println("Realizando chamada Http Token");
		System.out.println("==============");
		
		MultiValueMap<String, String> formParam = new LinkedMultiValueMap<>();
		formParam.add("client_id", "client_id_passado-Pello-Feign");
		formParam.add("id_correlação", "id_correlacao_passado-Pello-Feign");
		formParam.add("url_callback", "urlCallBack_passado-Pello-Feign");
	
		 ResponseEntity<JsonNode> response = httpTokenClientService.obterToken(formParam);
		
		 System.out.println("===============================");
		 System.out.println("Response");
		 System.out.println("===============================");
		 System.out.println("Status: "+ response.getStatusCode());
		 String token = response.getBody().get("access_token").textValue();
		 System.out.println("BODY: "+ token);
		 
		System.out.println("==============");
		System.out.println("token obtido - "+ token);
		
		return ResponseEntity.status(HttpStatus.OK).body(token);		
	}
	
	@PostMapping(value = "post/analise")
	public ResponseEntity<ResponsePayload> realizarAnalise(@RequestBody Payload payload){
		System.out.println("==============");
		System.out.println("Realizando chamada Http Analise");
		System.out.println("==============");
		
		ResponseEntity<ResponsePayload> responseEntity = httpAnaliseDeFraudeClientService.analiseDeFraude(payload);
		
		
		System.out.println("==============");
		System.out.println("Response");
		System.out.println("==============");
		System.out.println("Status: "+ responseEntity.getStatusCode());
		System.out.println("Body: "+ responseEntity.getBody());
		
		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			System.out.println("===============================");
			System.out.println("O RETORNO FOI ACCEPTED");
			System.out.println("===============================");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity.getBody());
		}
		
		return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
	}
	
	@GetMapping(value = "/get/test")
	public ResponseEntity<ResponsePayload> testGet(){
		
		System.out.println("Realizando chamada get teste para a api test...");
		ResponsePayload responsePayload = TesteRequesWithPathParamAndHeader.getTest(UUID.randomUUID().toString());
		System.out.println("Chamada realizada ...");
		
		return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
	}
	
}
