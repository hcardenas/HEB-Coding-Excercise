package com.hcardenas.hebcodingexcercise.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("client.imagga.api")
@Getter
@Setter
public class ImmageConfig {

    private String key;

    private String secret;
    private String url;
    private String auth;


}
