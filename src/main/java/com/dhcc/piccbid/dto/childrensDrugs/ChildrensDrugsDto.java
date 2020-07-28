package com.dhcc.piccbid.dto.childrensDrugs;

import java.util.List;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugs;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.childrensDrugs.ChildrensDrugsVo;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wmy
 * @date 2019-11-23 13:32:56
 * @version V1.0
 */
public class ChildrensDrugsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private ChildrensDrugs childrensDrugs;
	private List<ChildrensDrugs> childrensDrugss;
	private ChildrensDrugsVo childrensDrugsVo;
	private List<ChildrensDrugsVo> ChildrensDrugsVos;


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
	 * 年龄符号
	 */
	private String symbol;

	/**
	 * 导出参数
	 */
	private String param;

	/**
	 * 查询方式 违规/全部
	 */
	private String illegal;

	public String getIllegal() {
		return illegal;
	}

	public void setIllegal(String illegal) {
		this.illegal = illegal;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

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

	public ChildrensDrugs getChildrensDrugs() {
		return childrensDrugs;
	}

	public void setChildrensDrugs(ChildrensDrugs childrensDrugs) {
		this.childrensDrugs = childrensDrugs;
	}

	public List<ChildrensDrugs> getChildrensDrugss() {
		return childrensDrugss;
	}

	public void setChildrensDrugss(List<ChildrensDrugs> childrensDrugss) {
		this.childrensDrugss = childrensDrugss;
	}

	public ChildrensDrugsVo getChildrensDrugsVo() {
		return childrensDrugsVo;
	}

	public void setChildrensDrugsVo(ChildrensDrugsVo childrensDrugsVo) {
		this.childrensDrugsVo = childrensDrugsVo;
	}

	public List<ChildrensDrugsVo> getChildrensDrugsVos() {
		return ChildrensDrugsVos;
	}

	public void setChildrensDrugsVos(List<ChildrensDrugsVo> childrensDrugsVos) {
		ChildrensDrugsVos = childrensDrugsVos;
	}
}
