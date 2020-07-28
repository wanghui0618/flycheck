package com.dhcc.piccbid.dto.dataComparison;

import java.util.List;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-11-26 16:14:41
 * @version V1.0
 */
public class DataComparisonDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private DataComparison dataComparison;
	private List<DataComparison> dataComparisons;

	private String diagType;
	private String personType;
	private String orgCode1;
	private String orgCode2;
	private String balanceDate;
	private String param;
	private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getDiagType() {
		return diagType;
	}

	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getOrgCode1() {
		return orgCode1;
	}

	public void setOrgCode1(String orgCode1) {
		this.orgCode1 = orgCode1;
	}

	public String getOrgCode2() {
		return orgCode2;
	}

	public void setOrgCode2(String orgCode2) {
		this.orgCode2 = orgCode2;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String[] getBingZ() {
		return bingZ;
	}

	public void setBingZ(String[] bingZ) {
		this.bingZ = bingZ;
	}

	private String[] bingZ;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;

	public DataComparison getDataComparison() {
		return dataComparison;
	}

	public void setDataComparison(DataComparison dataComparison) {
		this.dataComparison = dataComparison;
	}

	public List<DataComparison> getDataComparisons() {
		return dataComparisons;
	}

	public void setDataComparisons(List<DataComparison> dataComparisons) {
		this.dataComparisons = dataComparisons;
	}
}
