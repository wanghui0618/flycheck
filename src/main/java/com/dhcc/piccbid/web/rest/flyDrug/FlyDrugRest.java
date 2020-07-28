package com.dhcc.piccbid.web.rest.flyDrug;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyDrug.FlyDrugBlh;
import com.dhcc.piccbid.dto.excessiveTreat.ExcessiveTreatDto;
import com.dhcc.piccbid.dto.flyDrug.FlyDrugDto;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.page.Page;
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
@RestController
@RequestMapping("/dhccApi/flyDrug/flyDrug")
public class FlyDrugRest {

	@Resource
	private FlyDrugBlh flyDrugBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyDrugDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyDrugDto dto) {
		flyDrugBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyDrugDto saveRest(@RequestBody FlyDrugDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyDrugDto save(FlyDrugDto dto) {
		flyDrugBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyDrugDto deleteRest(@RequestBody FlyDrugDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyDrugDto delete(FlyDrugDto dto) {
		flyDrugBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyDrugDto findByIdRest(@RequestBody FlyDrugDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyDrugDto findById(FlyDrugDto dto) {
		flyDrugBlh.findById(dto);
		return dto;
	}
	
	@RequestMapping("listVo")
	public Page listVo(FlyDrugDto dto) {
		flyDrugBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

}
