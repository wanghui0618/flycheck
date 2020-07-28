package com.dhcc.piccbid.dto.flyDetailInhos;

import java.util.List;
import com.dhcc.piccbid.entity.flyDetailInhos.FlyDetailInhos;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-29 16:37:25
 * @version V1.0
 */
public class FlyDetailInhosDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private FlyDetailInhos flyDetailInhos;
	private List<FlyDetailInhos> flyDetailInhoss;

	public FlyDetailInhos getFlyDetailInhos() {
		return flyDetailInhos;
	}

	public void setFlyDetailInhos(FlyDetailInhos flyDetailInhos) {
		this.flyDetailInhos = flyDetailInhos;
	}

	public List<FlyDetailInhos> getFlyDetailInhoss() {
		return flyDetailInhoss;
	}

	public void setFlyDetailInhoss(List<FlyDetailInhos> flyDetailInhoss) {
		this.flyDetailInhoss = flyDetailInhoss;
	}
}
