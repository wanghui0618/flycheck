package com.dhcc.piccbid.service.anesthesia;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-04-11 15:28:39
 * @version V1.0
 */
public interface AnesthesiaService {

	/**
	 * @param dto
	 */
	PageModel listVo(AnesthesiaDto dto);

	
	

}
