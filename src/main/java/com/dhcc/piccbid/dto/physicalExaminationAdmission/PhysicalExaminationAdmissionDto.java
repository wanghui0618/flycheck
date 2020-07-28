package com.dhcc.piccbid.dto.physicalExaminationAdmission;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmission;
import com.dhcc.piccbid.entity.physicalExaminationAdmission.PhysicalExaminationAdmissionVo;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author songchenyang
 * @date 2019-11-23 10:17:39
 * @version V1.0
 */
public class PhysicalExaminationAdmissionDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private PhysicalExaminationAdmission physicalExaminationAdmission;
	private List<PhysicalExaminationAdmission> physicalExaminationAdmissions;
	private PhysicalExaminationAdmissionVo physicalExaminationAdmissionVo;
	private List<PhysicalExaminationAdmissionVo> physicalExaminationAdmissionVos;
	private String hospitalId;// 医院编码
	private String hospitalName;// 医院名称
	private String billDate;// 结算日期
	private String code;// 大于/小于/等于
	private String code1;// 大于/小于/等于
	private String Sumdrugs;// 药品费
	private String jianchafei;// 检查费占比
	private String admissionDate;// 入院日期
	private String dischargeDate;// 出院日期
	private String admissionDiseaseId;// 诊断编码
	private String admissionDiseaseName;// 诊断名称
	private String baifenhao;
	private String dictName;
	private String param;
	
	
	/**
	 * @return the physicalExaminationAdmissionVo
	 */
	public PhysicalExaminationAdmissionVo getPhysicalExaminationAdmissionVo() {
		return physicalExaminationAdmissionVo;
	}

	/**
	 * @param physicalExaminationAdmissionVo the physicalExaminationAdmissionVo to set
	 */
	public void setPhysicalExaminationAdmissionVo(PhysicalExaminationAdmissionVo physicalExaminationAdmissionVo) {
		this.physicalExaminationAdmissionVo = physicalExaminationAdmissionVo;
	}

	/**
	 * @return the physicalExaminationAdmissionVos
	 */
	public List<PhysicalExaminationAdmissionVo> getPhysicalExaminationAdmissionVos() {
		return physicalExaminationAdmissionVos;
	}

	/**
	 * @param physicalExaminationAdmissionVos the physicalExaminationAdmissionVos to set
	 */
	public void setPhysicalExaminationAdmissionVos(List<PhysicalExaminationAdmissionVo> physicalExaminationAdmissionVos) {
		this.physicalExaminationAdmissionVos = physicalExaminationAdmissionVos;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getBaifenhao() {
		return baifenhao;
	}

	public void setBaifenhao(String baifenhao) {
		this.baifenhao = baifenhao;
	}

	public String getCode1() {
		return code1;
	}

	public void setCode1(String code1) {
		this.code1 = code1;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSumdrugs() {
		return Sumdrugs;
	}

	public void setSumdrugs(String sumdrugs) {
		Sumdrugs = sumdrugs;
	}

	public String getJianchafei() {
		return jianchafei;
	}

	public void setJianchafei(String jianchafei) {
		this.jianchafei = jianchafei;
	}

	public String getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public String getAdmissionDiseaseId() {
		return admissionDiseaseId;
	}

	public void setAdmissionDiseaseId(String admissionDiseaseId) {
		this.admissionDiseaseId = admissionDiseaseId;
	}

	public String getAdmissionDiseaseName() {
		return admissionDiseaseName;
	}

	public void setAdmissionDiseaseName(String admissionDiseaseName) {
		this.admissionDiseaseName = admissionDiseaseName;
	}

	public PhysicalExaminationAdmission getPhysicalExaminationAdmission() {
		return physicalExaminationAdmission;
	}

	public void setPhysicalExaminationAdmission(PhysicalExaminationAdmission physicalExaminationAdmission) {
		this.physicalExaminationAdmission = physicalExaminationAdmission;
	}

	public List<PhysicalExaminationAdmission> getPhysicalExaminationAdmissions() {
		return physicalExaminationAdmissions;
	}

	public void setPhysicalExaminationAdmissions(List<PhysicalExaminationAdmission> physicalExaminationAdmissions) {
		this.physicalExaminationAdmissions = physicalExaminationAdmissions;
	}
}
