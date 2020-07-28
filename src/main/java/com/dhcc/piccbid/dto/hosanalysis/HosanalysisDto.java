package com.dhcc.piccbid.dto.hosanalysis;

import java.util.List;
import com.dhcc.piccbid.entity.home.Regionalfunds;
import com.dhcc.piccbid.entity.hosanalysis.HosanalysisVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xtl

 */
public class HosanalysisDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;
	private Integer beiShu;
	private List<HosanalysisVo>  hosanalysisVos;
	private HosanalysisVo hosanalysisVo;
	private String handdingName;
	private String illName;
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
	/**
	 * @return the hosanalysisVo
	 */
	public HosanalysisVo getHosanalysisVo() {
		return hosanalysisVo;
	}
	/**
	 * @param hosanalysisVo the hosanalysisVo to set
	 */
	public void setHosanalysisVo(HosanalysisVo hosanalysisVo) {
		this.hosanalysisVo = hosanalysisVo;
	}
	/**
	 * @return the hosanalysisVos
	 */
	public List<HosanalysisVo> getHosanalysisVos() {
		return hosanalysisVos;
	}
	/**
	 * @param hosanalysisVos the hosanalysisVos to set
	 */
	public void setHosanalysisVos(List<HosanalysisVo> hosanalysisVos) {
		this.hosanalysisVos = hosanalysisVos;
	}
	/**  
	* @return beiShu 
	*/
	public Integer getBeiShu() {
		return beiShu;
	}
	/**  
	* @param beiShu beiShu 
	*/
	public void setBeiShu(Integer beiShu) {
		this.beiShu = beiShu;
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

}
