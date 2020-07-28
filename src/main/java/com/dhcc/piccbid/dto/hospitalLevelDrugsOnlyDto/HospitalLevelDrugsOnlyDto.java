package com.dhcc.piccbid.dto.hospitalLevelDrugsOnlyDto;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * <p>标题: HospitalLevelDrugsOnlyDto.java</p>
 * <p>业务描述:限医院等级用药DTO传输层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2020</p>
 * @author 王彤
 * @date 2020年1月2日
 * @version V1.0
 */
public class HospitalLevelDrugsOnlyDto extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;

    private String fileName; //下载文件名字
    private String hospitalCode;//医院编码
    private String hospitalName;//医院名称
    private String SettlementDate;//结算时间
    private String TypeOfTreatment;//就医类型
    private String DiagnosticCode;//诊断编码
    private String DiagnosticName;//诊断名称
    private String AdmissionDate;//入院日期
    private String DischargeDate;//出院日期
    private String itemId;//医保项目编码
    private String itemName;//医保项目名称
    private String itemIdHos;//医院项目编码
    private String itemNameHos;//医院项目名称
    private String SettlementDatea;//结算时间a
    private String SettlementDateb;//结算时间b
    private String AdmissionDatea;//入院日期a
    private String AdmissionDateb;//入院日期b
    private String DischargeDatea;//出院日期a
    private String DischargeDateb;//出院日期a


    int page;// 当前页数
    int limit;// 当前页个数

    public String getSettlementDatea() {
        return SettlementDatea;
    }

    public void setSettlementDatea(String settlementDatea) {
        SettlementDatea = settlementDatea;
    }

    public String getSettlementDateb() {
        return SettlementDateb;
    }

    public void setSettlementDateb(String settlementDateb) {
        SettlementDateb = settlementDateb;
    }

    public String getAdmissionDatea() {
        return AdmissionDatea;
    }

    public void setAdmissionDatea(String admissionDatea) {
        AdmissionDatea = admissionDatea;
    }

    public String getAdmissionDateb() {
        return AdmissionDateb;
    }

    public void setAdmissionDateb(String admissionDateb) {
        AdmissionDateb = admissionDateb;
    }

    public String getDischargeDatea() {
        return DischargeDatea;
    }

    public void setDischargeDatea(String dischargeDatea) {
        DischargeDatea = dischargeDatea;
    }

    public String getDischargeDateb() {
        return DischargeDateb;
    }

    public void setDischargeDateb(String dischargeDateb) {
        DischargeDateb = dischargeDateb;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSettlementDate() {
        return SettlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        SettlementDate = settlementDate;
    }

    public String getTypeOfTreatment() {
        return TypeOfTreatment;
    }

    public void setTypeOfTreatment(String typeOfTreatment) {
        TypeOfTreatment = typeOfTreatment;
    }

    public String getDiagnosticCode() {
        return DiagnosticCode;
    }

    public void setDiagnosticCode(String diagnosticCode) {
        DiagnosticCode = diagnosticCode;
    }

    public String getDiagnosticName() {
        return DiagnosticName;
    }

    public void setDiagnosticName(String diagnosticName) {
        DiagnosticName = diagnosticName;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        AdmissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return DischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        DischargeDate = dischargeDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIdHos() {
        return itemIdHos;
    }

    public void setItemIdHos(String itemIdHos) {
        this.itemIdHos = itemIdHos;
    }

    public String getItemNameHos() {
        return itemNameHos;
    }

    public void setItemNameHos(String itemNameHos) {
        this.itemNameHos = itemNameHos;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
