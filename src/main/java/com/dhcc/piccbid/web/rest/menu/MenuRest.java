package com.dhcc.piccbid.web.rest.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dhcc.piccbid.entity.menu.Menu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.menu.MenuBlh;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.page.Page;

import java.util.List;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author WJL
 * @version V1.0
 * @date 2019-06-12 09:51:27
 */
@RestController
@RequestMapping("/dhccApi/menu/menu")
public class MenuRest {

    @Resource
    private MenuBlh menuBlh;

    @PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) MenuDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(MenuDto dto) {
        Page page = new Page();
        menuBlh.list(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    /**
     * 根据八大目录名称获取下面的菜单
     * @param dto
     * @return
     */
    @PostMapping(value = "listTree", consumes = "application/json")
    public String listTreeRest(@RequestBody(required = false) MenuDto dto) {
        return this.listTree(dto);
    }

    @RequestMapping("listTree")
    public String listTree(MenuDto dto) {
        return menuBlh.listTree(dto);
    }

      //飞行检查的侧面菜单,只有两级
    @PostMapping(value = "listTreeNew", consumes = "application/json")
    public String listTreeNewRest(@RequestBody(required = false) MenuDto dto) {
        return this.listTreeNew(dto);
    }
    @RequestMapping("listTreeNew")
    public String listTreeNew(MenuDto dto) {
        return menuBlh.listTreeNew(dto);
    }

    @RequestMapping("listAllTree")
    public String listAllTree(MenuDto dto) {
        return menuBlh.listAllTree(dto);
    }


    /**
     * 用户菜单授权权限树
     * @param dto
     * @return
     */
    @RequestMapping("menuAuthoList")
    public String menuAuthoList(MenuDto dto) {
        return menuBlh.menuAuthoList(dto);
    }

    /**
     * 角色菜单授权权限树
     * @param dto
     * @return
     */

    @RequestMapping("roleMenuAuthoList")
    public String roleMenuAuthoList(MenuDto dto) {
        return menuBlh.roleMenuAuthoList(dto);
    }

    /**
     * 用户按钮   授权树
     * @param dto
     * @return
     */

    @RequestMapping("buttonAuthoList")
    public String buttonAuthoList(MenuDto dto) {
        return menuBlh.buttonAuthoList(dto);
    }
    /**
     * 角色按钮   授权树
     * @param dto
     * @return
     */

    @RequestMapping("roleButtonAuthoList")
    public String roleButtonAuthoList(MenuDto dto) {
        return menuBlh.roleButtonAuthoList(dto);
    }


    /**
     * 获取按钮树
     * @param dto
     * @return
     */
 @RequestMapping("buttonAllTreeList")
    public String buttonAllTreeList(MenuDto dto) {
        return menuBlh.buttonAllTreeList(dto);
    }

    /**
     * 点击右边的树节点  左边展示节点下所有的菜单
     * @param dto
     * @return
     */

    @PostMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody(required = false) MenuDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(MenuDto dto) {
        menuBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }


    /**
     * 获取八大目录中已授权的目录
     * @param dto
     * @return
     */
    @PostMapping(value = "getFirstMenu", consumes = "application/json")
    public List<Menu> getFirstMenuRest(@RequestBody MenuDto dto) {
        return this.getFirstMenu(dto);
    }

    @PostMapping("getFirstMenu")
    public List<Menu> getFirstMenu(MenuDto dto) {
        menuBlh.getFirstMenu(dto);
        List<Menu> menus = (List<Menu>) dto.getPageModel().getPageData();
        return menus;
    }

    @PostMapping(value = "save", consumes = "application/json")
    public MenuDto saveRest(@RequestBody MenuDto dto) {
        return this.save(dto);
    }

    @PostMapping("save")
    public MenuDto save(MenuDto dto) {
        return menuBlh.save(dto);
    }
/**
 * 根据用户昵称（login_name）刷新某个用户的菜单权限，刷新为所有的权限
 */
    @PostMapping(value = "updateAdminMenuAutho", consumes = "application/json")
    public String updateAdminMenuAuthoRest(@RequestBody String name) {
        return this.updateAdminMenuAutho(name);
    }

    @PostMapping("updateAdminMenuAutho")
    public String updateAdminMenuAutho(String name) {
        menuBlh.updateAdminMenuAutho(name);
        return "success";
    }

    /**
     * 根据用户的角色代码（role_code）刷新用户角色为（role_code）的用户的菜单权限，刷新为所有的菜单权限
     */
    @PostMapping(value = "updateAdminMenuAuthoForRole", consumes = "application/json")
    public String updateAdminMenuAuthoForRoleRest(@RequestBody String roleCode) {
        return this.updateAdminMenuAutho(roleCode);
    }

    @PostMapping("updateAdminMenuAuthoForRole")
    public String updateAdminMenuAuthoForRole(String roleCode) {
        menuBlh.updateAdminMenuAuthoForRole(roleCode);
        return "success";
    }


    /*
	通过传入request对象，获取前端传入数据的图片地址
     */
    @PostMapping(value = "delete", consumes = "application/json")
    public MenuDto deleteRest(HttpServletRequest request,@RequestBody MenuDto dto) {
        return this.delete(request,dto);
    }
  
    @PostMapping("delete")
    public MenuDto delete(HttpServletRequest request,MenuDto dto) {
        menuBlh.delete(request,dto);
        return dto;
    }
    
    
    @PostMapping(value = "update", consumes = "application/json")
    public MenuDto updateRest(@RequestBody MenuDto dto) {
        return this.update(dto);
    }

    @PostMapping("update")
    public MenuDto update(MenuDto dto) {
    	 return menuBlh.update(dto);
    }

    @PostMapping(value = "findById", consumes = "application/json")
    public MenuDto findByIdRest(@RequestBody MenuDto dto) {
        return this.findById(dto);
    }

    @PostMapping("findById")
    public MenuDto findById(MenuDto dto) {
        menuBlh.findById(dto);
        return dto;
    }
}
