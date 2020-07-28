/**
 * 
 */
package com.dhcc.piccbid.web.controller.textareaanddatagrid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * @author jpp
 *
 */
@Controller
public class TextAreaAndDatagrid {
	@RequestMapping("/textAreaAndDatagrid/textAreaAndDatagrid")
	public String textAreaAndDatagrid(){
       return "overclockingAndAmountItem/overclockingItem";
	}
	@RequestMapping("/textAreaAndDatagrid/mutexItem")
	public String mutexItem(){
		return "textAreaAndDatagrid/mutexItem";
	}
	
}
