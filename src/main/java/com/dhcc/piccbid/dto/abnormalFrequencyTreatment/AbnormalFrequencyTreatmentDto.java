package com.dhcc.piccbid.dto.abnormalFrequencyTreatment;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * <p>标题: AbnormalFrequencyTreatmentDto.java</p>
 * <p>业务描述:就诊次数异常</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2019</p>
 * @author 贺和平
 * @date 2019年11月23日
 * @version V1.0
 */

public class AbnormalFrequencyTreatmentDto extends BaseAbstractDto{
	
	private static final long serialVersionUID = 1L;

    private String route;//就诊途径
    private String insurance;//险种类型
    private String hospitalCode;//医院编码
    private String hospitalName;//医院名称
    private String settlementTime;//结算时间
    private String departmentName;//科室名称
    private String count;//就诊次数
    private String day;//维度
    private String patientId;//患者编号
    private String social_card_id;//社保ID
    private String fileName; //下载文件名字
    int page;  // 当前页数
    int limit; // 当前页个数
    private String hisid;
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
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
	public String getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(String settlementTime) {
		this.settlementTime = settlementTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getSocial_card_id() {
		return social_card_id;
	}
	public void setSocial_card_id(String social_card_id) {
		this.social_card_id = social_card_id;
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
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getHisid() {
		return hisid;
	}
	public void setHisid(String hisid) {
		this.hisid = hisid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AbnormalFrequencyTreatmentDto [route=" + route + ", insurance=" + insurance + ", hospitalCode="
				+ hospitalCode + ", hospitalName=" + hospitalName + ", settlementTime=" + settlementTime
				+ ", departmentName=" + departmentName + ", count=" + count + ", day=" + day + ", patientId="
				+ patientId + ", social_card_id=" + social_card_id + ", fileName=" + fileName + ", hisid=" + hisid
				+ "]";
	}
	
    
	
    
    
}
