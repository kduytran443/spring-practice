package com.securitydemo.securitydemo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@Getter
public class ApiKeyFilter implements Filter {
    private final String apiKey;

    public ApiKeyFilter() {
        apiKey = Base64.getEncoder().encodeToString("Password-Here".getBytes(StandardCharsets.UTF_8));
        log.info("ApiKey: {}", apiKey);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("Filtering API Key");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!getApiKey().equals(request.getHeader("x-api-key"))) {
            log.error("API Key does not matched");
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
