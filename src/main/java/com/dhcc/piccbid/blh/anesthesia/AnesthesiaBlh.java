package com.dhcc.piccbid.blh.anesthesia;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto;
import com.dhcc.piccbid.service.anesthesia.AnesthesiaService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class AnesthesiaBlh extends BaseAbstractBlh<AnesthesiaDto> {

	private static Log logger = LogFactory.getLog(AnesthesiaBlh.class);

	@Resource
	private AnesthesiaService anesthesiaService;

	/**
	 * @param dto
	 */
	public void listVo(AnesthesiaDto basedto) {
		// TODO Auto-generated method stub
		//统计一次住院全身麻醉次数大于1
		AnesthesiaDto dto = super.getExactlyDto(basedto);
		
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		anesthesiaService.listVo(dto);
		
	}

	
		
	

	
	
	
}
