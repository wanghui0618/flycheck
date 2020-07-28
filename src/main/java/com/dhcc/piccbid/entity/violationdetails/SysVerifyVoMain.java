package com.dhcc.piccbid.entity.violationdetails;

import java.io.Serializable;
import java.util.List;
/*
 * 系统审核，
 * 违规统计Vo--用于主病例违规
*/
public class SysVerifyVoMain implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String typeNo;    //违规编码
	private String typeNames;  //违规名称
	private String ruleProperty;//违规类别---- 0 主病例  1 明细
	private List<ViolationDetail> returnDescs; //描述--
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getTypeNames() {
		return typeNames;
	}
	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}
	public String getRuleProperty() {
		return ruleProperty;
	}
	public void setRuleProperty(String ruleProperty) {
		this.ruleProperty = ruleProperty;
	}
	public List<ViolationDetail> getReturnDescs() {
		return returnDescs;
	}
	public void setReturnDescs(List<ViolationDetail> returnDescs) {
		this.returnDescs = returnDescs;
	}
	
	
	
}
