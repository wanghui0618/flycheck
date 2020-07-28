package com.dhcc.piccbid.service.menu.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.piccbid.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.menu.MenuDao;
import com.dhcc.piccbid.dao.menu.MenuJDBCDao;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.dictdiag.TreeNode;
import com.dhcc.piccbid.entity.menu.Menu;

import com.dhcc.piccbid.service.menu.MenuService;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author WJL
 * @version V1.0
 * @date 2019-06-12 09:51:27
 */
@Service("menuService")
public class MenuServiceImpl extends AbstractBaseService<Menu, String> implements MenuService {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    private MenuDao menuDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private MenuJDBCDao menuJDBCDao;

    public MenuServiceImpl(MenuDao menuDao) {
        super(menuDao);
        this.menuDao = menuDao;
    }

    @Override
    public PageModel list(MenuDto dto) {
        Specification<Menu> spec = getListSpecification(dto);
        if (dto.getPageModel() == null) {
            dto.setPageModel(new PageModel());
        }
        Page<Menu> page = menuDao.findAll(spec, dto.getPageModel().getPageable());
        dto.getPageModel().setPage(page);
        return dto.getPageModel();
    }

    @Override
    public PageModel search(MenuDto dto) {
        menuJDBCDao.search(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        return dto.getPageModel();
    }


    protected Specification<Menu> getListSpecification(final MenuDto dto) {
        return new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构造查询条件

                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    //返回数据库查询出来的list
    public String menuList(MenuDto menuDto) {
        List<Menu> menuList;
        String meunName = "";
        if (menuDto.getMenu() != null) {
            meunName = menuDto.getMenu().getMenuName();
        }
        //这里是所有菜单的list
        List<Menu> menus = menus(menuDto);
        //获取以及授权的id
       // List<String> list = getAuthorityMenuIdsString();
        //以角色授权表去加载菜单
        List<String> list = getAuthorityMenuIdsStringByRoleId();
        //如果用户在授权表中没有找到已授权的菜单则默认给所有的菜单
        if (list == null || list.size() == 0) {
            menuList = menus;
        } else {
            menuList = storeAuthorityMenu(list, menus);
        }

        List<TreeNode> treeNodes = bulidAccordingToTreeName(parseMenuComplex(menuList), meunName);
        return JSON.toJSONString(treeNodes);
    }

    @Override
    //返回数据库查询出来的list
    public String listTreeNew(MenuDto menuDto) {
        List<TreeNode> treeNodes=new ArrayList<>();
        List< List<TreeNode> > finalList=new ArrayList<>();
        List<Menu> menuList;
        //这里是所有菜单的list
        List<Menu> menus = menus(menuDto);
        //获取以及授权的id
         List<String> list = getAuthorityMenuIdsString();
       // List<String> list = new ArrayList<>();
        //如果用户在授权表中没有找到已授权的菜单则默认给所有的菜单
        if (list == null || list.size() == 0) {
            menuList = menus;
        } else {
            menuList = storeAuthorityMenu(list, menus);
        }
        //查询所有的第一级菜单
        PageModel pageModel=getFirstMenu(menuDto);
        List<Menu> firstMenu = (List<Menu>) pageModel.getPageData();
        for(Menu menu:firstMenu){
            String meunName= menu.getMenuName();
            treeNodes = bulidAccordingToTreeName(parseMenuComplex(menuList), meunName);
            finalList.add(treeNodes);
        }
        return JSON.toJSONString(finalList);
    }

    public List<Menu> storeAuthorityMenu(List<String> list, List<Menu> menus) {
        List<Menu> menuList = new ArrayList<>();
        for (Menu menu : menus) {
            for (String s : list) {
                if (menu.getId().equals(s)) {
                    menuList.add(menu);
                }
            }
        }
        return menuList;
    }

    public String menuAllList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        List<TreeNode> treeNodes = bulid(parseMenuComplex(menus));
        TreeNode treeNode = new TreeNode();
        treeNode.setChildren(treeNodes);
        treeNode.setName("全部菜单");

        return JSON.toJSONString(treeNode);
    }

    public String menuAuthoList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        List<TreeNode> treeNodes = parseMenuComplex(menus);
        List<String> list = getAuthorityMenuIdsStringByUserId(menuDto);
        List<TreeNode> treeNodeList = returnAuthorityMenu(list, treeNodes);
        List<TreeNode> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    public String roleMenuAuthoList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        List<TreeNode> treeNodes = parseMenuComplex(menus);
        List<String> list = getAuthorityMenuIdsStringByRoleId(menuDto);
        List<TreeNode> treeNodeList = returnAuthorityMenu(list, treeNodes);
        List<TreeNode> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    /**
     * 返回授权的按钮树（用户授权中）
     * @param menuDto
     * @return
     */
    @Override
    public String buttonAuthoList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        //获取所有的按钮
        List<Button> buttons = buttons(menuDto);
        //将button放入 menu中组成按钮树
        getAllMenus(menus, buttons);
        // System.out.println("组装完后的"+JSON.toJSONString(menuList));
        List<TreeNode> treeNodes = parseMenuComplex(menus);

        List<String> list = getAuthorityButtonIdsStringByUserId(menuDto);
        List<TreeNode> treeNodeList = returnAuthorityMenu(list, treeNodes);
        List<TreeNode> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }

    @Override
    public String roleButtonAuthoList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        //获取所有的按钮
        List<Button> buttons = buttons(menuDto);
        //将button放入 menu中组成按钮树
        getAllMenus(menus, buttons);
        // System.out.println("组装完后的"+JSON.toJSONString(menuList));
        List<TreeNode> treeNodes = parseMenuComplex(menus);

        List<String> list = getAuthorityButtonIdsStringByRole(menuDto);
        List<TreeNode> treeNodeList = returnAuthorityMenu(list, treeNodes);
        List<TreeNode> treeNode = bulid(treeNodeList);
        return JSON.toJSONString(treeNode);
    }
    /**
     * 返回所有按钮树（按钮树的管理）
     * @param menuDto
     * @return
     */
    @Override
    public String buttonAllTreeList(MenuDto menuDto) {
        List<Menu> menus = menus(menuDto);
        List<Button> buttons = buttons(menuDto);
        //将button放入 menu中组成按钮树
        getAllMenus(menus, buttons);
        List<TreeNode> treeNodes = bulidButtonTree(parseMenuComplexButtonTree(menus));
        TreeNode treeNode=new TreeNode();
        treeNode.setName("全部按钮");
        treeNode.setChildren(treeNodes);
        return JSON.toJSONString(treeNode);
    }


    //授权菜单的回显,checked设为true表示菜单已选择
    public List<TreeNode> returnAuthorityMenu(List<String> list, List<TreeNode> treeNodes) {
        for (TreeNode treeNode : treeNodes) {
            for (String s : list) {
                if (treeNode.getId().equals(s)) {
                    treeNode.setChecked("true");
                }
            }
        }
        return treeNodes;
    }

    @Override
    //返回数据库查询出来的list
    public List<Menu> menus(MenuDto menuDto) {
        //查询所有的菜单
        menuJDBCDao.menuList(menuDto);
        PageModel pageModel = menuDto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        List<Menu> menus = (List<Menu>) menuDto.getPageModel().getPageData();
        return menus;
    }

    @Override
    public List<Button> buttons(MenuDto dto) {
        menuJDBCDao.buttonsList(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Button.class, "id");
        List<Button> buttonList = (List<Button>) dto.getPageModel().getPageData();
       // System.out.println("按钮" + JSON.toJSONString(buttonList));
        return buttonList;
    }
    @Override
    public int isMenuExist(MenuDto menuDto) {
        menuJDBCDao.menuCount(menuDto);
        PageModel pageModel = menuDto.getPageModel();
        // pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        int count = menuDto.getPageModel().getTotals();
        return count;
    }

    @Override
    public PageModel listTreeTable(MenuDto menuDto) {
        List<Menu> menuList = new ArrayList<>();
        String id = menuDto.getMenu().getId();
        menuJDBCDao.menuTreeTable(menuDto);
        PageModel pageModel = menuDto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        List<Menu> menus = (List<Menu>) menuDto.getPageModel().getPageData();
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        List<Menu> menusList = returnChildrenList(menus, id, menuList);
        List<Menu> menuListAll = addFirstNode(menusList, id, menus);
        Collections.reverse(menuListAll);
        menuDto.getPageModel().setPageData(menuListAll);
        return pageModel;
    }

    @Override
    public PageModel listTreeTableNew(MenuDto menuDto){
        menuJDBCDao.menuTreeTableNew(menuDto);
        PageModel pageModel=menuDto.getPageModel();
        commonService.fillSqlPageModelData(pageModel,Menu.class,"id");
        return menuDto.getPageModel();
    }

    public List<Menu>  getButtonTreeTable( List<Menu> menus,String id,List<Menu> menuList ){
        List<Menu> menuLists=new ArrayList<>();
        List<Menu> menusList = returnChildrenList(menus, id, menuList);
       // System.out.println("子节点"+JSON.toJSONString(menusList));
        for (Menu menu:menusList){
           // System.out.println("进来了");
            if ("1".equals(menu.getMenuUrl())){
                menuLists.add(menu);
            }
        }
        return menuLists;
   }

    public PageModel getFirstMenu(MenuDto menuDto) {
        menuJDBCDao.getFirstMenu(menuDto);
        List<String> list = getAuthorityMenuIdsString();
        PageModel pageModel = menuDto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, Menu.class, "id");
        List<Menu> menus = (List<Menu>) menuDto.getPageModel().getPageData();
        List<Menu> menuList = getAuthorityFirstMenu(list, menus);
        menuDto.getPageModel().setPageData(menuList);
        return pageModel;
    }

    //获取当前登录用户的菜单id集合
    public List<String> getAuthorityMenuIdsString() {
        List<String> list = new ArrayList<>();
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        String roleId=user.getRoleId();
        //String id = user.getId();
       // String menuString = menuJDBCDao.selectIsAuthoMenu(id);
        String menuString = menuJDBCDao.selectIsAuthoMenuByRoleCode(roleId);
        if (!"".equals(menuString)) {
            list = JSONObject.parseArray(menuString, String.class);
        }
        return list;
    }

    //获取当前登录用户的菜单id集合
    public List<String> getAuthorityMenuIdsStringByRoleId() {
        List<String> list = new ArrayList<>();
        User user = (User) WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
        String roleId = user.getRoleId();
        //String id = user.getId();
        //根据用户id去加载菜单树
       // String menuString = menuJDBCDao.selectIsAuthoMenu(id);
        //根据用户角色去加载菜单树
        String menuString = menuJDBCDao.selectIsAuthoMenuByRoleCode(roleId);
        if (!"".equals(menuString)) {
            list = JSONObject.parseArray(menuString, String.class);
        }
        return list;
    }

    //回显table表中选择行的用户的菜单id集合
    public List<String> getAuthorityMenuIdsStringByUserId(MenuDto dto) {
        List<String> list = new ArrayList<>();
        String id = dto.getMenu().getId();
        String menuString = menuJDBCDao.selectIsAuthoMenu(id);
        if (!"".equals(menuString)) {
            list = JSONObject.parseArray(menuString, String.class);
        }
        return list;
    }

    //回显table表中选择行的用户的按钮id集合
    public List<String> getAuthorityButtonIdsStringByUserId(MenuDto dto) {
        List<String> list = new ArrayList<>();
        String id = dto.getMenu().getId();
        String buttonString = menuJDBCDao.selectIsAuthoButton(id);
        if (!"".equals(buttonString)) {
            list = JSONObject.parseArray(buttonString, String.class);
        }
        return list;
    }
         //回显table表中选择行的角色的按钮id集合
    public List<String> getAuthorityButtonIdsStringByRole(MenuDto dto) {
        List<String> list = new ArrayList<>();
        String id = dto.getMenu().getId();
        String buttonString = menuJDBCDao.selectRoleIsAuthoButton(id);
        if (!"".equals(buttonString)) {
            list = JSONObject.parseArray(buttonString, String.class);
        }
        return list;
    }
    //回显table表中选择行的角色的菜单id集合
    public List<String> getAuthorityMenuIdsStringByRoleId(MenuDto dto) {
        List<String> list = new ArrayList<>();
        String id = dto.getMenu().getId();
        String menuString = menuJDBCDao.selectRoleIsAuthoMenu(id);
        if (!"".equals(menuString)) {
            list = JSONObject.parseArray(menuString, String.class);
        }
        return list;
    }

    //获取已经授权的第一级菜单
    public List<Menu> getAuthorityFirstMenu(List<String> list, List<Menu> menuList) {
        List<Menu> menus = new ArrayList<>();
        for (Menu menu : menuList) {
            for (String s : list) {
                if (menu.getId().equals(s)) {
                    menus.add(menu);
                }
            }
        }
        return menus;
    }

    //组装标准ztree树
    public List<TreeNode> parseMenuComplex(List<Menu> menus) {
        if (menus != null && menus.size() > 0) {
            List<TreeNode> treeNodesList = new ArrayList<>();
            //将Dictdiag的数据放到TreeNode里面
            for (Menu menu : menus) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(menu.getId());
                treeNode.setMenuCode(menu.getMenuCode());
                treeNode.setMenuName(menu.getMenuName());
                treeNode.setParentId(menu.getParentId());
                treeNode.setParentLeaf(menu.getParentLeaf());
                treeNode.setMenuUrl(menu.getMenuUrl());
                treeNode.setOnclickBef(menu.getOnclickBef());
                treeNode.setOnclickAft(menu.getOnclickAft());
                treeNode.setOwner(menu.getOwner());
                //treeNode.setChecked("true");
                //默认都没有子节点
                treeNode.setHasChildren("0");
                treeNode.setName(menu.getMenuName());
                treeNodesList.add(treeNode);
            }
            return treeNodesList;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 两层循环实现建树
     *
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public List<TreeNode> bulid(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes) {
                //给子节点添加children
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setHasChildren("1");
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    public List<TreeNode> bulidButtonTree(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes) {
                //给子节点添加children
                if (treeNode.getId().equals(it.getParentId())) {
                    //如果这个节点是按钮，则将他的父节点设置为‘3’表示可以进行新增
                    if ("1".equals(it.getIsAdd())){
                        treeNode.setIsAdd("3");
                    }

                    if (treeNode.getChildren() == null) {
                        treeNode.setHasChildren("1");
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }


    public List<TreeNode> bulidAccordingToTreeName(List<TreeNode> treeNodes, String menuName) {
        String parentId = menuJDBCDao.selectParentIdByMenuName(menuName);
        //System.out.println(JSON.toJSONString(treeNodes));
        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {

            if (parentId.equals(treeNode.getId())) {
                trees.add(treeNode);
            }
            for (TreeNode it : treeNodes) {
                //给子节点添加children
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setHasChildren("1");
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    //返回某个节点下的所有子节点
    private List<Menu> returnChildrenList(List<Menu> menus, String id, List<Menu> menuList) {
        for (Menu menu : menus) {
	              /*  if (id.equals(menu.getId())) {
	                    MenuList.add(menu);
	                }*/
            if (id.equals(menu.getParentId())) {
                returnChildrenList(menus, menu.getId(), menuList);
                menuList.add(menu);
            }
        }

        return menuList;
    }

    //添加点击后的第一级节点信息
    public List<Menu> addFirstNode(List<Menu> menuList, String id, List<Menu> menus) {
        for (Menu menu : menus) {
            if (id.equals(menu.getId()))
                menuList.add(menu);
        }
        return menuList;
    }

    public List<Menu> addButtonFirstNode(List<Menu> menuList, String id, List<Menu> menus) {
        for (Menu menu : menus) {
            if (id.equals(menu.getId())&&"1".equals(menu.getMenuUrl()))
                menuList.add(menu);
        }
        return menuList;
    }
    @Override
	public void deleteArray(String[] arrays) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from t_piccbid_menu where id in(");
		int length = arrays.length;
		for(int i = 0 ; i <= length-1; i++) {
			if(i==length-1) {
				sql.append("'"+arrays[i]+"'"+")");
			}else {
				sql.append("'"+arrays[i]+"'"+",");
			}
		}
		jdbcTemplate.execute(sql.toString());
	}
	@Override
    public void updateAdminMenuAutho(String name){
        menuJDBCDao.updateAdminMenuAutho(name);
    }

    @Override
    public void updateAdminMenuAuthoForRole(String roleCode){
        menuJDBCDao.updateAdminMenuAuthoForRole(roleCode);
    }

    public List<Menu> getAllMenus(List<Menu> menus, List<Button> buttons) {
        for (Button button : buttons) {
            Menu menu = new Menu();
            menu.setId(button.getId());
            menu.setParentId(button.getParentId());
            menu.setParentLeaf(button.getParentLeaf());
            menu.setMenuCode(button.getButtonCode());
            menu.setMenuName(button.getButtonName());
            menu.setOnclickAft(button.getButtonPageName());
            /**
             * 借用这个字段  用来区分菜单表和按钮表
             */
            menu.setMenuUrl("1");
            menus.add(menu);
        }
        return menus;
    }
    //组装标准ztree树
    public List<TreeNode> parseMenuComplexButtonTree(List<Menu> menus) {
        if (menus != null && menus.size() > 0) {
            List<TreeNode> treeNodesList = new ArrayList<>();
            //将Dictdiag的数据放到TreeNode里面
            for (Menu menu : menus) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(menu.getId());
                treeNode.setMenuCode(menu.getMenuCode());
                treeNode.setMenuName(menu.getMenuName());
                treeNode.setParentId(menu.getParentId());
                treeNode.setParentLeaf(menu.getParentLeaf());
                treeNode.setRoleId(menu.getOnclickAft());
                if("1".equals(menu.getMenuUrl())) {
                    treeNode.setIsAdd(menu.getMenuUrl());
                }
                //默认都没有子节点
                treeNode.setHasChildren("0");
                treeNode.setName(menu.getMenuName());
                treeNodesList.add(treeNode);
            }
            return treeNodesList;
        } else {
            return new ArrayList<>();
        }
    }
}
