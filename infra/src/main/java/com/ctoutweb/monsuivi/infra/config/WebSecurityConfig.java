package com.ctoutweb.monsuivi.infra.config;

import com.ctoutweb.monsuivi.infra.config.authentication.CustomAuthenicationProvider;
import com.ctoutweb.monsuivi.infra.config.authorizationFilter.AuthorizationFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  private static final Logger LOGGER = LogManager.getLogger();
  @Value("${api.version}")
  private String apiVersion;
  private final CustomAuthenicationProvider customAuthProvider;
  private final CorsConfigurationSource corsConfigurationSource;
  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final AuthorizationFilter authorizationFilter;

  public WebSecurityConfig(
          CustomAuthenicationProvider customAuthProvider,
          @Qualifier("corsConfiguration") CorsConfigurationSource corsConfigurationSource,
          AccessDeniedHandler accessDeniedHandler, AuthenticationEntryPoint authenticationEntryPoint,
          AuthorizationFilter authorizationFilter) {
      this.customAuthProvider = customAuthProvider;
      this.corsConfigurationSource = corsConfigurationSource;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
      this.authorizationFilter = authorizationFilter;
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    LOGGER.debug(apiVersion);
    http
            .csrf(csrf->csrf.disable())
            .cors(cors->cors.configurationSource(corsConfigurationSource))
            .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex->ex
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
            )
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(request->request
                    .requestMatchers(
                            apiVersion+"/auth/**",
                            apiVersion+"/api").permitAll()
                    .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(customAuthProvider)
            .build();
  }
}
