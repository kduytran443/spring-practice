package com.securitydemo.securitydemo.config;

import com.securitydemo.securitydemo.filter.ApiKeyFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER - 1)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatchers(matchers -> matchers.requestMatchers("/api/**"));
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
        http.addFilterBefore(new ApiKeyFilter(), AuthorizationFilter.class);
        return http.build();
    }
}
