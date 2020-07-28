package com.dhcc.piccbid.service.button.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dao.button.ButtonDao;
import com.dhcc.piccbid.dao.button.ButtonJDBCDao;
import com.dhcc.piccbid.dao.menu.MenuJDBCDao;
import com.dhcc.piccbid.dto.button.ButtonDto;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.piccbid.entity.menu.Menu;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.button.ButtonService;
import com.dhcc.piccbid.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 鎻忚堪: TODO<br/>
 * 鍏徃: 涓滃崕杞欢鑲′唤鍏徃<br/>
 * 鐗堟潈: dhcc2017<br/>
 *
 * @author ll
 * @version V1.0
 * @date 2019-07-10 09:17:54
 */
@Service("buttonService")
public class ButtonServiceImpl extends AbstractBaseService<Button, String> implements ButtonService {

    private ButtonDao buttonDao;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ButtonJDBCDao buttonJDBCDao;
    @Autowired
    private MenuJDBCDao menuJDBCDao;
    @Autowired
    private CommonService commonService;

    public ButtonServiceImpl(ButtonDao buttonDao) {
        super(buttonDao);
        this.buttonDao = buttonDao;
    }

    @Override
    public PageModel list(ButtonDto dto) {
        Specification<Button> spec = getListSpecification(dto);
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        Page<Button> page = buttonDao.findAll(spec, dto.getPageModel().getPageable());
        dto.getPageModel().setPage(page);
        return dto.getPageModel();
    }

    @Override
    public PageModel search(ButtonDto dto) {
        buttonJDBCDao.search(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Button.class, "id");
        return dto.getPageModel();
    }

    protected Specification<Button> getListSpecification(final ButtonDto dto) {
        return new Specification<Button>() {
            @Override
            public Predicate toPredicate(Root<Button> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    public String listAllTree(ButtonDto dto) {
        MenuDto menuDto = new MenuDto();
        String listAllTreeString = menuService.buttonAllTreeList(menuDto);
        return listAllTreeString;
    }


   /* @Override
    public PageModel listTreeTable(ButtonDto dto) {
        List<Menu> menuList = new ArrayList<>();
        Button button = dto.getButton();
        String parentLeaf = "";
        String id = "";
        if (dto.getButton() != null) {
            parentLeaf = dto.getButton().getParentLeaf();
            id = dto.getButton().getId();

        }
        MenuDto menuDto = new MenuDto();
        menuDto.setMenu(new Menu());
        menuDto.getMenu().setParentLeaf(parentLeaf);
        menuDto.getMenu().setId(id);
        menuJDBCDao.menuTreeTable(menuDto);
        PageModel pageModel = menuDto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        List<Menu> menus = (List<Menu>) menuDto.getPageModel().getPageData();
        List<Button> buttons = menuService.buttons(menuDto);
        menuService.getAllMenus(menus, buttons);
        //System.out.println("所有的"+ JSON.toJSONString(menus));
        menuList = menuService.getButtonTreeTable(menus, id, menuList);
        List<Menu> menuListAll = menuService.addButtonFirstNode(menuList, id, menus);
        //根据查询条件进行模糊查询
        List<Menu> resultMenu = getMenuByLimit(menuListAll, button);
        dto.getPageModel().setPageData(resultMenu);
        return pageModel;
    }*/

    @Override
    public PageModel listTreeTable(ButtonDto dto) {
       buttonJDBCDao.selectListTreeTable(dto);
        PageModel pageModel= dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel,Menu.class,"id");
        pageModel=dto.getPageModel();
        return pageModel;
    }




    private List<Menu> getMenuByLimit(List<Menu> menuListAll, Button button) {
        List<Menu> menuList = new ArrayList<>();
        String buttonCode = button.getButtonCode();
        String buttonName = button.getButtonName();

        if (!"".equals(buttonCode) && buttonCode != null || !"".equals(buttonName) && buttonName != null) {
            for (Menu menu : menuListAll) {
                if (menu.getMenuName() == null) {
                    menu.setMenuName("");
                }
                if (menu.getMenuCode() == null) {
                    menu.setMenuCode("");
                }

                // System.out.println(menu.getMenuName().startsWith(buttonName));
                if (menu.getMenuCode().startsWith(buttonCode) && menu.getMenuName().startsWith(buttonName)) {
                    menuList.add(menu);
                }
            }
        } else {
            menuList = menuListAll;
        }


        return menuList;
    }

    @Override
    public String getButtonAuthoByUserId(ButtonDto dto) {
        String resultString = "";
        List<String> list;
        StringBuilder stringBuilderResult = new StringBuilder();
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
       // String id = user.getId();
        String roleId=user.getRoleId();
        //根据当前用户的id去查询已授权的按钮id
      //  String buttonString = buttonJDBCDao.getButtonAuthoByUserId(id);
        String buttonString= buttonJDBCDao.getButtonAuthoByroleCode(roleId);
        if (!"".equals(buttonString)) {
            list = JSONObject.parseArray(buttonString, String.class);
            if (list.size() > 0) {
                for (String s : list) {
                    stringBuilderResult.append("'");
                    stringBuilderResult.append(s);
                    stringBuilderResult.append("'");
                    stringBuilderResult.append(",");
                }
                stringBuilderResult.deleteCharAt(stringBuilderResult.length() - 1);
            }
        }
        String stringBuilderResultString = stringBuilderResult.toString();
        //根据按钮id去查询按钮code返回List<String>
        if (!"".equals(stringBuilderResultString)) {
            resultString = buttonJDBCDao.getButtonCode(stringBuilderResultString);
        }
        return resultString;
    }

    @Override
    public int isExist(ButtonDto dto) {
        int count = buttonJDBCDao.isExist(dto);
        return count;
    }

}
