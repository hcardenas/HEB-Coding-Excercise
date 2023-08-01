package com.hcardenas.hebcodingexcercise.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ImaggaClientConfig {


    @Bean
    public WebClient imaggaWebClient(ImmageConfig config) {
        return WebClient.builder()
                .baseUrl(config.getUrl())
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(config.getAuth()))
                .build();
    }

}
