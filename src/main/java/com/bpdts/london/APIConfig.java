package com.bpdts.london;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan({"com.bpdts.london"})
public class APIConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
