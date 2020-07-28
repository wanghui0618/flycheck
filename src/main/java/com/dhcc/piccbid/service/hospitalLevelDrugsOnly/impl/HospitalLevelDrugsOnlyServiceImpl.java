package com.dhcc.piccbid.service.hospitalLevelDrugsOnly.impl;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dao.hospitalLevelDrugsOnly.HospitalLevelDrugsOnlyDao;
import com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto.HospitalLevelDrugsOnlyDto;
import com.dhcc.piccbid.entity.hospitalLevelDrugsOnly.HospitalLevelDrugsOnly;
import com.dhcc.piccbid.service.hospitalLevelDrugsOnly.HospitalLevelDrugsOnlyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: HospitalLevelDrugsOnlyServiceImpl.java</p>
 * <p>业务描述:限医院等级用药接口实现类</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
@Service("hospitalLevelDrugsOnlyService")
public class HospitalLevelDrugsOnlyServiceImpl implements HospitalLevelDrugsOnlyService {

    @Resource
    private HospitalLevelDrugsOnlyDao hospitalLevelDrugsOnlyDao;

    @Override
    public PageModel getHospitalLevelDrugsOnly(HospitalLevelDrugsOnlyDto dto) {
        getDate(dto);
        String typeOfTreatment=dto.getTypeOfTreatment();
        if("BeHospitalized".equals(typeOfTreatment)){
            return hospitalLevelDrugsOnlyDao.getHospitalLevelDrugsOnly(dto);
        }else if("Outpatient".equals(typeOfTreatment)){
            return hospitalLevelDrugsOnlyDao.getOutpatientHospitalLevelDrugsOnly(dto);
        }
        return hospitalLevelDrugsOnlyDao.getHospitalLevelDrugsOnly(dto);
    }

    @Override
    public List<Map<String, Object>> getCountAndTotle(HospitalLevelDrugsOnlyDto dto) {
        getDate(dto);
        String typeOfTreatment=dto.getTypeOfTreatment();
        if("BeHospitalized".equals(typeOfTreatment)){
            return hospitalLevelDrugsOnlyDao.getCountAndTotle(dto);
        }else if("Outpatient".equals(typeOfTreatment)){
            return hospitalLevelDrugsOnlyDao.getOutpatientCountAndTotle(dto);
        }
        return hospitalLevelDrugsOnlyDao.getCountAndTotle(dto);
    }

    @Override
    public List<HospitalLevelDrugsOnly> exportOutpatient(HospitalLevelDrugsOnlyDto dto) {
        getDate(dto);
        return hospitalLevelDrugsOnlyDao.exportOutpatient(dto);
    }

    @Override
    public List<HospitalLevelDrugsOnly> exportBeHospitalized(HospitalLevelDrugsOnlyDto dto) {
        getDate(dto);
        return hospitalLevelDrugsOnlyDao.exportBeHospitalized(dto);
    }

    public void getDate(HospitalLevelDrugsOnlyDto dto){
        String SettlementDate= dto.getSettlementDate();
        String AdmissionDate= dto.getAdmissionDate();
        String DischargeDate= dto.getDischargeDate();
        if(!StringUtils.isNullOrEmpty(SettlementDate) && SettlementDate != null && SettlementDate != ""){
            String[] SettlementDateArray = SettlementDate.split("forbid");
            for (int i=0;i<SettlementDateArray.length-1;i++){
                dto.setSettlementDatea(SettlementDateArray[i]);
                dto.setSettlementDateb(SettlementDateArray[i+1]);
            }
            dto.setSettlementDatea(dto.getSettlementDatea().trim());
            dto.setSettlementDateb(dto.getSettlementDateb().trim());
        }
        if(!StringUtils.isNullOrEmpty(AdmissionDate) && AdmissionDate != null && AdmissionDate != ""){
            String[] AdmissionDateArray = AdmissionDate.split("forbid");
            for (int i=0;i<AdmissionDateArray.length-1;i++){
                dto.setAdmissionDatea(AdmissionDateArray[i]);
                dto.setAdmissionDateb(AdmissionDateArray[i+1]);
            }
            dto.setAdmissionDatea(dto.getAdmissionDatea().trim());
            dto.setAdmissionDateb(dto.getAdmissionDateb().trim());
        }
        if(!StringUtils.isNullOrEmpty(DischargeDate) && DischargeDate != null && DischargeDate != ""){
            String[] DischargeDateArray = DischargeDate.split("forbid");
            for (int i=0;i<DischargeDateArray.length-1;i++){
                dto.setDischargeDatea(DischargeDateArray[i]);
                dto.setDischargeDateb(DischargeDateArray[i+1]);
            }
            dto.setDischargeDatea(dto.getDischargeDatea().trim());
            dto.setDischargeDateb(dto.getDischargeDateb().trim());
        }
    }
}
