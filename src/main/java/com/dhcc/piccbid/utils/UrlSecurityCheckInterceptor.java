package com.dhcc.piccbid.utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhcc.piccbid.entity.user.User;

/**
 * <p>标题: UrlSecurityCheckInterceptor.java</p>
 * <p>业务描述:Drgs绩效分析及控费系统</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2016</p>
 * @author 寇祥
 * @date 2017年6月9日
 * @version V1.0 
 */
public class UrlSecurityCheckInterceptor implements HandlerInterceptor {
	//拦截设置，忽略以下jsp
	private static final String[] IGNORE_URI = {"/login","register","userRegister","forget","setNewPassword","/dhccApi/medicaldetail/medicalDetail/getList","/dhccApi/medical/medical/listAuditVo","/dhccApi/medicalaudit/medicalAudit/save","/dhccApi/medicalaudit/medicalAudit/appSave","/dhccApi/user/user/register","/dhccApi/user/user/login","/dhccApi/user/user/forget","/dhccApi/user/user/saveLogInformation","/dhccApi/medical/medical/appAuditListVo","/dhccApi/medical/medical/appAuditList","/dhccApi/medical/medical/medicalCostList","/dhccApi/medicalaudit/medicalAudit/findData","dhccApi/insuredPerson/insuredPerson/list","dhccApi/insuredPerson/insuredPerson/findById","/dhccApi/medical/medical/icdCard","/dhccApi/medicaldetail/medicalDetail/medicalId"};

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String applicationName = "/" + request.getContextPath().split("/")[request.getContextPath().split("/").length - 1] + "/";
		String requestUrl = request.getRequestURL().toString();
		if (requestUrl.endsWith(applicationName)) {
			return true;
		}
		boolean flag = false;
		String url = request.getRequestURL().toString();
		for (String s : IGNORE_URI) {
			if (url.endsWith(s)) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			User user = (User) request.getSession().getAttribute("user");
			if (null == user) {
				// ajax方式交互
				if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))// 如果是ajax请求响应头会有，x-requested-with；
				{
					response.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
					return false;
				}else {
					// 未登录
					PrintWriter out = response.getWriter();
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
					builder.append("alert(\"页面过期，请重新登录\");");
					builder.append("top.location.href='/flycheck';");
					builder.append("</script>");
					out.write(builder.toString());
					out.close();
					return false;
				}
			}else {
				return true;
			}
		}
		return flag;
	}

	/* (非 Javadoc)   
	* <p>Title: afterCompletion</p>   
	* <p>Description: </p>   
	* @param arg0
	* @param arg1
	* @param arg2
	* @param arg3
	* @throws Exception   
	* @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)   
	*/
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (非 Javadoc)   
	* <p>Title: postHandle</p>   
	* <p>Description: </p>   
	* @param arg0
	* @param arg1
	* @param arg2
	* @param arg3
	* @throws Exception   
	* @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)   
	*/
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}  
}
