/**
 * 
 */
package com.dhcc.piccbid.web.controller.superviserule;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhcc.piccbid.entity.user.User;

/**
 * @author xtl
 *
 */
@Controller
public class SuperviseRule {
	@RequestMapping("/superviseRule/superviseRule")
	public String superviseRule(HttpServletRequest request){
		String path= "";
    	
    		path = "/superviseRule/superviseRuleIndex";
    	
       return path;
	}
	
	@RequestMapping("/superviseRule/superviseRuleform")
	public String superviseRuleForm(){
		return "/superviseRule/superviseRuleForm";
	}
	@RequestMapping("/superviseRule/superviseRuleform1")
	public String superviseRuleForm1(){
		return "/superviseRule/superviseRuleForm1";
	}
	@RequestMapping("/superviseRule/superviseRuleform2")
	public String superviseRuleForm2(){
		return "/superviseRule/superviseRuleForm2";
	}
}
