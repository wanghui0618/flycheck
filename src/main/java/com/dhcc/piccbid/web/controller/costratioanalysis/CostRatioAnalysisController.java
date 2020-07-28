package com.dhcc.piccbid.web.controller.costratioanalysis;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>标题: CostRatioAnalysisController.java</p>
 * <p>业务描述:获取前台传过来的链接，并且跳到相应的jsp</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年10月18日
 * @version V1.0
 */
@Controller
public class CostRatioAnalysisController {

    @RequestMapping("/CostRatioAnalysis/CostRatioAnalysisQ")
    public String CostRatioAnalysis() {
        return "costRatioAnalysis/CostRatioAnalysisQ";
    }

}
