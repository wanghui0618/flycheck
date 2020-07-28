package com.dhcc.piccbid.dto.decompositionHospital;


import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * <p>标题: DecompositionHospitalDto.java</p>
 * <p>业务描述:分解住院DTO传输层</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 王彤
 * @date 2019年11月23日
 * @version V1.0
 */
public class DecompositionHospitalDto extends BaseAbstractDto {
    private static final long serialVersionUID = 1L;

    private String route;//就诊途径
    private String insurance;//险种类型
    private String hospitalCode;//医院编码
    private String hospitalName;//医院名称
    private String settlementTime;//结算时间
    private String settlementTimea;//结算时间前
    private String settlementTimeb;//结算时间后
    private String departmentName;//科室名称
    private String decomposedHospitalStay;//分解住院天数
    private String day;//维度
    private String zyh;//住院号
    private String social_card_id;//社保ID
    private String fileName; //下载文件名字

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSocial_card_id() {
        return social_card_id;
    }

    public void setSocial_card_id(String social_card_id) {
        this.social_card_id = social_card_id;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    // 当前页数
    int page;
    // 当前页个数
    int limit;

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
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

    public String getSettlementTimea() {
        return settlementTimea;
    }

    public void setSettlementTimea(String settlementTimea) {
        this.settlementTimea = settlementTimea;
    }

    public String getSettlementTimeb() {
        return settlementTimeb;
    }

    public void setSettlementTimeb(String settlementTimeb) {
        this.settlementTimeb = settlementTimeb;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDecomposedHospitalStay() {
        return decomposedHospitalStay;
    }

    public void setDecomposedHospitalStay(String decomposedHospitalStay) {
        this.decomposedHospitalStay = decomposedHospitalStay;
    }

    public String getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(String settlementTime) {
        this.settlementTime = settlementTime;
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

    @Override
    public String toString() {
        return "DecompositionHospitalDto{" +
                "route='" + route + '\'' +
                ", hospitalCode='" + hospitalCode + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", settlementTime='" + settlementTime + '\'' +
                ", settlementTimea='" + settlementTimea + '\'' +
                ", settlementTimeb='" + settlementTimeb + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", decomposedHospitalStay='" + decomposedHospitalStay + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
