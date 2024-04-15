package com.ufc.pi.webservice.config.security;

import com.ufc.pi.webservice.services.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final UserDetailsService userDetailsService;

  private final JwtService jwtService;


  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request,
    @NonNull HttpServletResponse response,
    @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String token;
    final String userEmail;

    if(authHeader == null || !authHeader.startsWith("Bearer ")){
      filterChain.doFilter(request, response);
      return;
    }

    token = authHeader.split(" ")[1];
    userEmail = jwtService.extractEmail(token);

    if(userHasTokenButItIsNotInAuthContext(userEmail)){
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

      if(jwtService.isTokenValid(token, userDetails)){
        setUserInfoInAuthContext(userDetails, request);
      }
    }

    filterChain.doFilter(request, response);
  }

  private boolean userHasTokenButItIsNotInAuthContext(String userEmail) {
    return userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null;
  }

  private void setUserInfoInAuthContext(UserDetails userDetails, HttpServletRequest request) {
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
      userDetails,
      null,
      userDetails.getAuthorities()
    );

    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authToken);
  }

}

