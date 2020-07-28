package com.dhcc.piccbid.web.controller.returntypeCon;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class RuletypeconnotationController {
    @RequestMapping("/ruletypeconnotation/ruletypeConnotation")
    public String ruletypeConnotation() {
        return "ruletypeconnotation/ruletypeConnotation";
    }
}
