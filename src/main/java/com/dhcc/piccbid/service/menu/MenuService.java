package com.dhcc.piccbid.service.menu;

import java.util.List;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.piccbid.entity.menu.Menu;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author WJL
 * @date 2019-06-12 09:51:27
 * @version V1.0
 */
public interface MenuService extends BaseService<Menu, String> {

    PageModel list(MenuDto dto);
    PageModel search(MenuDto dto);
    String menuList(MenuDto dto);
    String listTreeNew(MenuDto dto);
    String menuAllList(MenuDto dto);
    String menuAuthoList(MenuDto dto);
    String roleMenuAuthoList(MenuDto dto);
    String buttonAuthoList(MenuDto dto);
    String roleButtonAuthoList(MenuDto dto);
    String buttonAllTreeList(MenuDto dto);
    List<Menu> menus(MenuDto dto);
    List<Button> buttons(MenuDto dto);
    PageModel listTreeTable(MenuDto dto);
    PageModel listTreeTableNew(MenuDto dto);
    PageModel getFirstMenu(MenuDto dto);
    int isMenuExist(MenuDto MenuDto);
    void deleteArray(String[] arrays);
    void updateAdminMenuAutho(String name);
    void updateAdminMenuAuthoForRole(String roleCode);
    List<Menu> getAllMenus(List<Menu> menus, List<Button> buttons);

    /**
     * 获取按钮树节点下的按钮
     * @param menus
     * @param id
     * @param menuList
     * @return
     */
    List<Menu>  getButtonTreeTable( List<Menu> menus,String id,List<Menu> menuList );

    List<Menu> addButtonFirstNode(List<Menu> menuList, String id, List<Menu> menus);
}
