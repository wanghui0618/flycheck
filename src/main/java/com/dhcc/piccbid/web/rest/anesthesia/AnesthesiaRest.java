package com.dhcc.piccbid.web.rest.anesthesia;



import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.anesthesia.AnesthesiaBlh;
import com.dhcc.piccbid.dto.anesthesia.AnesthesiaDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ZWY
 * @date 2019-06-07 11:04:35
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/anesthesia/anesthesia")
public class AnesthesiaRest {
	
	@Resource
	private AnesthesiaBlh anesthesiaBlh;
	
	@PostMapping(value="listVo",consumes="application/json")
	public PageModel listVoRest(@RequestBody(required=false) AnesthesiaDto dto) {
		return this.listVoRest(dto);
	}
	@RequestMapping("listVo")
	public Page listVo(AnesthesiaDto dto) {
		anesthesiaBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	
}
