package com.my.configuration.filter;

import com.my.filter.LoginFilter;
import com.my.filter.LogoutFilter;
import com.my.filter.TodoFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean(name = "todoFilter")
    public FilterRegistrationBean todoFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TodoFilter());
        registrationBean.addUrlPatterns("/todo/*");
        return registrationBean;
    }

    @Bean(name = "logoutFilter")
    public FilterRegistrationBean logoutFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LogoutFilter());
        registrationBean.addUrlPatterns("/auth/logout/*");
        return registrationBean;
    }

    @Bean(name = "loginFilter")
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/auth/login/*");
        return registrationBean;
    }
}
