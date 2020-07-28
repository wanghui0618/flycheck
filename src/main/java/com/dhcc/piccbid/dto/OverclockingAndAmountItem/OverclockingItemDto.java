package com.dhcc.piccbid.dto.OverclockingAndAmountItem;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.OverclockingAndAmountItem.OverclockingItemVo;
import com.dhcc.piccbid.entity.flycheckMedicalDetail.FlycheckMedicalDetail;

import java.util.List;

public class OverclockingItemDto  extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;
    private OverclockingItemVo overclockingItemVo;
    private List<OverclockingItemVo> overclockingItemVoList;
    private FlycheckMedicalDetail flycheckMedicalDetail;
    private List<FlycheckMedicalDetail> flycheckMedicalDetailList;

    public OverclockingItemVo getOverclockingItemVo() {
        return overclockingItemVo;
    }

    public void setOverclockingItemVo(OverclockingItemVo overclockingItemVo) {
        this.overclockingItemVo = overclockingItemVo;
    }

    public List<OverclockingItemVo> getOverclockingItemVoList() {
        return overclockingItemVoList;
    }

    public void setOverclockingItemVoList(List<OverclockingItemVo> overclockingItemVoList) {
        this.overclockingItemVoList = overclockingItemVoList;
    }

    public FlycheckMedicalDetail getFlycheckMedicalDetail() {
        return flycheckMedicalDetail;
    }

    public void setFlycheckMedicalDetail(FlycheckMedicalDetail flycheckMedicalDetail) {
        this.flycheckMedicalDetail = flycheckMedicalDetail;
    }

    public List<FlycheckMedicalDetail> getFlycheckMedicalDetailList() {
        return flycheckMedicalDetailList;
    }

    public void setFlycheckMedicalDetailList(List<FlycheckMedicalDetail> flycheckMedicalDetailList) {
        this.flycheckMedicalDetailList = flycheckMedicalDetailList;
    }
}
