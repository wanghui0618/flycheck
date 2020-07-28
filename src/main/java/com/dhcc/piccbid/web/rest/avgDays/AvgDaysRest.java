package com.dhcc.piccbid.web.rest.avgDays;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.avgDays.AvgDaysBlh;
import com.dhcc.piccbid.dto.avgDays.AvgDaysDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-07-31 10:28:01
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/avgDays/avgDays")
public class AvgDaysRest {

	@Resource
	private AvgDaysBlh avgDaysBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) AvgDaysDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(AvgDaysDto dto) {
		avgDaysBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public AvgDaysDto saveRest(@RequestBody AvgDaysDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public AvgDaysDto save(AvgDaysDto dto) {
		avgDaysBlh.save(dto);
		return dto;
	}
	
//	@PostMapping(value="delete",consumes="application/json")
//	public AvgDaysDto deleteRest(@RequestBody AvgDaysDto dto) {
//		return this.delete(dto);
//	}
//	@PostMapping("delete")
//	public AvgDaysDto delete(AvgDaysDto dto) {
//		avgDaysBlh.delete(dto);
//		return dto;
//	}
//
//	@PostMapping(value="findById",consumes="application/json")
//	public AvgDaysDto findByIdRest(@RequestBody AvgDaysDto dto) {
//		return this.findById(dto);
//	}
//	@PostMapping("findById")
//	public AvgDaysDto findById(AvgDaysDto dto) {
//		avgDaysBlh.findById(dto);
//		return dto;
//	}

	@PostMapping(value="listVo",consumes="application/json")
	public Page listVoRest(@RequestBody(required=false) AvgDaysDto dto) {
		return this.listVo(dto);
	}
	@RequestMapping("listVo")
	public Page listVo(AvgDaysDto dto) {
		avgDaysBlh.listVo(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
}
