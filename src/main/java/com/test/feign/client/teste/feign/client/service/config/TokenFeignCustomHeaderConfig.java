package com.test.feign.client.teste.feign.client.service.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.feign.client.teste.feign.client.domain.model.Payload;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class TokenFeignCustomHeaderConfig {

	@Value("${api.test.header.client.id}")
	private String clientId;
	@Value("${api.test.header.id.correlacao}")
	private String idCorrelacao;
	@Value("${api.test.header.url.callback}")
	private String urlCallback;

	private final ObjectMapper objectMapper;

	protected static final String CLIENT_ID = "client_id";
	protected static final String ID_CORRELACAO = "id_correlacao";
	protected static final String URL_CALLBACK = "url_callback";

	@Bean
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }
	
	@Bean(name = "TokenRequestInterceptor")
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor() {

			@Override
			public void apply(RequestTemplate template) {

				try {
					/*@SuppressWarnings("deprecation")
					Payload payload = objectMapper.readValue(template.body(), Payload.class);*/

					template.header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
					
					/* template.header(CLIENT_ID, clientId);
					template.header(ID_CORRELACAO, payload.getIdCorrelacao());
					template.header(URL_CALLBACK, urlCallback); */
					
				} catch (Exception e) {
					System.out.println("=========================================");
					System.out.println("====================ERRO=================");
					System.out.println("=========================================");
					System.out.println("erro ao converter com json objectMapper");
					e.printStackTrace();
				}
			}
		};
	}


	

}
