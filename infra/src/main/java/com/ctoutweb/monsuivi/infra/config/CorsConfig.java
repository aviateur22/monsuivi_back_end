package com.ctoutweb.monsuivi.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

 // Domaine ouvert à l'API
  @Value("${cors.domains}")
  String corsDomains;

  @Value("${api.version}")
  String apiVersion;

  @Value("${application.name}")
  String applicationName;

  @Bean(name = "corsConfiguration")
  public CorsConfigurationSource corsConfigurationSource() {
    UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();

    // Configuration Cors pour la gestion Product
    CorsConfiguration productCorsConfig = new CorsConfiguration();
    productCorsConfig.setAllowCredentials(true);
    productCorsConfig.setAllowedOrigins(Arrays.asList(corsDomains.split(",")));
    productCorsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT"));
    productCorsConfig.setAllowedHeaders(Arrays.asList("Content-Type"));
    source.registerCorsConfiguration(apiVersion+"/products/**", productCorsConfig);
    source.registerCorsConfiguration("/"+ applicationName+apiVersion+"/products/**", productCorsConfig);
    return source;
  }
}
