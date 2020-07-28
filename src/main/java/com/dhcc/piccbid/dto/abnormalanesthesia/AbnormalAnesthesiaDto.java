package com.dhcc.piccbid.dto.abnormalanesthesia;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;

public class AbnormalAnesthesiaDto extends BaseAbstractDto{
	private static final long serialVersionUID = 1L;

    private String hospitalCode;//医院编码
    private String hospitalName;//医院名称
    private String admissiondate;//住院日期
    private String dischargedate;//出院日期
    private String hisid;
    private String fileName; //下载文件名字
    int page;  // 当前页数
    int limit; // 当前页个数
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
	public String getHisid() {
		return hisid;
	}
	public void setHisid(String hisid) {
		this.hisid = hisid;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SametimeHospitalDto [hospitalCode=" + hospitalCode + ", hospitalName=" + hospitalName
				+ ", admissiondate=" + admissiondate + ", dischargedate=" + dischargedate + ", hisid=" + hisid
				+ ", fileName=" + fileName + ", page=" + page + ", limit=" + limit + "]";
	}
    
    
    

}
