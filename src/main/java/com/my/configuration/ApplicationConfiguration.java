package com.my.configuration;

import com.my.resolver.UserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement
public class ApplicationConfiguration extends SpringBootServletInitializer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationConfiguration.class);
    }

    @Bean
    public RequestMappingHandlerAdapter requestHandler() {
        RequestMappingHandlerAdapter requestHandler = new RequestMappingHandlerAdapter();
        requestHandler.setCustomArgumentResolvers(Arrays.asList(new UserMethodArgumentResolver()));
        return requestHandler;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
