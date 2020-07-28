package com.dhcc.piccbid.blh.unit;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.unit.UnitDto;
import com.dhcc.piccbid.entity.unit.Unit;
import com.dhcc.piccbid.service.unit.UnitService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class UnitBlh extends BaseAbstractBlh<UnitDto> {
    private static final String LOGIN_NAME="admin";

    private static Log logger = LogFactory.getLog(UnitBlh.class);

    @Resource
    private UnitService unitService;

    public UnitBlh() {
        logger.debug("UnitBlh Init");
    }


    public void list(BaseAbstractDto basedto) {
        PageModel pageModel = new PageModel();
        UnitDto dto = super.getExactlyDto(basedto);
        if (dto.getUnit() == null) {
            //调用service查询方法
            pageModel = unitService.list(dto);
        }
        //如果其中一个值不为null，说明是查询
        else if (dto.getUnit().getUnitName() != null || dto.getUnit().getPhone() != null || dto.getUnit().getConcat() != null) {
            pageModel = unitService.search(dto);
        }
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);
    }

    public String synInfo(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        unitService.synInfo(dto);
        return "success";
    }
    public String listAllTree(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = unitService.unitAllList(dto);
        return jsonString;
    }

    public void listTreeTable(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        unitService.listTreeTable(dto);
    }


    public String save(BaseAbstractDto basedto) {
        UnitDto dto = super.getExactlyDto(basedto);
      /*  //当选择医院下面的地区时，需要将传过来的unitName 设为null 因为后面会拆分
        if ("0".equals(dto.getUnit().getIsUnit())){
            dto.getUnit().setUnitName(null);
        }*/
        //勾选医院  拆分code和name
        getUnitIdAndName(dto);
        //如果这ParetId为null则表示是全部组织的下一级，也就是第一级节点，这是一个组织架构赋值isUnit为1；
        if (dto.getUnit().getParentId() == null) {
            dto.getUnit().setIsUnit("1");
            //如果isUnit为null，表示新增的组织架构（除医院下面的地区：组织架构和"系统"，"医保局"，"公司"）
        } else if (dto.getUnit().getIsUnit() == null) {
            dto.getUnit().setIsUnit("0");
        }

        //新增
        if (dto.getUnit().getId() == null) {
            //roleName 必传
            if (dto.getUnit().getUnitCode() == null) {
                return "false";
            }//当传入的unitName为null时
            else {
                //拿到新增的unitName和unitId为唯一不能重复 数据库查询，如果存在则新增失败，当使用count
                int count = unitService.isExist(dto);
                if (count > 0) {
                    return "exist";
                } else {
                    //调用对应的service保存方法，新增
                    dto.getUnit().setAddDate(new Date());
                    dto.getUnit().setUpdateDate(new Date());
                    unitService.save(dto.getUnit());
                    //刷新用户的数据权限为所有
                  //  String name="管理员";
//                    updateUserDataAuthorityAll(LOGIN_NAME);
                    return "success";
                }
            }
            //编辑
        } else {
            int count = unitService.isExist(dto);
            if (count > 0) {
                return "exist";
            } else {
                dto.getUnit().setUpdateDate(new Date());
                dto.getUnit().setAddDate(new Date());
                unitService.save(dto.getUnit());
                return "success";
            }
        }

    }


    //将unitId和unitName拆分保存到数据库
    public void getUnitIdAndName(UnitDto dto) {
        String unitId = dto.getUnit().getUnitCode();
        String cityCode=dto.getUnit().getCityCode();
        if (unitId!= null&&!"".equals(unitId)) {
            //切割
            String[] strings = unitId.split("!!##@1a@a1@##!!");
            dto.getUnit().setUnitCode(strings[0]);
            if (strings.length > 1) {
                dto.getUnit().setUnitName(strings[1]);
            }
        }else {
            if (cityCode!=null&&!"".equals(cityCode)){
                dto.getUnit().setUnitCode(cityCode);
            }
        }
    }
    //使用vo类去保存

    /**
     * 删除
     *
     * @param basedto
     */
    public void delete(BaseAbstractDto basedto) {
        UnitDto dto = super.getExactlyDto(basedto);
        String id = dto.getUnit().getId();
        String excuteQuery = dto.getUnit().getUnitCode();
        //为0说明删除的是子节点
        if ("0".equals(excuteQuery)) {
            //调用对应的service删除方法
            unitService.delete(id);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        } else {
            String[] arrays = getDeleteIds(dto);
            // roleService.listTreeTable();
            unitService.deleteArray(arrays);
            dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
        }

    }

    public String dataAuthoList(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = unitService.dataAuthoList(dto);
        return jsonString;
    }

    public String roleDataAuthoList(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = unitService.roleDataAuthoList(dto);
        return jsonString;
    }
  public List<String> getUserDataAhthority() {
        return  unitService.getUserDataAhthority();
    }

    public List<String> getUserDataAhthorityForApp(String userId) {
        return  unitService.getUserDataAhthorityForApp(userId);
    }

    public StringBuilder appendDataAuhoritySql(String limitAuthorityDict, StringBuilder stringBuilder, List<String> list) {

        return  unitService.appendDataAuhoritySql(limitAuthorityDict,stringBuilder,list);
    }

    public void updateUserDataAuthorityAll(String name){
        unitService.updateUserDataAuthorityAll(name);
    }

    private String[] getDeleteIds(UnitDto dto) {
        List<Unit> unitList = (List<Unit>) unitService.listTreeTableForDelete(dto).getPageData();
        List<String> stringList = new ArrayList<>();
        for (Unit unit : unitList) {
            stringList.add(unit.getId());
        }
        // System.out.println("list的值为");
        String[] ids = new String[stringList.size()];
        stringList.toArray(ids);
        return ids;
    }


    public void findById(BaseAbstractDto basedto) {
        UnitDto dto = super.getExactlyDto(basedto);
        Unit entity = unitService.findOne(dto.getUnit().getId());
        dto.setUnit(entity);
    }
    public String roleAreaAuthoList(BaseAbstractDto baseAbstractDto) {
        UnitDto dto = super.getExactlyDto(baseAbstractDto);
        String jsonString = unitService.roleAreaAuthoList(dto);
        return jsonString;
    }
//    public void insertDataAutho(BaseAbstractDto basedto) {
//        unitService.insertDataAutho();
//    }

}
