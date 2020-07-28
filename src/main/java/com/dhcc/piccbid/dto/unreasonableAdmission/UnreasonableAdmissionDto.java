package com.dhcc.piccbid.dto.unreasonableAdmission;

import java.util.List;

import com.dhcc.piccbid.entity.unreasonableAdmission.CaseInfo;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

import javax.persistence.Column;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zhouwei
 * @date 2019-11-23 15:20:20
 * @version V1.0
 */
public class UnreasonableAdmissionDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private UnreasonableAdmission unreasonableAdmission;
	private List<UnreasonableAdmission> unreasonableAdmissions;
	private CaseInfo caseInfo;
	private List<CaseInfo> caseInfos;
	private String param;
	private String dictName;
	private String Keyword;
	private int pages;
	private int rows;
	private String params;

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getKeyword() {
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
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

	@Override
	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public CaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

	public List<CaseInfo> getCaseInfos() {
		return caseInfos;
	}

	public void setCaseInfos(List<CaseInfo> caseInfos) {
		this.caseInfos = caseInfos;
	}

	public UnreasonableAdmission getUnreasonableAdmission() {
		return unreasonableAdmission;
	}

	public void setUnreasonableAdmission(UnreasonableAdmission unreasonableAdmission) {
		this.unreasonableAdmission = unreasonableAdmission;
	}

	public List<UnreasonableAdmission> getUnreasonableAdmissions() {
		return unreasonableAdmissions;
	}

	public void setUnreasonableAdmissions(List<UnreasonableAdmission> unreasonableAdmissions) {
		this.unreasonableAdmissions = unreasonableAdmissions;
	}
}
