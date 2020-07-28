package com.dhcc.piccbid.dto.medicalAnalysis;

import java.math.BigDecimal;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

//import com.dhcc.piccbid.entity.medical.CountDataVo;
import com.dhcc.piccbid.entity.medical.Medical;
//import com.dhcc.piccbid.entity.medical.MedicalNumVo;
import com.dhcc.piccbid.entity.medical.MedicalVo;
//import com.dhcc.piccbid.entity.medical.MedicalVoJpp;
import com.dhcc.piccbid.entity.medicalAnalysis.MedicalAnalysis;
//import com.dhcc.piccbid.entity.medicalcost.MedicalCostVerify;
import com.dhcc.piccbid.entity.medicaldetail.MedicalDetail;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-08-08 17:39:45
 * @version V1.0
 */
public class MedicalAnalysisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private MedicalAnalysis medicalAnalysis;
	private List<MedicalAnalysis> medicalAnalysiss;
	

	private Medical medical;
	private MedicalVo medicalVo;
	private List<Medical> medicals;
	//private MedicalNumVo medicalNumVo;
	//private List<MedicalNumVo> medicalNumVos;
	private String inFlag;
	private String inFlag1;
	private String inFlag2;
	private String class1;
	private String level;
	private String illName;
	private String findOrgName;
	private MedicalDetail medicalDetail;
	private List<MedicalDetail> medicalDetails;
	private String itemCode;
	private BigDecimal itemCost;
	private String itemName;
	private BigDecimal itemPrice;
	private BigDecimal itemQuantity;
	private String itemStandard;
	private String tPiccbidMedicalId;
	private String drugType;
	private String type;//医院违规页面跳转
	//private CountDataVo countDataVo;
	private String orgName;
	private String orgCode;
	private String finaTime;
	private String balanceDate;
	private String inhosDate;
	private String outhosDate;
	//private MedicalCostVerify medicalCostVerify;//就诊明细费用明细审核记录表
	//private List<MedicalCostVerify> medicalCostVerifys;//就诊明细费用明细审核记录表
	//private MedicalVoJpp medicalVoJpp;//诊断明细4表封装VO类
	private String sHType;//请求来源 （‘zs’--终审）
	private Integer plsh; //批量审核计数
	private String tableName;
	private String billingNo;
	private String userStatusPl;//批量明细状态
	private String  MedicalDetailPl;//批量明细提交明细Id数组
	private String medicalIdPl;//批量明细提交medicalId
	private String getYear;
	private String handdingName;
	

	

	

	

	/**
	 * @return the handdingName
	 */
	public String getHanddingName() {
		return handdingName;
	}

	/**
	 * @param handdingName the handdingName to set
	 */
	public void setHanddingName(String handdingName) {
		this.handdingName = handdingName;
	}

	/*public List<MedicalCostVerify> getMedicalCostVerifys() {
		return medicalCostVerifys;
	}

	public void setMedicalCostVerifys(List<MedicalCostVerify> medicalCostVerifys) {
		this.medicalCostVerifys = medicalCostVerifys;
	}*/

	public String getMedicalDetailPl() {
		return MedicalDetailPl;
	}

	public void setMedicalDetailPl(String medicalDetailPl) {
		MedicalDetailPl = medicalDetailPl;
	}

	public String getMedicalIdPl() {
		return medicalIdPl;
	}

	public void setMedicalIdPl(String medicalIdPl) {
		this.medicalIdPl = medicalIdPl;
	}

	public String getUserStatusPl() {
		return userStatusPl;
	}

	public void setUserStatusPl(String userStatusPl) {
		this.userStatusPl = userStatusPl;
	}

	/**
	 * @return the billingNo
	 */
	public String getBillingNo() {
		return billingNo;
	}

	/**
	 * @param billingNo the billingNo to set
	 */
	public void setBillingNo(String billingNo) {
		this.billingNo = billingNo;
	}

	public Integer getPlsh() {
		return plsh;
	}

	public void setPlsh(Integer plsh) {
		this.plsh = plsh;
	}

	public String getsHType() {
		return sHType;
	}

	public void setsHType(String sHType) {
		this.sHType = sHType;
	}

	public String getBalanceDate() {
		return balanceDate;
	}

	public void setBalanceDate(String balanceDate) {
		this.balanceDate = balanceDate;
	}

	public String getInhosDate() {
		return inhosDate;
	}

	public void setInhosDate(String inhosDate) {
		this.inhosDate = inhosDate;
	}

	public String getOuthosDate() {
		return outhosDate;
	}

	public void setOuthosDate(String outhosDate) {
		this.outhosDate = outhosDate;
	}

	/*public MedicalVoJpp getMedicalVoJpp() {
		return medicalVoJpp;
	}

	public void setMedicalVoJpp(MedicalVoJpp medicalVoJpp) {
		this.medicalVoJpp = medicalVoJpp;
	}

	public MedicalCostVerify getMedicalCostVerify() {
		return medicalCostVerify;
	}

	public void setMedicalCostVerify(MedicalCostVerify medicalCostVerify) {
		this.medicalCostVerify = medicalCostVerify;
	}

	public String getFinaTime() {
		return finaTime;
	}

	public void setFinaTime(String finaTime) {
		this.finaTime = finaTime;
	}*/

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/*public CountDataVo getCountDataVo() {
		return countDataVo;
	}

	public void setCountDataVo(CountDataVo countDataVo) {
		this.countDataVo = countDataVo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}*/

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public BigDecimal getItemCost() {
		return itemCost;
	}

	public void setItemCost(BigDecimal itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public BigDecimal getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(BigDecimal itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemStandard() {
		return itemStandard;
	}

	public void setItemStandard(String itemStandard) {
		this.itemStandard = itemStandard;
	}

	public String gettPiccbidMedicalId() {
		return tPiccbidMedicalId;
	}

	public void settPiccbidMedicalId(String tPiccbidMedicalId) {
		this.tPiccbidMedicalId = tPiccbidMedicalId;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public MedicalDetail getMedicalDetail() {
		return medicalDetail;
	}

	public void setMedicalDetail(MedicalDetail medicalDetail) {
		this.medicalDetail = medicalDetail;
	}

	public List<MedicalDetail> getMedicalDetails() {
		return medicalDetails;
	}

	public void setMedicalDetails(List<MedicalDetail> medicalDetails) {
		this.medicalDetails = medicalDetails;
	}

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public Medical getMedical() {
		return medical;
	}

	public void setMedical(Medical medical) {
		this.medical = medical;
	}

	public List<Medical> getMedicals() {
		return medicals;
	}

	public void setMedicals(List<Medical> medicals) {
		this.medicals = medicals;
	}

	/*public MedicalNumVo getMedicalNumVo() {
		return medicalNumVo;
	}

	public void setMedicalNumVo(MedicalNumVo medicalNumVo) {
		this.medicalNumVo = medicalNumVo;
	}

	public List<MedicalNumVo> getMedicalNumVos() {
		return medicalNumVos;
	}*/

	/*public void setMedicalNumVos(List<MedicalNumVo> medicalNumVos) {
		this.medicalNumVos = medicalNumVos;
	}*/

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public MedicalVo getMedicalVo() {
		return medicalVo;
	}

	public void setMedicalVo(MedicalVo medicalVo) {
		this.medicalVo = medicalVo;
	}
	
	

	public MedicalAnalysis getMedicalAnalysis() {
		return medicalAnalysis;
	}

	public void setMedicalAnalysis(MedicalAnalysis medicalAnalysis) {
		this.medicalAnalysis = medicalAnalysis;
	}

	public List<MedicalAnalysis> getMedicalAnalysiss() {
		return medicalAnalysiss;
	}

	public void setMedicalAnalysiss(List<MedicalAnalysis> medicalAnalysiss) {
		this.medicalAnalysiss = medicalAnalysiss;
	}

	/**  
	* @return inFlag2 
	*/
	public String getInFlag2() {
		return inFlag2;
	}

	/**  
	* @param inFlag2 inFlag2 
	*/
	public void setInFlag2(String inFlag2) {
		this.inFlag2 = inFlag2;
	}

	/**  
	* @return inFlag1 
	*/
	public String getInFlag1() {
		return inFlag1;
	}

	/**  
	* @param inFlag1 inFlag1 
	*/
	public void setInFlag1(String inFlag1) {
		this.inFlag1 = inFlag1;
	}

	/**  
	* @return findOrgName 
	*/
	public String getFindOrgName() {
		return findOrgName;
	}

	/**  
	* @param findOrgName findOrgName 
	*/
	public void setFindOrgName(String findOrgName) {
		this.findOrgName = findOrgName;
	}

	/**  
	* @return getYear 
	*/
	public String getGetYear() {
		return getYear;
	}

	/**  
	* @param getYear getYear 
	*/
	public void setGetYear(String getYear) {
		this.getYear = getYear;
	}

	/**  
	* @return level 
	*/
	public String getLevel() {
		return level;
	}

	/**  
	* @param level level 
	*/
	public void setLevel(String level) {
		this.level = level;
	}

	/**  
	* @return class1 
	*/
	public String getClass1() {
		return class1;
	}

	/**  
	* @param class1 class1 
	*/
	public void setClass1(String class1) {
		this.class1 = class1;
	}

	/**  
	* @return illName 
	*/
	public String getIllName() {
		return illName;
	}

	/**  
	* @param illName illName 
	*/
	public void setIllName(String illName) {
		this.illName = illName;
	}

	/** 
	* 方法名:          getClass1
	* 方法功能描述:    获取汉字拼音首字母的字符串，生成健康档案信息
	* @param:         是包含汉字的字符串
	* @return:        其他非简体汉字返回 '0';
	* @Author:        姚凯
	* @Create Date:   2019年8月26日 下午5:16:01
	*/
}
