package com.ufc.pi.webservice.config.security;

import com.ufc.pi.webservice.enums.UserRole;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authorizeHttpRequestsCustomizer -> {
        authorizeHttpRequestsCustomizer
          .requestMatchers("/api/v1/auth/**", "/api/v1/publishers/**", "/api/v1")
          .permitAll()
          .requestMatchers("/api/v1/admins/*").hasRole(UserRole.ADMIN.name())
          .requestMatchers("/api/v1/customers/*").hasRole(UserRole.CUSTOMER.name())
          .anyRequest()
          .authenticated();
      })
      .sessionManagement(sessionManagementCustomizer -> {
        sessionManagementCustomizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      })
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
