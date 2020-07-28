package com.dhcc.piccbid.dto.dictdiag;

import java.util.List;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.dictdiag.Dictdiag;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-30 10:13:57
 * @version V1.0
 */
public class DictdiagDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Dictdiag Dictdiag;
	private List<Dictdiag> Dictdiags;

	public Dictdiag getDictdiag() {
		return Dictdiag;
	}

	public void setDictdiag(Dictdiag Dictdiag) {
		this.Dictdiag = Dictdiag;
	}

	public List<Dictdiag> getDictdiags() {
		return Dictdiags;
	}

	public void setDictdiags(List<Dictdiag> Dictdiags) {
		this.Dictdiags = Dictdiags;
	}
}
