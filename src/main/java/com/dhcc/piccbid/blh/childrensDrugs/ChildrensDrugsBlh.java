package com.dhcc.piccbid.blh.childrensDrugs;


import com.alibaba.fastjson.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.childrensDrugs.ChildrensDrugsDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import com.dhcc.piccbid.service.childrensDrugs.ChildrensDrugsService;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class ChildrensDrugsBlh extends BaseAbstractBlh<ChildrensDrugsDto> {

    private static Log logger = LogFactory.getLog(ChildrensDrugsBlh.class);

    @Autowired
    private ChildrensDrugsService childrensDrugsService;

    public ChildrensDrugsBlh() {
        logger.debug("ChildrensDrugsBlh Init");
    }

    /**
     *
     * 进入某个列表的入口方法（分页查询方法）
     * @param basedto
     */
    public void list(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        //调用service查询方法
        PageModel pageModel=childrensDrugsService.list(dto);
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setPageModel(pageModel);
    }

    /**
     *
     * 保存对象，若无ID则新建对象，若有ID则更新对象
     * @param basedto
     */
    public void save(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        //调用对应的service保存方法
        childrensDrugsService.save(dto.getChildrensDrugs());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    /**
     *
     * 删除
     * @param basedto
     */
    public void delete(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        //调用对应的service删除方法
        childrensDrugsService.delete(dto.getChildrensDrugs().getHisid());
        dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
    }

    /**
     *
     * 根据ID查询实体的方法
     * @param: basedto
     *
     */
    public void findById(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);

        //调用对应的service查询某个实体的方法
        ChildrensDrugs entity = childrensDrugsService.findOne(dto.getChildrensDrugs().getHisid());
        //不要删除这行代码，调用set是为以后 service层要加缓存
        dto.setChildrensDrugs(entity);
    }

    /**
     * 限儿童用药
     * @param basedto
     */
    public void childrensDrugs(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        PageModel pageModel = childrensDrugsService.childrensDrugs(dto);
        dto.setPageModel(pageModel);
    }

    /**
     * 限儿童用药
     * @param basedto
     */
    public void gather(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        PageModel pageModel = childrensDrugsService.gather(dto);
        dto.setPageModel(pageModel);
    }

    /**
     * 导出
     * @return
     */
    public XSSFWorkbook exportExcel(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        String param = dto.getParam();
        JSONObject jsonObject= (JSONObject)JSONObject.parse(param);
        if(jsonObject!=null) {
            if(dto.getChildrensDrugs()==null) {
                dto.setChildrensDrugs(new ChildrensDrugs());
            }
            ChildrensDrugs childrensDrugs = dto.getChildrensDrugs();
            dto.setSymbol(jsonObject.getString("symbol"));
            dto.setBalanceDate(jsonObject.getString("balanceDate"));
            dto.setAdmissionDate(jsonObject.getString("admissionDate"));
            dto.setDischargeDate(jsonObject.getString("dischargeDate"));
            childrensDrugs.setAdmissionDiseaseId(jsonObject.getString("childrensDrugs.admissionDiseaseId"));
            childrensDrugs.setAdmissionDiseaseName(jsonObject.getString("childrensDrugs.admissionDiseaseName"));
            childrensDrugs.setPatientAge(jsonObject.getString("childrensDrugs.patientAge"));
            childrensDrugs.setHospitalId(jsonObject.getString("childrensDrugs.hospitalId"));
            childrensDrugs.setHospitalName(jsonObject.getString("childrensDrugs.hospitalName"));
            childrensDrugs.setPatientName(jsonObject.getString(""));
            childrensDrugs.setItemId(jsonObject.getString("childrensDrugs.itemId"));
            childrensDrugs.setItemName(jsonObject.getString("childrensDrugs.itemName"));
        }
        return childrensDrugsService.exportExcel(dto);
    }

    public void detail(BaseAbstractDto basedto) {
        ChildrensDrugsDto dto = super.getExactlyDto(basedto);
        PageModel pageModel=childrensDrugsService.detail(dto);
        dto.setPageModel(pageModel);
    }

}
