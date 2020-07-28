package com.dhcc.piccbid.service.decompositionHospital.impl;

import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.utils.StringUtils;
import com.dhcc.piccbid.dao.decompositionHospital.DecompositionHospitalDao;
import com.dhcc.piccbid.dto.decompositionHospital.DecompositionHospitalDto;
import com.dhcc.piccbid.entity.decompositionHospital.DecompositionHospital;
import com.dhcc.piccbid.service.decompositionHospital.DecompositionHospitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>标题: DecompositionHospitalServiceImpl.java</p>
 * <p>业务描述:分解住院接口实现类</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
@Service("decompositionHospitalService")
public class DecompositionHospitalServiceImpl implements DecompositionHospitalService {

    @Resource
    private DecompositionHospitalDao decompositionHospitalDao;

    @Override
    public PageModel getDecompositionHospital(DecompositionHospitalDto dto) {
        getDate(dto);
        return decompositionHospitalDao.getDecompositionHospital(dto);
    }

    @Override
    public PageModel getDecompositionHospitalDetails(DecompositionHospitalDto dto) {
        return decompositionHospitalDao.getDecompositionHospitalDetails(dto);
    }

    @Override
    public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(DecompositionHospitalDto dto) {
        getDate(dto);
        return decompositionHospitalDao.getTotalNumberOfCasesAndTotalAmount(dto);
    }

    @Override
    public PageModel getInsuredDataForm(DecompositionHospitalDto dto) {
        getDate(dto);
        return decompositionHospitalDao.getInsuredDataForm(dto);
    }

    @Override
    public PageModel getDetailsHospitalTable(DecompositionHospitalDto dto) {
        return decompositionHospitalDao.getDetailsHospitalTable(dto);
    }

    @Override
    public List<DecompositionHospital> exportD(DecompositionHospitalDto dto) {
        getDate(dto);
        return decompositionHospitalDao.exportD(dto);
    }

    @Override
    public List<DecompositionHospital> exportA(DecompositionHospitalDto dto) {
        return decompositionHospitalDao.exportA(dto);
    }


    public void getDate(DecompositionHospitalDto dto){
        String settlementTime= dto.getSettlementTime();
        if(!StringUtils.isNullOrEmpty(settlementTime) && settlementTime != null && settlementTime != ""){
            String[] SettlementTimeArray = settlementTime.split("forbid");
            for (int i=0;i<SettlementTimeArray.length-1;i++){
                dto.setSettlementTimea(SettlementTimeArray[i]);
                dto.setSettlementTimeb(SettlementTimeArray[i+1]);
            }
            dto.setSettlementTimea(dto.getSettlementTimea().trim());
            dto.setSettlementTimeb(dto.getSettlementTimeb().trim());
        }

    }
}
