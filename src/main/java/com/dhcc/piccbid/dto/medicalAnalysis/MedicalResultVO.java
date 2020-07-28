package com.dhcc.piccbid.dto.medicalAnalysis;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;


public class MedicalResultVO extends BaseAbstractDto {
    private String  handdingInsName;
    private String orgCode;
    private String attendingDocCode;
    private String insurePersonCode;
    private String storeNo;
    private String totalCost;
    private String selfCost;

    public String getHanddingInsName() {
        return handdingInsName;
    }

    public void setHanddingInsName(String handdingInsName) {
        this.handdingInsName = handdingInsName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAttendingDocCode() {
        return attendingDocCode;
    }

    public void setAttendingDocCode(String attendingDocCode) {
        this.attendingDocCode = attendingDocCode;
    }

    public String getInsurePersonCode() {
        return insurePersonCode;
    }

    public void setInsurePersonCode(String insurePersonCode) {
        this.insurePersonCode = insurePersonCode;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getSelfCost() {
        return selfCost;
    }

    public void setSelfCost(String selfCost) {
        this.selfCost = selfCost;
    }
}
