package com.acme.store.persist;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan("com.acme.store.persist")
public class StorePersistApplication {

  public static void main(String[] args) {
    SpringApplication.run(StorePersistApplication.class, args);
  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
