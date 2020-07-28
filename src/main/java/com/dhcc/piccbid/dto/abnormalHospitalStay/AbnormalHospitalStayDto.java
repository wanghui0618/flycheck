package com.dhcc.piccbid.dto.abnormalHospitalStay;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayVo;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayexcle;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-11-23 13:31:41
 * @version V1.0
 */
public class AbnormalHospitalStayDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private AbnormalHospitalStay abnormalHospitalStay;
	private List<AbnormalHospitalStay> abnormalHospitalStays;

	private AbnormalHospitalStayVo abnormalHospitalStayVo;
	private List<AbnormalHospitalStayVo> abnormalHospitalStayVos;

	private AbnormalHospitalStayexcle abnormalHospitalStayexcle;
	private List<AbnormalHospitalStayexcle> abnormalHospitalStayexcles;

	public AbnormalHospitalStayexcle getAbnormalHospitalStayexcle() {
		return abnormalHospitalStayexcle;
	}

	public void setAbnormalHospitalStayexcle(AbnormalHospitalStayexcle abnormalHospitalStayexcle) {
		this.abnormalHospitalStayexcle = abnormalHospitalStayexcle;
	}

	public List<AbnormalHospitalStayexcle> getAbnormalHospitalStayexcles() {
		return abnormalHospitalStayexcles;
	}

	public void setAbnormalHospitalStayexcles(List<AbnormalHospitalStayexcle> abnormalHospitalStayexcles) {
		this.abnormalHospitalStayexcles = abnormalHospitalStayexcles;
	}

	private String zyts;
	private String indate;
	private String hospitalId;
	private String hospitalName;
	private String admissionDiseaseId;
	private String admissionDiseaseName;
	private String outdate;
	private String paydate;
	private String code;
	private String dictName;
	private String Keyword;
	private int pages;
	private int rows;
	private String params;
	private String dictNames;

	public String getDictNames() {
		return dictNames;
	}

	public void setDictNames(String dictNames) {
		this.dictNames = dictNames;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public AbnormalHospitalStayVo getAbnormalHospitalStayVo() {
		return abnormalHospitalStayVo;
	}

	public void setAbnormalHospitalStayVo(AbnormalHospitalStayVo abnormalHospitalStayVo) {
		this.abnormalHospitalStayVo = abnormalHospitalStayVo;
	}

	public List<AbnormalHospitalStayVo> getAbnormalHospitalStayVos() {
		return abnormalHospitalStayVos;
	}

	public void setAbnormalHospitalStayVos(List<AbnormalHospitalStayVo> abnormalHospitalStayVos) {
		this.abnormalHospitalStayVos = abnormalHospitalStayVos;
	}

	

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getZyts() {
		return zyts;
	}

	public void setZyts(String zyts) {
		this.zyts = zyts;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
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

	public String getOutdate() {
		return outdate;
	}

	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public AbnormalHospitalStay getAbnormalHospitalStay() {
		return abnormalHospitalStay;
	}

	public void setAbnormalHospitalStay(AbnormalHospitalStay abnormalHospitalStay) {
		this.abnormalHospitalStay = abnormalHospitalStay;
	}

	public List<AbnormalHospitalStay> getAbnormalHospitalStays() {
		return abnormalHospitalStays;
	}

	public void setAbnormalHospitalStays(List<AbnormalHospitalStay> abnormalHospitalStays) {
		this.abnormalHospitalStays = abnormalHospitalStays;
	}
}
