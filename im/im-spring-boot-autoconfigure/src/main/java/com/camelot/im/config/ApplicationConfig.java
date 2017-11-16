package com.camelot.im.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {


  public void addInterceptors(InterceptorRegistry registry) {
    //TODO 一些拦截器定义
  }


}
