package com.dhcc.piccbid.web.controller.sidebar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Annotation:
 * 侧边栏 Controller
 *
 * @Author: Adam Ming
 * @Date: Feb 13, 2019 at 11:22:03 AM
 */
@Controller
@RequestMapping("/sidebar")
public class SidebarController {

    // 重复收费
    @RequestMapping("/duplicateCharge")
    public String duplicateCharge() {
        return "duplicateCharge/duplicateCharge";
    }
}
