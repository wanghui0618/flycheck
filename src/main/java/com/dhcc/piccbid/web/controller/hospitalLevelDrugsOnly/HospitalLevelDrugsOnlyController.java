package com.dhcc.piccbid.web.controller.hospitalLevelDrugsOnly;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>标题: HospitalLevelDrugsOnlyController.java</p>
 * <p>业务描述:获取前台传过来的链接，并且跳到相应的jsp</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
@Controller
public class HospitalLevelDrugsOnlyController {

    /**
     *  限医院等级用药主页面
     *  @author 王彤
     *  @date 2020年1月2日
     * @return String
     */
    @RequestMapping("/hospitalLevelDrugsOnly/hospitalLevelDrugsOnly")
    public String hospitalLevelDrugsOnly() {
        return "hospitalLevelDrugsOnly/hospitalLevelDrugsOnly";
    }



}
