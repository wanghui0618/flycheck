/**
 * 
 */
package com.dhcc.piccbid.web.controller.supervisework;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhcc.piccbid.entity.user.User;

/**
 * @author xtl
 *
 */
@Controller
public class SuperviseWork {
	@RequestMapping("/superviseWork/superviseWork")
	public String superviseWork(HttpServletRequest request){
		String path= "";
    	
    		path = "/superviseWork/superviseWorkIndex";
    	
       return path;
	}
	@RequestMapping("/superviseWork/superviseWorkform")
	public String superviseRuleForm(){
		return "/superviseWork/superviseWorkForm";
	}
	@RequestMapping("/superviseWork/superviseWorkform1")
	public String superviseRuleForm1(){
		return "/superviseWork/superviseWorkForm1";
	}
	@RequestMapping("/superviseWork/superviseWorkform2")
	public String superviseRuleForm2(){
		return "/superviseWork/superviseWorkForm2";
	}
	//superviseWork
	@RequestMapping("/superviseWork/superviseWorkAllArea")
	public String superviseWorkAllArea(HttpServletRequest request){
		String path= "";
    	
    		path = "/superviseWork/superviseWorkAllArea";
    	
       return path;
	}
	
	@RequestMapping("/superviseWork/superviseWork/getAllSureInfo")
	public String superviseResAllArea(HttpServletRequest request){
		String path= "";
    
    		path = "superviseWork/superviseWorkAllSureInfo";
    	
       return path;
    }
}
