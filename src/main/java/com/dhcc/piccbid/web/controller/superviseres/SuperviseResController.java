/**
 * 
 */
package com.dhcc.piccbid.web.controller.superviseres;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhcc.piccbid.entity.user.User;

/**
 * @author xtl
 *
 */
@Controller
@RequestMapping("/superviseRes")
public class SuperviseResController {
	 // 重复收费
    @RequestMapping("/superviseRes")
    public String duplicateCharge(HttpServletRequest request) {
    	String path= "";
    	
    
    	
    	path = "superviseRes/superviseResIndex";
    	return  path;
    }
    
    @RequestMapping("/superviseResAllRule")
    public String superviseResAllRule(HttpServletRequest request,String value){
    	String path= "";
    	
    	
    		path = "superviseRes/superviseResAllRule";
    	
    	request.setAttribute("ZSJSFlag", value);
       return path;
    }
    
    @RequestMapping("/superviseRes/getAllSureInfo")
    public String getAllSureInfo(HttpServletRequest request){
    	String path= "";
    	
    		path = "superviseRes/superviseResAllSureInfo";
    	
       return path;
    }
 //superviseResAllArea   
    @RequestMapping("/superviseResAllArea")
    public String superviseResAllArea(HttpServletRequest request){
    	String path= "";
    	
    		path = "superviseRes/superviseResAllArea";
    	
       return path;
    }
}
