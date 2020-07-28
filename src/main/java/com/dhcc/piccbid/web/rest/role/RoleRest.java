package com.dhcc.piccbid.web.rest.role;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.dhcc.piccbid.entity.page.Page;
import net.sf.json.JSONString;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.role.RoleBlh;
import com.dhcc.piccbid.dto.role.RoleDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @version V1.0
 * @date 2019-01-14 15:42:22
 */
@RestController
@RequestMapping("/dhccApi/role/role")
public class RoleRest {

    @Resource
    private RoleBlh roleBlh;

    @PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) RoleDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(RoleDto dto) {
        Page page = new Page();
        roleBlh.list(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @PostMapping(value = "listTree", consumes = "application/json")
    public String listTreeRest(@RequestBody(required = false) RoleDto dto) {
        return this.listTree(dto);
    }

    @RequestMapping("listTree")
    public String listTree(RoleDto dto) {
        return roleBlh.listTree(dto);
    }

    @PostMapping(value = "listAllTree", consumes = "application/json")
    public String listAllTreeRest(@RequestBody(required = false) RoleDto dto) {
        return this.listAllTree(dto);
    }

    @RequestMapping("listAllTree")
    public String listAllTree(RoleDto dto) {
        return roleBlh.listAllTree(dto);
    }

    /**
     * 角色菜单授权
     * @param dto
     * @return
     */
    @PostMapping(value = "saveAutho", consumes = "application/json")
    public RoleDto saveAuthoRest(@RequestBody(required = false) RoleDto dto) {
        return this.saveAutho(dto);
    }

    @RequestMapping("saveAutho")
    public RoleDto saveAutho(RoleDto dto) {
         roleBlh.saveAutho(dto);
        return dto;
    }

    /**
     * 角色数据授权
     * @param dto
     * @return
     */
   @PostMapping(value = "saveDataAutho", consumes = "application/json")
    public RoleDto saveDataAuthoRest(@RequestBody(required = false) RoleDto dto) {
        return this.saveDataAutho(dto);
    }

    @RequestMapping("saveDataAutho")
    public RoleDto saveDataAutho(RoleDto dto) {
         roleBlh.saveDataAutho(dto);
        return dto;
    }


    /**
     * 角色按钮授权 保存
     * @param dto
     * @return
     */
    @PostMapping(value = "saveRoleButtonAutho", consumes = "application/json")
    public RoleDto saveRoleButtonAuthoRest(@RequestBody RoleDto dto) {
        return this.saveRoleButtonAutho(dto);
    }

    @PostMapping("saveRoleButtonAutho")
    public RoleDto saveRoleButtonAutho(RoleDto dto) {
        roleBlh.saveRoleButtonAutho(dto);
        return dto;
    }

    @PostMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody(required = false) RoleDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(RoleDto dto) {
        roleBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @PostMapping(value = "save", consumes = "application/json")
    public String saveRest(@RequestBody RoleDto dto) {
        return this.save(dto);
    }

    @PostMapping("save")
    public String save(RoleDto dto) {
        return roleBlh.save(dto);

    }

    @PostMapping(value = "delete", consumes = "application/json")
    public RoleDto deleteRest(@RequestBody RoleDto dto) {
        return this.delete(dto);
    }

    @PostMapping("delete")
    public RoleDto delete(RoleDto dto) {
        roleBlh.delete(dto);
        return dto;
    }

    @PostMapping(value = "findById", consumes = "application/json")
    public RoleDto findByIdRest(@RequestBody RoleDto dto) {
        return this.findById(dto);
    }

    @PostMapping("findById")
    public RoleDto findById(RoleDto dto) {
        roleBlh.findById(dto);
        return dto;
    }
}
