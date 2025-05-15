package com.moxos.uab.config;

import com.moxos.uab.config.filters.HtmlMinifyFilter;
import com.moxos.uab.config.servlet.CaptchaGenServlet;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class ServletConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ServletRegistrationBean<CaptchaGenServlet> capchatServletRegistrationBean() {
        ServletRegistrationBean<CaptchaGenServlet> registrationBean = new ServletRegistrationBean<>(
                new CaptchaGenServlet());
        registrationBean.addUrlMappings("/captcha.jpg");
        return registrationBean;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

   /* @Bean
    public FilterRegistrationBean<HtmlMinifyFilter> htmlMinifyFilter() {
        FilterRegistrationBean<HtmlMinifyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HtmlMinifyFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    } */
}
