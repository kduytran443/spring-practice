package com.securitydemo.securitydemo.config;

import com.securitydemo.securitydemo.filter.ApiKeyFilter;
import jakarta.servlet.DispatcherType;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class FilterConfiguration {

    @Bean
    @Profile("DISABLED")
    public FilterRegistrationBean<ApiKeyFilter> apiKeyFilter() {
        ApiKeyFilter filter = new ApiKeyFilter();
        var registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR);
        registrationBean.setUrlPatterns(List.of("/api/*"));
        registrationBean.setOrder(99);
        return registrationBean;
    }
}
