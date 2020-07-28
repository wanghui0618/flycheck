package com.dhcc.piccbid.dto.notExists;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.DetailedInformationVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.entity.flycheckMedicalDetail.FlycheckMedicalDetail;
import com.dhcc.piccbid.entity.flycheckMedicalMz.FlycheckMedicalMz;
import com.dhcc.piccbid.entity.flycheckMedicalMzDetail.FlycheckMedicalMzDetail;
import com.dhcc.piccbid.entity.notExists.Noexists1;
import com.dhcc.piccbid.entity.notExists.NotExists;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail_MZ;
import com.dhcc.piccbid.entity.notExists.NotExsitsmzzd;
import com.dhcc.piccbid.entity.notExists.NotExsitszyzd;

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
public class NotExistsDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;
	//---------------------2019-12-04 ---jpp------------------------------------
	private FlycheckMedical flycheckMedical;
	private List<FlycheckMedical> flycheckMedicals;
	private FlycheckMedicalMz flycheckMedicalMz;
	private List<FlycheckMedicalMz> flycheckMedicalMzs;
	private FlycheckMedicalDetail flycheckMedicalDetail;
	private List<FlycheckMedicalDetail> flycheckMedicalDetails;
	private FlycheckMedicalMzDetail flycheckMedicalMzDetail;
	private List<FlycheckMedicalMzDetail> flycheckMedicalMzDetails;
	
	private String path;
	private String sxtj;//条件--大于小于等于同时存在 不同时存在
	private String xzvalue;//数量or价格的值
	private String costOrnum;//数量价格
	private String limittime;//同次或不同次
	private String itemId;//A项目编码
	private String itemName;//A项目名称
	private String itemId1;//B项目编码
	private String itemName1;//B项目名称
	private String benefitType;//险种类型
	private String hospitalName;//医疗机构名称
	private String hospitalId;//医疗机构编码
	private String billDate;//结算日期
	private String admissionDate;//入院时间
	private String dischargeDate;//出院时间
	private String dischargeDeptName;//科室名称
	private String hisid;//结算号
	private String totalAmount;//总金额
	
	//就诊方式
	private String  dialogType; 
	//医院
	private String  hospType;
	private String  hospValue;
	//科室
	private String  dischargeType;  
	private String  dischargeValue;  
	//医保项目文本框内容
	private String  bbj  ;
	//医保项目取值类型
	private String  itemType ;

	//医院项目文本框内容
	private String  bj ; 
	//医院项目取值类型
	private String  itemHospType ;     
         
	//限制范围
	private String  limite; 
	//要求
	private String  mutex;  
	
	//导出参数
	private String param;
	
	
	//导出文本框参数左
	private String mybbj;
	//导出文本框参数右
	private String mybj;
	
	
	/**
	 * @return the mybbj
	 */
	public String getMybbj() {
		return mybbj;
	}
	/**
	 * @param mybbj the mybbj to set
	 */
	public void setMybbj(String mybbj) {
		this.mybbj = mybbj;
	}
	/**
	 * @return the mybj
	 */
	public String getMybj() {
		return mybj;
	}
	/**
	 * @param mybj the mybj to set
	 */
	public void setMybj(String mybj) {
		this.mybj = mybj;
	}
	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}
	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * @return the hospValue
	 */
	public String getHospValue() {
		return hospValue;
	}
	/**
	 * @param hospValue the hospValue to set
	 */
	public void setHospValue(String hospValue) {
		this.hospValue = hospValue;
	}
	/**
	 * @return the dischargeValue
	 */
	public String getDischargeValue() {
		return dischargeValue;
	}
	/**
	 * @param dischargeValue the dischargeValue to set
	 */
	public void setDischargeValue(String dischargeValue) {
		this.dischargeValue = dischargeValue;
	}
	/**
	 * @return the itemType
	 */
	public String getItemType() {
		return itemType;
	}
	/**
	 * @param itemType the itemType to set
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the itemHospType
	 */
	public String getItemHospType() {
		return itemHospType;
	}
	/**
	 * @param itemHospType the itemHospType to set
	 */
	public void setItemHospType(String itemHospType) {
		this.itemHospType = itemHospType;
	}

	/**
	 * @return the dialogType
	 */
	public String getDialogType() {
		return dialogType;
	}
	/**
	 * @param dialogType the dialogType to set
	 */
	public void setDialogType(String dialogType) {
		this.dialogType = dialogType;
	}
	/**
	 * @return the hospType
	 */
	public String getHospType() {
		return hospType;
	}
	/**
	 * @param hospType the hospType to set
	 */
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	/**
	 * @return the dischargeType
	 */
	public String getDischargeType() {
		return dischargeType;
	}
	/**
	 * @param dischargeType the dischargeType to set
	 */
	public void setDischargeType(String dischargeType) {
		this.dischargeType = dischargeType;
	}
	/**
	 * @return the limite
	 */
	public String getLimite() {
		return limite;
	}
	/**
	 * @param limite the limite to set
	 */
	public void setLimite(String limite) {
		this.limite = limite;
	}
	/**
	 * @return the mutex
	 */
	public String getMutex() {
		return mutex;
	}
	/**
	 * @param mutex the mutex to set
	 */
	public void setMutex(String mutex) {
		this.mutex = mutex;
	}
	/**
	 * @return the bbj
	 */
	public String getBbj() {
		return bbj;
	}
	/**
	 * @param bbj the bbj to set
	 */
	public void setBbj(String bbj) {
		this.bbj = bbj;
	}
	/**
	 * @return the bj
	 */
	public String getBj() {
		return bj;
	}
	/**
	 * @param bj the bj to set
	 */
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getSxtj() {
		return sxtj;
	}
	public void setSxtj(String sxtj) {
		this.sxtj = sxtj;
	}
	public String getXzvalue() {
		return xzvalue;
	}
	public void setXzvalue(String xzvalue) {
		this.xzvalue = xzvalue;
	}
	public String getCostOrnum() {
		return costOrnum;
	}
	public void setCostOrnum(String costOrnum) {
		this.costOrnum = costOrnum;
	}
	public String getLimittime() {
		return limittime;
	}
	public void setLimittime(String limittime) {
		this.limittime = limittime;
	}
	public String getItemId1() {
		return itemId1;
	}
	public void setItemId1(String itemId1) {
		this.itemId1 = itemId1;
	}
	public String getItemName1() {
		return itemName1;
	}
	public void setItemName1(String itemName1) {
		this.itemName1 = itemName1;
	}
	public FlycheckMedical getFlycheckMedical() {
		return flycheckMedical;
	}
	public void setFlycheckMedical(FlycheckMedical flycheckMedical) {
		this.flycheckMedical = flycheckMedical;
	}
	public List<FlycheckMedical> getFlycheckMedicals() {
		return flycheckMedicals;
	}
	public void setFlycheckMedicals(List<FlycheckMedical> flycheckMedicals) {
		this.flycheckMedicals = flycheckMedicals;
	}
	public FlycheckMedicalMz getFlycheckMedicalMz() {
		return flycheckMedicalMz;
	}
	public void setFlycheckMedicalMz(FlycheckMedicalMz flycheckMedicalMz) {
		this.flycheckMedicalMz = flycheckMedicalMz;
	}
	public List<FlycheckMedicalMz> getFlycheckMedicalMzs() {
		return flycheckMedicalMzs;
	}
	public void setFlycheckMedicalMzs(List<FlycheckMedicalMz> flycheckMedicalMzs) {
		this.flycheckMedicalMzs = flycheckMedicalMzs;
	}
	public FlycheckMedicalMzDetail getFlycheckMedicalMzDetail() {
		return flycheckMedicalMzDetail;
	}
	public void setFlycheckMedicalMzDetail(FlycheckMedicalMzDetail flycheckMedicalMzDetail) {
		this.flycheckMedicalMzDetail = flycheckMedicalMzDetail;
	}
	public List<FlycheckMedicalMzDetail> getFlycheckMedicalMzDetails() {
		return flycheckMedicalMzDetails;
	}
	public void setFlycheckMedicalMzDetails(List<FlycheckMedicalMzDetail> flycheckMedicalMzDetails) {
		this.flycheckMedicalMzDetails = flycheckMedicalMzDetails;
	}
	//-------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	
	
	
	private DetailedInformationVo detailedInformationVo;
	private List<DetailedInformationVo> detailedInformationVoList;
	    
	private NotExists notExists;
	private List<NotExists> notExistss;	
	
	private Noexists1 noexists1;
	private List<Noexists1> noexists1s;	
	
	private NotExistsDetail notExistsDetail;
	private List<NotExistsDetail> notExistsDetails;
	
	private NotExistsDetail_MZ notExistsDetail_MZ;
	private List<NotExistsDetail_MZ> notExistsDetail_MZs;
	
	private NotExsitszyzd notExsitszyzd;
	private List<NotExsitszyzd> notExsitszyzds;
	
	private NotExsitsmzzd notExsitsmzzd;
	private List<NotExsitsmzzd> notExsitsmzzds;
	
	
	
	private String itemIdHosp;
	private String itemNameHosp;
	private String itemIdHosp1;
	private String itemNameHosp1;
	private String timelimit;
	
	private String preAdmissionDate;

	
	
	
	/**
	 * @return the notExsitszyzd
	 */
	public NotExsitszyzd getNotExsitszyzd() {
		return notExsitszyzd;
	}
	/**
	 * @param notExsitszyzd the notExsitszyzd to set
	 */
	public void setNotExsitszyzd(NotExsitszyzd notExsitszyzd) {
		this.notExsitszyzd = notExsitszyzd;
	}
	/**
	 * @return the notExsitszyzds
	 */
	public List<NotExsitszyzd> getNotExsitszyzds() {
		return notExsitszyzds;
	}
	/**
	 * @param notExsitszyzds the notExsitszyzds to set
	 */
	public void setNotExsitszyzds(List<NotExsitszyzd> notExsitszyzds) {
		this.notExsitszyzds = notExsitszyzds;
	}
	/**
	 * @return the notExsitsmzzd
	 */
	public NotExsitsmzzd getNotExsitsmzzd() {
		return notExsitsmzzd;
	}
	/**
	 * @param notExsitsmzzd the notExsitsmzzd to set
	 */
	public void setNotExsitsmzzd(NotExsitsmzzd notExsitsmzzd) {
		this.notExsitsmzzd = notExsitsmzzd;
	}
	/**
	 * @return the notExsitsmzzds
	 */
	public List<NotExsitsmzzd> getNotExsitsmzzds() {
		return notExsitsmzzds;
	}
	/**
	 * @param notExsitsmzzds the notExsitsmzzds to set
	 */
	public void setNotExsitsmzzds(List<NotExsitsmzzd> notExsitsmzzds) {
		this.notExsitsmzzds = notExsitsmzzds;
	}
	/**
	 * @return the notExistsDetail_MZ
	 */
	public NotExistsDetail_MZ getNotExistsDetail_MZ() {
		return notExistsDetail_MZ;
	}
	/**
	 * @param notExistsDetail_MZ the notExistsDetail_MZ to set
	 */
	public void setNotExistsDetail_MZ(NotExistsDetail_MZ notExistsDetail_MZ) {
		this.notExistsDetail_MZ = notExistsDetail_MZ;
	}
	/**
	 * @return the notExistsDetail_MZs
	 */
	public List<NotExistsDetail_MZ> getNotExistsDetail_MZs() {
		return notExistsDetail_MZs;
	}
	/**
	 * @param notExistsDetail_MZs the notExistsDetail_MZs to set
	 */
	public void setNotExistsDetail_MZs(List<NotExistsDetail_MZ> notExistsDetail_MZs) {
		this.notExistsDetail_MZs = notExistsDetail_MZs;
	}
	/**
	 * @return the notExistsDetail
	 */
	public NotExistsDetail getNotExistsDetail() {
		return notExistsDetail;
	}
	/**
	 * @param notExistsDetail the notExistsDetail to set
	 */
	public void setNotExistsDetail(NotExistsDetail notExistsDetail) {
		this.notExistsDetail = notExistsDetail;
	}
	/**
	 * @return the notExistsDetails
	 */
	public List<NotExistsDetail> getNotExistsDetails() {
		return notExistsDetails;
	}
	/**
	 * @param notExistsDetails the notExistsDetails to set
	 */
	public void setNotExistsDetails(List<NotExistsDetail> notExistsDetails) {
		this.notExistsDetails = notExistsDetails;
	}
	/**
	 * @return the detailedInformationVo
	 */
	public DetailedInformationVo getDetailedInformationVo() {
		return detailedInformationVo;
	}
	/**
	 * @param detailedInformationVo the detailedInformationVo to set
	 */
	public void setDetailedInformationVo(DetailedInformationVo detailedInformationVo) {
		this.detailedInformationVo = detailedInformationVo;
	}
	/**
	 * @return the detailedInformationVoList
	 */
	public List<DetailedInformationVo> getDetailedInformationVoList() {
		return detailedInformationVoList;
	}
	/**
	 * @param detailedInformationVoList the detailedInformationVoList to set
	 */
	public void setDetailedInformationVoList(List<DetailedInformationVo> detailedInformationVoList) {
		this.detailedInformationVoList = detailedInformationVoList;
	}
	/**
	 * @return the noexists1
	 */
	public Noexists1 getNoexists1() {
		return noexists1;
	}
	/**
	 * @param noexists1 the noexists1 to set
	 */
	public void setNoexists1(Noexists1 noexists1) {
		this.noexists1 = noexists1;
	}
	/**
	 * @return the noexists1s
	 */
	public List<Noexists1> getNoexists1s() {
		return noexists1s;
	}
	/**
	 * @param noexists1s the noexists1s to set
	 */
	public void setNoexists1s(List<Noexists1> noexists1s) {
		this.noexists1s = noexists1s;
	}
	/**
	 * @return the flycheckMedicalDetail
	 */
	public FlycheckMedicalDetail getFlycheckMedicalDetail() {
		return flycheckMedicalDetail;
	}
	/**
	 * @param flycheckMedicalDetail the flycheckMedicalDetail to set
	 */
	public void setFlycheckMedicalDetail(FlycheckMedicalDetail flycheckMedicalDetail) {
		this.flycheckMedicalDetail = flycheckMedicalDetail;
	}
	/**
	 * @return the flycheckMedicalDetails
	 */
	public List<FlycheckMedicalDetail> getFlycheckMedicalDetails() {
		return flycheckMedicalDetails;
	}
	/**
	 * @param flycheckMedicalDetails the flycheckMedicalDetails to set
	 */
	public void setFlycheckMedicalDetails(List<FlycheckMedicalDetail> flycheckMedicalDetails) {
		this.flycheckMedicalDetails = flycheckMedicalDetails;
	}
	/**
	 * @return the hisid
	 */
	public String getHisid() {
		return hisid;
	}
	/**
	 * @param hisid the hisid to set
	 */
	public void setHisid(String hisid) {
		this.hisid = hisid;
	}
	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}
	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	/**
	 * @return the preAdmissionDate
	 */
	public String getPreAdmissionDate() {
		return preAdmissionDate;
	}
	/**
	 * @param preAdmissionDate the preAdmissionDate to set
	 */
	public void setPreAdmissionDate(String preAdmissionDate) {
		this.preAdmissionDate = preAdmissionDate;
	}
	/**
	 * @return the dischargeDate
	 */
	public String getDischargeDate() {
		return dischargeDate;
	}
	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(String dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	/**
	 * @return the notExists
	 */
	public NotExists getNotExists() {
		return notExists;
	}
	/**
	 * @param notExists the notExists to set
	 */
	public void setNotExists(NotExists notExists) {
		this.notExists = notExists;
	}
	/**
	 * @return the notExistss
	 */
	public List<NotExists> getNotExistss() {
		return notExistss;
	}
	/**
	 * @param notExistss the notExistss to set
	 */
	public void setNotExistss(List<NotExists> notExistss) {
		this.notExistss = notExistss;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the benefitType
	 */
	public String getBenefitType() {
		return benefitType;
	}
	/**
	 * @param benefitType the benefitType to set
	 */
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	/**
	 * @return the hospitalId
	 */
	public String getHospitalId() {
		return hospitalId;
	}
	/**
	 * @param hospitalId the hospitalId to set
	 */
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}
	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	/**
	 * @return the dischargeDeptName
	 */
	public String getDischargeDeptName() {
		return dischargeDeptName;
	}
	/**
	 * @param dischargeDeptName the dischargeDeptName to set
	 */
	public void setDischargeDeptName(String dischargeDeptName) {
		this.dischargeDeptName = dischargeDeptName;
	}
	/**
	 * @return the itemIdHosp
	 */
	public String getItemIdHosp() {
		return itemIdHosp;
	}
	/**
	 * @param itemIdHosp the itemIdHosp to set
	 */
	public void setItemIdHosp(String itemIdHosp) {
		this.itemIdHosp = itemIdHosp;
	}
	/**
	 * @return the itemNameHosp
	 */
	public String getItemNameHosp() {
		return itemNameHosp;
	}
	/**
	 * @param itemNameHosp the itemNameHosp to set
	 */
	public void setItemNameHosp(String itemNameHosp) {
		this.itemNameHosp = itemNameHosp;
	}
	/**
	 * @return the itemIdHosp1
	 */
	public String getItemIdHosp1() {
		return itemIdHosp1;
	}
	/**
	 * @param itemIdHosp1 the itemIdHosp1 to set
	 */
	public void setItemIdHosp1(String itemIdHosp1) {
		this.itemIdHosp1 = itemIdHosp1;
	}
	/**
	 * @return the itemNameHosp1
	 */
	public String getItemNameHosp1() {
		return itemNameHosp1;
	}
	/**
	 * @param itemNameHosp1 the itemNameHosp1 to set
	 */
	public void setItemNameHosp1(String itemNameHosp1) {
		this.itemNameHosp1 = itemNameHosp1;
	}
	/**
	 * @return the timelimit
	 */
	public String getTimelimit() {
		return timelimit;
	}
	/**
	 * @param timelimit the timelimit to set
	 */
	public void setTimelimit(String timelimit) {
		this.timelimit = timelimit;
	}
	
}
