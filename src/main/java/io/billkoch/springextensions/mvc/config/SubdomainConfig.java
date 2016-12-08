package io.billkoch.springextensions.mvc.config;

import io.billkoch.springextensions.mvc.bind.annotation.SubdomainExtractor;
import io.billkoch.springextensions.mvc.bind.annotation.SubdomainResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class SubdomainConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SubdomainExtractor subdomainExtractor() {
        return new SubdomainExtractor();
    }

    @Bean
    public SubdomainResolver subdomainResolver() {
        return new SubdomainResolver(subdomainExtractor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(subdomainResolver());
    }
}
