package com.dhcc.piccbid.dto.genderLimit;

import java.util.List;
import com.dhcc.piccbid.entity.genderLimit.GenderLimit;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.genderLimit.GenderLimitVo;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2020-01-03 10:32:21
 * @version V1.0
 */
public class GenderLimitDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private GenderLimit genderLimit;
	private List<GenderLimit> genderLimits;
	private GenderLimitVo genderLimitVo;
	private List<GenderLimitVo> genderLimitVos;

	/**
	 * 结算日期
	 */
	private String balanceDate;
	/**
	 * 入院日期
	 */
	private String admissionDate;
	/**
	 * 出院日期
	 */
	private String dischargeDate;

	/**
	 * 导出参数
	 */
	private String param;

	/**
	 * 查询方式 违规/全部
	 */
	private String illegal;

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
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

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getIllegal() {
		return illegal;
	}

	public void setIllegal(String illegal) {
		this.illegal = illegal;
	}

	public GenderLimit getGenderLimit() {
		return genderLimit;
	}

	public GenderLimitVo getGenderLimitVo() {
		return genderLimitVo;
	}

	public void setGenderLimitVo(GenderLimitVo genderLimitVo) {
		this.genderLimitVo = genderLimitVo;
	}

	public List<GenderLimitVo> getGenderLimitVos() {
		return genderLimitVos;
	}

	public void setGenderLimitVos(List<GenderLimitVo> genderLimitVos) {
		this.genderLimitVos = genderLimitVos;
	}

	public void setGenderLimit(GenderLimit genderLimit) {
		this.genderLimit = genderLimit;
	}

	public List<GenderLimit> getGenderLimits() {
		return genderLimits;
	}

	public void setGenderLimits(List<GenderLimit> genderLimits) {
		this.genderLimits = genderLimits;
	}
}
