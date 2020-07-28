package com.dhcc.piccbid.web.controller.decompositionHospital;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题: CostRatioAnalysisController.java</p>
 * <p>业务描述:获取前台传过来的链接，并且跳到相应的jsp</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
@Controller
public class DecompositionHospital {
    /**
     *  分解住院
     *  @author 王彤
     *  @date 2019年11月23日
     * @return String
     */
    @RequestMapping("/decompositionHospital/decompositionHospital")
    public String decompositionHospital() {
        return "decompositionHospital/decompositionHospital";
    }

    /**
     *  病例明细
     *  @author 王彤
     *  @date 2019年12月24日
     * @return String
     */
    @RequestMapping("/decompositionHospital/decompositionHospitalDetails")
    public String decompositionHospitalDetails() {
        return "decompositionHospital/decompositionHospitalDetails";
    }
}
