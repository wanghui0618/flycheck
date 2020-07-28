package com.dhcc.piccbid.web.controller.duplicateCharge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/duplicateCharge/")
public class DuplicateChargeController {

    @RequestMapping("costDetail")
    public String costDetail() {
        return "duplicateCharge/costDetail";
    }
}
