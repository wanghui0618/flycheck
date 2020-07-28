package com.dhcc.piccbid.dto.flyDrug;

import java.util.List;
import com.dhcc.piccbid.entity.flyDrug.FlyDrug;
import com.dhcc.piccbid.entity.flyDrug.FlyDrugDetailVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author cgq
 * @date 2019-10-16 15:05:04
 * @version V1.0
 */
public class FlyDrugDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyDrug flyDrug;
	private List<FlyDrug> flyDrugs;
	private FlyDrugDetailVo flyDrugDetailVo;
	private List<FlyDrugDetailVo> flyDrugDetailVos;
	private String chooseYear;
	private String chooseMonth;
	private String itemNameIns;

	public FlyDrug getFlyDrug() {
		return flyDrug;
	}

	public void setFlyDrug(FlyDrug flyDrug) {
		this.flyDrug = flyDrug;
	}

	public List<FlyDrug> getFlyDrugs() {
		return flyDrugs;
	}

	public void setFlyDrugs(List<FlyDrug> flyDrugs) {
		this.flyDrugs = flyDrugs;
	}

	public FlyDrugDetailVo getFlyDrugDetailVo() {
		return flyDrugDetailVo;
	}

	public void setFlyDrugDetailVo(FlyDrugDetailVo flyDrugDetailVo) {
		this.flyDrugDetailVo = flyDrugDetailVo;
	}

	public List<FlyDrugDetailVo> getFlyDrugDetailVos() {
		return flyDrugDetailVos;
	}

	public void setFlyDrugDetailVos(List<FlyDrugDetailVo> flyDrugDetailVos) {
		this.flyDrugDetailVos = flyDrugDetailVos;
	}

	public String getChooseYear() {
		return chooseYear;
	}

	public void setChooseYear(String chooseYear) {
		this.chooseYear = chooseYear;
	}

	public String getChooseMonth() {
		return chooseMonth;
	}

	public void setChooseMonth(String chooseMonth) {
		this.chooseMonth = chooseMonth;
	}

	public String getItemNameIns() {
		return itemNameIns;
	}

	public void setItemNameIns(String itemNameIns) {
		this.itemNameIns = itemNameIns;
	}

	
	
}
