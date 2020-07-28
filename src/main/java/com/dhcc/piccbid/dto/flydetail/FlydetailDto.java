package com.dhcc.piccbid.dto.flydetail;

import java.util.List;
import com.dhcc.piccbid.entity.flydetail.Flydetail;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author renjie
 * @date 2019-10-15 14:19:36
 * @version V1.0
 */
public class FlydetailDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Flydetail flydetail;
	private List<Flydetail> flydetails;

	public Flydetail getFlydetail() {
		return flydetail;
	}

	public void setFlydetail(Flydetail flydetail) {
		this.flydetail = flydetail;
	}

	public List<Flydetail> getFlydetails() {
		return flydetails;
	}

	public void setFlydetails(List<Flydetail> flydetails) {
		this.flydetails = flydetails;
	}
}
