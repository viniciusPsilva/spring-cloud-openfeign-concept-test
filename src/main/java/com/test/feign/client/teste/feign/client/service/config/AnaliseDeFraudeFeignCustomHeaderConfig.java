package com.test.feign.client.teste.feign.client.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.feign.client.teste.feign.client.service.TokenClientService;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AnaliseDeFraudeFeignCustomHeaderConfig {

	//private final ObjectMapper objectMapper;
	
	private final TokenClientService httpTokenClientService;
	
	@Value("${api.test.header.client.id}")
	private String clientId;
	
	@Value("${api.test.header.url.callback}")
	private String urlCallback;
	
	@Bean(name = "AnaliseDeFraudeRequestInterceptor")
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				template.header("token", obterToken());
				//template.header("client_id", payload.getIdCorrelacao());
				template.header("url_callback", "url_callback_0987_passado-pelo-feign");
				
			}

			private String obterToken() {
				MultiValueMap<String, String> formParam = new LinkedMultiValueMap<>();
				formParam.add("client_id", "client_id_passado-Pello-Feign");
				formParam.add("id_correlação", "id_correlacao_passado-Pello-Feign");
				formParam.add("url_callback", "urlCallBack_passado-Pello-Feign");
				ResponseEntity<JsonNode> response = httpTokenClientService.obterToken(formParam);
				
				return response.getBody().get("access_token").textValue();
			}
		};
	}
	
}
