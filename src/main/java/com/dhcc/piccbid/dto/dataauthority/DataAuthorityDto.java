package com.dhcc.piccbid.dto.dataauthority;

import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.entity.dataauthority.DataAuthority;
import com.dhcc.piccbid.entity.dataauthority.DataAuthorityVO;
import com.dhcc.piccbid.entity.dict.DictResponseVo;

import java.util.List;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @date 2019-06-25 09:36:33
 * @version V1.0
 */
public class DataAuthorityDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private DataAuthority dataAuthority;
	private DataAuthorityVO dataAuthorityVO;
	private List<DataAuthority> dataAuthoritys;
	private DictResponseVo dictResponseVo;


	public DataAuthority getDataAuthority() {
		return dataAuthority;
	}

	public void setDataAuthority(DataAuthority dataAuthority) {
		this.dataAuthority = dataAuthority;
	}

	public List<DataAuthority> getDataAuthoritys() {
		return dataAuthoritys;
	}

	public void setDataAuthoritys(List<DataAuthority> dataAuthoritys) {
		this.dataAuthoritys = dataAuthoritys;
	}

	public DataAuthorityVO getDataAuthorityVO() {
		return dataAuthorityVO;
	}

	public void setDataAuthorityVO(DataAuthorityVO dataAuthorityVO) {
		this.dataAuthorityVO = dataAuthorityVO;
	}
}
