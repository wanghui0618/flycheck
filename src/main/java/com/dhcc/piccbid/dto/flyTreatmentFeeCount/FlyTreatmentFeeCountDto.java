package com.dhcc.piccbid.dto.flyTreatmentFeeCount;

import java.util.List;
import com.dhcc.piccbid.entity.flyTreatmentFeeCount.FlyTreatmentFeeCount;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zfm
 * @date 2019-10-17 22:02:46
 * @version V1.0
 */
public class FlyTreatmentFeeCountDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyTreatmentFeeCount flyTreatmentFeeCount;
	private List<FlyTreatmentFeeCount> flyTreatmentFeeCounts;

	public FlyTreatmentFeeCount getFlyTreatmentFeeCount() {
		return flyTreatmentFeeCount;
	}

	public void setFlyTreatmentFeeCount(FlyTreatmentFeeCount flyTreatmentFeeCount) {
		this.flyTreatmentFeeCount = flyTreatmentFeeCount;
	}

	public List<FlyTreatmentFeeCount> getFlyTreatmentFeeCounts() {
		return flyTreatmentFeeCounts;
	}

	public void setFlyTreatmentFeeCounts(List<FlyTreatmentFeeCount> flyTreatmentFeeCounts) {
		this.flyTreatmentFeeCounts = flyTreatmentFeeCounts;
	}
}
