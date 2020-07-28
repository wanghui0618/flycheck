package com.dhcc.piccbid.web.rest.unit;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dhcc.piccbid.blh.unit.UnitBlh;
import com.dhcc.piccbid.dto.unit.UnitDto;

import java.util.List;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-17 13:02:58
 */
@RestController
@RequestMapping("/dhccApi/unit/unit")
public class UnitRest {

    @Resource
    private UnitBlh unitBlh;

    @PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) UnitDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(UnitDto dto) {
        Page page = new Page();
        unitBlh.list(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    /**
     * 同步数据到unit表
     * @param dto
     * @return
     */
    @PostMapping(value = "synInfo", consumes = "application/json")
    public String synInfoRest(@RequestBody(required = false) UnitDto dto) {
        return this.synInfo(dto);
    }

    @RequestMapping("synInfo")
    public String synInfo(UnitDto dto) {
        return unitBlh.synInfo(dto);
    }
    @PostMapping(value = "listAllTree", consumes = "application/json")
    public String listAllTreeRest(@RequestBody(required = false) UnitDto dto) {
        return this.listAllTree(dto);
    }

    @RequestMapping("listAllTree")
    public String listAllTree(UnitDto dto) {
        return unitBlh.listAllTree(dto);
    }


    @PostMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody(required = false) UnitDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(UnitDto dto) {
        unitBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }


    @PostMapping(value = "save", consumes = "application/json")
    public String saveRest(@RequestBody UnitDto dto) {
        return this.save(dto);
    }

    @PostMapping("save")
    public String save(UnitDto dto) {
        return unitBlh.save(dto);
    }

    @PostMapping(value = "delete", consumes = "application/json")
    public UnitDto deleteRest(@RequestBody UnitDto dto) {
        return this.delete(dto);
    }

    @PostMapping("delete")
    public UnitDto delete(UnitDto dto) {
        unitBlh.delete(dto);
        return dto;
    }

    /**
     * 用户数据授权菜单树
     *
     * @param dto
     * @return
     */

    @RequestMapping(value = "dataAuthoList", consumes = "application/json")
    public String dataAuthoListRest(@RequestBody UnitDto dto) {
        return this.dataAuthoList(dto);
    }

    @RequestMapping("dataAuthoList")
    public String dataAuthoList(UnitDto dto) {
        String s = unitBlh.dataAuthoList(dto);
        return s;
    }

    /**
     * 角色数据授权菜单树
     *
     * @param dto
     * @return
     */

    @RequestMapping(value = "roleDataAuthoList", consumes = "application/json")
    public String roleDataAuthoListRest(@RequestBody UnitDto dto) {
        return this.roleDataAuthoList(dto);
    }

    @RequestMapping("roleDataAuthoList")
    public String roleDataAuthoList(UnitDto dto) {
        String s = unitBlh.roleDataAuthoList(dto);
        return s;
    }

    /**
     * 数据授权管控（获取当前用户的数据权限）
     *
     * @param
     * @return
     */

    @RequestMapping(value = "getUserDataAhthority", consumes = "application/json")
    public List<String> getUserDataAhthorityRest() {
        return this.getUserDataAhthority();
    }

    @RequestMapping("getUserDataAhthority")
    public List<String> getUserDataAhthority() {
        return unitBlh.getUserDataAhthority();
    }

    /**
     * 数据授权管控App端（根据传入用户id获取该用户的数据权限）
     *
     * @param
     * @return
     */

    @RequestMapping(value = "getUserDataAhthorityForApp", consumes = "application/json")
    public List<String> getUserDataAhthorityForAppRest(String userId) {
        return this.getUserDataAhthorityForApp(userId);
    }

    @RequestMapping("getUserDataAhthorityForApp")
    public List<String> getUserDataAhthorityForApp(String userId) {
        return unitBlh.getUserDataAhthorityForApp(userId);
    }

    /**
     *todo:拼接数据授权的sql
     */
    @RequestMapping(value = "appendDataAuhoritySql", consumes = "application/json")
    public StringBuilder appendDataAuhoritySqlRest(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list ) {
        return this.appendDataAuhoritySql(limitAuthorityDict,stringBuilder,list);
    }

    @RequestMapping("appendDataAuhoritySql")
    public StringBuilder appendDataAuhoritySql(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list) {
        return unitBlh.appendDataAuhoritySql(limitAuthorityDict,stringBuilder,list);
    }

    /**
     *  根据用户昵称（login_name）刷新用户的数据权限为所有权限
     *
     */
    @RequestMapping(value = "updateUserDataAuthorityAll", consumes = "application/json")
    public String updateUserDataAuthorityAllRest(@RequestBody String  name) {
        return this.updateUserDataAuthorityAll(name);
    }

    @RequestMapping("updateUserDataAuthorityAll")
    public String updateUserDataAuthorityAll(String name) {
        unitBlh.updateUserDataAuthorityAll(name);
        return "success";
    }

    @PostMapping(value = "findById", consumes = "application/json")
    public UnitDto findByIdRest(@RequestBody UnitDto dto) {
        return this.findById(dto);
    }

    @PostMapping("findById")
    public UnitDto findById(UnitDto dto) {
        unitBlh.findById(dto);
        return dto;
    }

    /**
     * 角色数据授权菜单树 (更新)
     *
     * @param dto
     * @return
     */

    @RequestMapping(value = "roleAreaAuthoList", consumes = "application/json")
    public String roleAreaAuthoListRest(@RequestBody UnitDto dto) {
        return this.roleAreaAuthoList(dto);
    }

    @RequestMapping("roleAreaAuthoList")
    public String roleAreaAuthoList(UnitDto dto) {
        String s = unitBlh.roleAreaAuthoList(dto);
        return s;
    }

//    //将授权树，插入数据库
//    @RequestMapping(value = "insertDataAutho", consumes = "application/json")
//    public String insertDataAuthoRest(@RequestBody UnitDto dto) {
//        return this.insertDataAutho(dto);
//    }
//
//    @RequestMapping("insertDataAutho")
//    public String insertDataAutho(UnitDto dto) {
//        unitBlh.insertDataAutho(dto);
//        return "success";
//    }
}
