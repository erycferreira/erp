package com.erycferreira.enterprise.api.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

  private final static Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    long start = System.currentTimeMillis();

    MDC.put("method", request.getMethod());
    MDC.put("path", request.getRequestURI());

    try {
      filterChain.doFilter(request, response);
    } finally {
      long duration = System.currentTimeMillis() - start;

      MDC.put("status", String.valueOf(response.getStatus()));
      MDC.put("duration_ms", String.valueOf(duration));

      if (response.getStatus() >= 500) {
        log.error("HTTP request completed with server error");
      } else if (response.getStatus() >= 400) {
        log.warn("HTTP request completed with client error");
      } else {
        log.info("HTTP request completed");
      }

      MDC.clear();
    }
  }
}