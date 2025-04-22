package com.ctoutweb.monsuivi.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiVersionConfig {
  @Value("${api.version}")
  private String apiVersion;

  public String getApiVersion() {
    return apiVersion;
  }
}
