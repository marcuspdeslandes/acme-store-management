package com.acme.store.persist.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.api.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CustomOpenAPIConfig {

  @Value("${openapi.server.url}")
  private String openapiServerUrl;

  @Value("${openapi.title}")
  private String title;

  @Value("${openapi.version}")
  private String version;

  @Value("${openapi.description}")

  private String description;
  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi ->
      openApi.getPaths().values().stream()
        .flatMap(pathItem -> pathItem.readOperations().stream())
        .forEach(
          operation -> {
            var parameters = operation.getParameters();
            if (parameters == null) parameters = new ArrayList<>();

            operation.setParameters(parameters);
          });
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info().title(title).version(version).description(description))
      .addServersItem(new Server().url(openapiServerUrl));
  }
}
