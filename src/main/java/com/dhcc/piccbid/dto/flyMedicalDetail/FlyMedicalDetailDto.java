package com.dhcc.piccbid.dto.flyMedicalDetail;

import java.util.List;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetail;
import com.dhcc.piccbid.entity.flyMedicalDetail.FlyMedicalDetailVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-10-15 14:48:33
 * @version V1.0
 */
public class FlyMedicalDetailDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyMedicalDetail flyMedicalDetail;
	private List<FlyMedicalDetail> flyMedicalDetails;
	private FlyMedicalDetailVo flyMedicalDetailVo;
	private List<FlyMedicalDetailVo> flyMedicalDetailVos;
	private String month;
	private String year;

	public FlyMedicalDetail getFlyMedicalDetail() {
		return flyMedicalDetail;
	}

	public void setFlyMedicalDetail(FlyMedicalDetail flyMedicalDetail) {
		this.flyMedicalDetail = flyMedicalDetail;
	}

	public List<FlyMedicalDetail> getFlyMedicalDetails() {
		return flyMedicalDetails;
	}

	public void setFlyMedicalDetails(List<FlyMedicalDetail> flyMedicalDetails) {
		this.flyMedicalDetails = flyMedicalDetails;
	}

	public FlyMedicalDetailVo getFlyMedicalDetailVo() {
		return flyMedicalDetailVo;
	}

	public void setFlyMedicalDetailVo(FlyMedicalDetailVo flyMedicalDetailVo) {
		this.flyMedicalDetailVo = flyMedicalDetailVo;
	}

	public List<FlyMedicalDetailVo> getFlyMedicalDetailVos() {
		return flyMedicalDetailVos;
	}

	public void setFlyMedicalDetailVos(List<FlyMedicalDetailVo> flyMedicalDetailVos) {
		this.flyMedicalDetailVos = flyMedicalDetailVos;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
