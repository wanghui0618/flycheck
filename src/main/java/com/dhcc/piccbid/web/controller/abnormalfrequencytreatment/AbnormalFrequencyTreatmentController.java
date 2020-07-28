package com.dhcc.piccbid.web.controller.abnormalfrequencytreatment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * <p>标题: AbnormalFrequencyTreatmentController.java</p>
 * <p>业务描述:获取前台传过来的链接，并且跳到相应的jsp</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */
@Controller
public class AbnormalFrequencyTreatmentController {
	/**
     *  就诊次数
     *  @author 贺和平
     *  @date 2019年11月23日
     * @return String
     */
	@RequestMapping("/abnormalfrequencytreatment/abnormalfrequencytreatment")
	public String show() {
		return "/abnormalfrequencytreatment/abnormalfrequencytreatment";
	}
	/**
     *  就诊明细
     *  @author 贺和平
     *  @date 2019年12月24日
     * @return String
     */
	 @RequestMapping("/abnormalfrequencytreatment/abnormalfrequencytreatmentDetails")
	    public String abnormalfrequencytreatmentDetails() {
	        return "abnormalfrequencytreatment/abnormalfrequencytreatmentDetails";
	    }
	 
	 @RequestMapping("/abnormalfrequencytreatment/abnormalfrequencytreatmentzy")
		public String showzy() {
			return "/abnormalfrequencytreatment/abnormalfrequencytreatmentzy";
		}
	 
	 @RequestMapping("/abnormalfrequencytreatment/abnormalfrequencytreatmentzyDetails")
	    public String abnormalfrequencytreatmentZYDetails() {
	        return "abnormalfrequencytreatment/abnormalfrequencytreatmentzyDetails";
	    }
}
