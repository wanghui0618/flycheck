package com.dhcc.piccbid.dto.flyTreatmentFeeCount;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.DetailedInformationVo;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyMedical;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.PersonalInformationInquiryVo;

import java.util.List;

public class PersonalInformationInquiryDto  extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;
    private PersonalInformationInquiryVo personalInformationInquiryVo;
    private List<PersonalInformationInquiryVo> personalInformationInquiryVoList;

    private DetailedInformationVo detailedInformationVo;
    private List<DetailedInformationVo> detailedInformationVoList;

    private FlyMedical flyMedical;
    private List<FlyMedical> flyMedicalList;

    public PersonalInformationInquiryVo getPersonalInformationInquiryVo() {
        return personalInformationInquiryVo;
    }

    public void setPersonalInformationInquiryVo(PersonalInformationInquiryVo personalInformationInquiryVo) {
        this.personalInformationInquiryVo = personalInformationInquiryVo;
    }

    public List<PersonalInformationInquiryVo> getPersonalInformationInquiryVoList() {
        return personalInformationInquiryVoList;
    }

    public void setPersonalInformationInquiryVoList(List<PersonalInformationInquiryVo> personalInformationInquiryVoList) {
        this.personalInformationInquiryVoList = personalInformationInquiryVoList;
    }

    public DetailedInformationVo getDetailedInformationVo() {
        return detailedInformationVo;
    }

    public void setDetailedInformationVo(DetailedInformationVo detailedInformationVo) {
        this.detailedInformationVo = detailedInformationVo;
    }

    public List<DetailedInformationVo> getDetailedInformationVoList() {
        return detailedInformationVoList;
    }

    public void setDetailedInformationVoList(List<DetailedInformationVo> detailedInformationVoList) {
        this.detailedInformationVoList = detailedInformationVoList;
    }

    public FlyMedical getFlyMedical() {
        return flyMedical;
    }

    public void setFlyMedical(FlyMedical flyMedical) {
        this.flyMedical = flyMedical;
    }

    public List<FlyMedical> getFlyMedicalList() {
        return flyMedicalList;
    }

    public void setFlyMedicalList(List<FlyMedical> flyMedicalList) {
        this.flyMedicalList = flyMedicalList;
    }
}
