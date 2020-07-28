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

public class AbnormalFrequencyTreatmentZYDto extends BaseAbstractDto{
	
	private static final long serialVersionUID = 1L;

    private String route;//就诊途径
    private String hospitalCode;//医院编码
    private String hospitalName;//医院名称
    private String admissiondate;//住院日期
    private String dischargedate;//出院日期
    private String departmentName;//科室名称
    private String dayts;//住院天数维度
    private String zyts;//住院天数
    private String day;//维度
    private String count;//住院次数
    
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
	public String getAdmissiondate() {
		return admissiondate;
	}
	public void setAdmissiondate(String admissiondate) {
		this.admissiondate = admissiondate;
	}
	public String getDischargedate() {
		return dischargedate;
	}
	public void setDischargedate(String dischargedate) {
		this.dischargedate = dischargedate;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getZyts() {
		return zyts;
	}
	public void setZyts(String zyts) {
		this.zyts = zyts;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
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
	public String getDayts() {
		return dayts;
	}
	public void setDayts(String dayts) {
		this.dayts = dayts;
	}
	@Override
	public String toString() {
		return "AbnormalFrequencyTreatmentZYDto [route=" + route + ", hospitalCode=" + hospitalCode + ", hospitalName="
				+ hospitalName + ", admissiondate=" + admissiondate + ", dischargedate=" + dischargedate
				+ ", departmentName=" + departmentName + ", dayts=" + dayts + ", zyts=" + zyts + ", day=" + day
				+ ", count=" + count + ", patientId=" + patientId + ", social_card_id=" + social_card_id + ", fileName="
				+ fileName + ", page=" + page + ", limit=" + limit + ", hisid=" + hisid + "]";
	}
	
	
    
    

	
    
	
    
    
}
