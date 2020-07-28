package com.dhcc.piccbid.service.dataauthority.impl;

import com.alibaba.fastjson.JSON;
import com.dhcc.framework.app.service.common.AbstractBaseService;
import com.dhcc.framework.app.service.common.CommonService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dao.dataauthority.DataAuthorityDao;
import com.dhcc.piccbid.dao.dataauthority.DataAuthorityJDBCDao;
import com.dhcc.piccbid.dto.dataauthority.DataAuthorityDto;
import com.dhcc.piccbid.entity.dataauthority.DataAhthorityTreeNode;
import com.dhcc.piccbid.entity.dataauthority.DataAuthority;
import com.dhcc.piccbid.entity.dataauthority.DataAuthorityVO;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.service.dataauthority.DataAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-25 09:36:33
 */
@Service("dataAuthorityService")
public class DataAuthorityServiceImpl extends AbstractBaseService<DataAuthority, String> implements DataAuthorityService {

    private DataAuthorityDao dataAuthorityDao;

    @Autowired
    private DataAuthorityJDBCDao dataAuthorityJDBCDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public DataAuthorityServiceImpl(DataAuthorityDao dataAuthorityDao) {
        super(dataAuthorityDao);
        this.dataAuthorityDao = dataAuthorityDao;
    }

    @Override
    public void list(DataAuthorityDto dto) {
        dataAuthorityJDBCDao.dataAuthorityList(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, DataAuthorityVO.class, "t1.id");

    }

    @Override
    public String listAllTree(DataAuthorityDto dto) {
        List<DataAuthorityVO> dataAuthorities = dataAuthorities(dto);
        List<DataAhthorityTreeNode> treeNodes = bulidTree(parseMenuComplex(dataAuthorities));
        DataAhthorityTreeNode treeNode = new DataAhthorityTreeNode();
        treeNode.setChildren(treeNodes);
        treeNode.setName("数据权限");
        return JSON.toJSONString(treeNode);
    }

    //组装标准ztree树
    public List<DataAhthorityTreeNode> parseMenuComplex(List<DataAuthorityVO> dataAuthorities) {
        if (dataAuthorities != null && dataAuthorities.size() > 0) {
            List<DataAhthorityTreeNode> treeNodesList = new ArrayList<>();
            //将Dictdiag的数据放到TreeNode里面
            for (DataAuthorityVO dataAuthority : dataAuthorities) {
                DataAhthorityTreeNode treeNode = new DataAhthorityTreeNode();
                treeNode.setId(dataAuthority.getId());
                treeNode.setParentId(dataAuthority.getParentId());
                treeNode.setParentLeaf(dataAuthority.getParentLeaf());
                treeNode.setCityCode(dataAuthority.getCityCode());
                treeNode.setOrgCode(dataAuthority.getOrgCode());
                //treeNode.setChecked("true");
                //默认都没有子节点
                //treeNode.setHasChildren("0");
                if (dataAuthority.getOrgName() != null) {
                    treeNode.setName(dataAuthority.getOrgName());
                } else {
                    treeNode.setName(dataAuthority.getCityName());
                }
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
    public List<DataAhthorityTreeNode> bulidTree(List<DataAhthorityTreeNode> treeNodes) {

        List<DataAhthorityTreeNode> trees = new ArrayList<>();
        for (DataAhthorityTreeNode treeNode : treeNodes) {
            if (treeNode.getParentId() == null || "".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (DataAhthorityTreeNode it : treeNodes) {
                //给子节点添加children
                if (treeNode.getId().equals(it.getParentId())) {
                    if (treeNode.getChildren() == null) {
                        //treeNode.setHasChildren("1");
                        treeNode.setChildren(new ArrayList<DataAhthorityTreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }


    //返回数据库查询出来的list
    public List<DataAuthorityVO> dataAuthorities(DataAuthorityDto dto) {
       /* String sql="select  t1.*, t2.unit_name as hispatial_name from (select  a.* ,b.unit_name as city_name from t_piccbid_dataauthority a left join t_piccbid_unit b on a.city_code=b.unit_id)" +
                "t1 left join t_piccbid_unit t2 on  t1.org_code =t2.unit_id";*/
        //查询所有的菜单
        dataAuthorityJDBCDao.dataAuthorityList(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, DataAuthorityVO.class, "t1.id");
        List<DataAuthorityVO> dataAuthorities = (List<DataAuthorityVO>) dto.getPageModel().getPageData();
        /*   List<DataAuthorityVO> dataAuthorityVOS= jdbcTemplate.query(sql, new Object[]{}, new BeanPropertyRowMapper<>(DataAuthorityVO.class));
         */
        return dataAuthorities;
    }

    protected Specification<DataAuthority> getListSpecification(final DataAuthorityDto dto) {
        return new Specification<DataAuthority>() {
            @Override
            public Predicate toPredicate(Root<DataAuthority> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                //构�?�查询条�?
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }

    @Override
    public void listTreeTable(DataAuthorityDto dto) {
        List<DataAuthorityVO> roleList = new ArrayList<>();
        String id = dto.getDataAuthority().getId();
        dataAuthorityJDBCDao.dataAuthorotyTreeTable(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(1000);
        commonService.fillSqlPageModelData(pageModel, DataAuthorityVO.class, "t1.id");
        List<DataAuthorityVO> dataAuthorityVOS = (List<DataAuthorityVO>) dto.getPageModel().getPageData();
        // System.out.println("没有筛选之前" + JSON.toJSONString(dataAuthorityVOS));
        //对数据库查出了的数据进行递归找到一个节点下的所有节点
        List<DataAuthorityVO> rolesList = returnChildrenList(dataAuthorityVOS, id, roleList);
        List<DataAuthorityVO> roleListAll = addFirstNode(rolesList, id, dataAuthorityVOS);
        // Collections.reverse(roleListAll);
        dto.getPageModel().setPageData(roleListAll);
    }


    //返回某个节点下的所有子节点
    private List<DataAuthorityVO> returnChildrenList(List<DataAuthorityVO> roles, String id, List<DataAuthorityVO> roleList) {
        for (DataAuthorityVO unit : roles) {
            if (id.equals(unit.getParentId())) {
                returnChildrenList(roles, unit.getId(), roleList);
                //判断是否为一个组织，如果是组织则不会展示在table中
               /* if (!"1".equals(unit.getIsUnit())) {
                    roleList.add(unit);
                }*/
            }
        }

        return roleList;
    }

    //添加点击后的第一级节点信息
    private List<DataAuthorityVO> addFirstNode(List<DataAuthorityVO> roleList, String id, List<DataAuthorityVO> roles) {
        for (DataAuthorityVO unit : roles) {
            if (id.equals(unit.getId()))
               /* //如果是组织架构那么展示点击处的第一级节点
                if (!"1".equals(unit.getIsUnit())) {
                    roleList.add(unit);
                }*/
                roleList.add(unit);
        }
        return roleList;
    }

    @Override
    public void findCity(DataAuthorityDto dto) {
        dataAuthorityJDBCDao.findCity(dto);
        PageModel pageModel = dto.getPageModel();
        commonService.fillSqlPageModelData(pageModel, DictResponseVo.class, "id");
    }

    @Override
    public void findOrg(DataAuthorityDto dto) {
        dataAuthorityJDBCDao.findOrg(dto);
        PageModel pageModel = dto.getPageModel();
        pageModel.setPageSize(10000);
        commonService.fillSqlPageModelData(pageModel, DictResponseVo.class, "id");
    }
}
