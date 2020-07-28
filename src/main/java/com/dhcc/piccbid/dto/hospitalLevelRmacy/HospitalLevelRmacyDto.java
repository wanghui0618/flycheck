package com.dhcc.piccbid.dto.hospitalLevelRmacy;

import java.util.List;

import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalDetailVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.FlyCheckMedicalVo;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.HospitalLevelRmacy;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-11-27 10:00:37
 * @version V1.0
 */
public class HospitalLevelRmacyDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private HospitalLevelRmacy hospitalLevelRmacy;
	private List<HospitalLevelRmacy> hospitalLevelRmacys;
	private FlyCheckMedicalVo flyCheckMedicalVo;
	private List<FlyCheckMedicalVo> flyCheckMedicalVos;
	private FlyCheckMedicalDetailVo flyCheckMedicalDetailVo;
	private List<FlyCheckMedicalDetailVo> flyCheckMedicalDetailVos;
	private String type;//0 住院，1 门诊
	private String queryParams;
	private String phyID;

	public String getPhyID() {
		return phyID;
	}

	public void setPhyID(String phyID) {
		this.phyID = phyID;
	}

	public HospitalLevelRmacy getHospitalLevelRmacy() {
		return hospitalLevelRmacy;
	}

	public void setHospitalLevelRmacy(HospitalLevelRmacy hospitalLevelRmacy) {
		this.hospitalLevelRmacy = hospitalLevelRmacy;
	}

	public List<HospitalLevelRmacy> getHospitalLevelRmacys() {
		return hospitalLevelRmacys;
	}

	public void setHospitalLevelRmacys(List<HospitalLevelRmacy> hospitalLevelRmacys) {
		this.hospitalLevelRmacys = hospitalLevelRmacys;
	}

	public FlyCheckMedicalVo getFlyCheckMedicalVo() {
		return flyCheckMedicalVo;
	}

	public void setFlyCheckMedicalVo(FlyCheckMedicalVo flyCheckMedicalVo) {
		this.flyCheckMedicalVo = flyCheckMedicalVo;
	}

	public List<FlyCheckMedicalVo> getFlyCheckMedicalVos() {
		return flyCheckMedicalVos;
	}

	public void setFlyCheckMedicalVos(List<FlyCheckMedicalVo> flyCheckMedicalVos) {
		this.flyCheckMedicalVos = flyCheckMedicalVos;
	}

	public FlyCheckMedicalDetailVo getFlyCheckMedicalDetailVo() {
		return flyCheckMedicalDetailVo;
	}

	public void setFlyCheckMedicalDetailVo(FlyCheckMedicalDetailVo flyCheckMedicalDetailVo) {
		this.flyCheckMedicalDetailVo = flyCheckMedicalDetailVo;
	}

	public List<FlyCheckMedicalDetailVo> getFlyCheckMedicalDetailVos() {
		return flyCheckMedicalDetailVos;
	}

	public void setFlyCheckMedicalDetailVos(List<FlyCheckMedicalDetailVo> flyCheckMedicalDetailVos) {
		this.flyCheckMedicalDetailVos = flyCheckMedicalDetailVos;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	
	
	
	
	
	
	
}
