package com.dhcc.piccbid.web.rest.flyDetailInhos;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyDetailInhos.FlyDetailInhosBlh;
import com.dhcc.piccbid.dto.flyDetailInhos.FlyDetailInhosDto;
import com.dhcc.piccbid.dto.flymedical.FlymedicalDto;
import com.dhcc.piccbid.entity.page.Page;
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
@RestController
@RequestMapping("/dhccApi/flyDetailInhos/flyDetailInhos")
public class FlyDetailInhosRest {

	@Resource
	private FlyDetailInhosBlh flyDetailInhosBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyDetailInhosDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyDetailInhosDto dto) {
		flyDetailInhosBlh.list(dto);
		return dto.getPageModel();
	}

	@RequestMapping("statisticsDrugs")
	public Page statisticsDrugs(FlyDetailInhosDto dto) {
		flyDetailInhosBlh.statisticsDrugs(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyDetailInhosDto saveRest(@RequestBody FlyDetailInhosDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyDetailInhosDto save(FlyDetailInhosDto dto) {
		flyDetailInhosBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyDetailInhosDto deleteRest(@RequestBody FlyDetailInhosDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyDetailInhosDto delete(FlyDetailInhosDto dto) {
		flyDetailInhosBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyDetailInhosDto findByIdRest(@RequestBody FlyDetailInhosDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyDetailInhosDto findById(FlyDetailInhosDto dto) {
		flyDetailInhosBlh.findById(dto);
		return dto;
	}
}
