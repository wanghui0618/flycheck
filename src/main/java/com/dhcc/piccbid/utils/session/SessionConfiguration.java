package com.dhcc.piccbid.utils.session;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {
	
	//注册session监听器;
    @Bean
    public ServletListenerRegistrationBean<SessionManage> servletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SessionManage> slrBean = new ServletListenerRegistrationBean<SessionManage>();
        slrBean.setListener(new SessionManage());
        return slrBean;
    }

}
