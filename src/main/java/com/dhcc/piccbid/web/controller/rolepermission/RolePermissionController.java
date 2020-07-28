package com.dhcc.piccbid.web.controller.rolepermission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


        /**
        *
        * 描述: TODO<br/>
        * 公司: 东华软件股份公司<br/>
        * 版权: dhcc2017<br/>
        *
        * @author tangjianbo
        * @date 2019-06-03 20:42:22
        * @version V1.0
        */
@Controller
public class RolePermissionController {

    @RequestMapping("/rolepermission/rolepermission")
    public String role() {
        return "rolepermission/rolepermission";
    }

    @RequestMapping("/rolepermission/rolepermissionAdd")
    public String roleAdd() {
        return "rolepermission/rolepermissionAdd";
    }

}
