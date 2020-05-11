package com.supermap.gaf.rest.exceptions;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.supermap.gaf.rest.filters.GAFRequestWrapperFilter;

@Configuration
@ConditionalOnBean(ResourceConfig.class)
@ConditionalOnProperty(name="gaf.jersey.exception.enable", havingValue="true", matchIfMissing = true)
public class ExceptionMapperConfig {
    
    @Bean
    public ExceptionMapperConfig configExceptionMapper(@Autowired ResourceConfig resouceConfig) {
        resouceConfig.register(IllegalArgumentExceptionMapper.class);
        resouceConfig.register(IllegalStateExceptionMapper.class);
        resouceConfig.register(ScNotModifiedExceptionMapper.class);
        resouceConfig.register(JaxrsHttpExceptionMapper.class);
        resouceConfig.register(WebApplicationExceptionMapper.class);
        resouceConfig.register(ShiroExceptionMapper.class);
        return new ExceptionMapperConfig();
    }
    
    @Bean
    public GAFRequestWrapperFilter  requestFilter() {
        return new GAFRequestWrapperFilter();
    }
}
