package com.dhcc.piccbid.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>标题: XssFilter.java</p>
 * <p>业务描述:Drgs绩效分析及控费系统</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2016</p>
 * @author 寇祥
 * @date 2018年9月10日
 * @version V1.0 
 */
public class XssFilter implements Filter {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(XssFilter.class);

    FilterConfig filterConfig = null;
    //不需要登录就可以访问的路径(比如:注册登录等)
    String[] includeUrls = new String[]{"/app/","/base/","/css/","/images/","/js/","/plugins/"};

	/* (非 Javadoc)   
	* <p>Title: init</p>   
	* <p>Description: </p>   
	* @param filterConfig
	* @throws ServletException   
	* @see javax.servlet.Filter#init(javax.servlet.FilterConfig)   
	*/
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}

	/* (非 Javadoc)   
	* <p>Title: doFilter</p>   
	* <p>Description: </p>   
	* @param request
	* @param response
	* @param chain
	* @throws IOException
	* @throws ServletException   
	* @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)   
	*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
	    //是否需要过滤
	    boolean needFilter = isNeedFilter(uri);
	
	    if (!needFilter) { //不需要过滤直接传给下一个过滤器
		    chain.doFilter(httpRequest, httpResponse);
        } else {
        	//logger.info("=========过滤Url===========>"+uri);
			chain.doFilter(new XssHttpServletRequestWrapper( httpRequest), response);
		}
	}

	/* (非 Javadoc)   
	* <p>Title: destroy</p>   
	* <p>Description: </p>      
	* @see javax.servlet.Filter#destroy()   
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		 this.filterConfig = null;
	}
	 /**
     * @Author: xxxxx
     * @Description: 是否需要过滤
     * @Date: 2018-03-12 13:20:54
     * @param uri
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if(uri.contains(includeUrl)) {
                return false;
            }
        }

        return true;
    }

}
