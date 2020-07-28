package com.dhcc.piccbid.common;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dhcc.piccbid.utils.UrlSecurityCheckInterceptor;
import com.dhcc.piccbid.utils.XssFilter;

/**
 * <p>标题: BaseConfig.java</p>
 * <p>业务描述:Drgs绩效分析及控费系统</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2016</p>
 * @author 寇祥
 * @date 2018年9月10日
 * @version V1.0 
 */
@Configuration
public class BaseConfig  extends WebMvcConfigurerAdapter{
	@Bean
	public FilterRegistrationBean addFilter() throws ServletException {
		XssFilter xssFilter = new XssFilter();
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(xssFilter);
		registrationBean.setName("xssFilter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(900);
		return registrationBean;
	}
	
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //单个文件最大  
        factory.setMaxFileSize("102400KB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("1024000KB");  
        return factory.createMultipartConfig();  
    }  

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UrlSecurityCheckInterceptor())
		.excludePathPatterns("/app/**","/base/**","/css/**","/html/**","/images/**","/js/**","/plugins/**","/portal/**","/dhccApi/**","/resources/**");
	}
	
    
	
}
