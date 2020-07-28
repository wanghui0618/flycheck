package com.dhcc.piccbid.web.rest.flyAvgDay;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyAvgDay.FlyAvgDayBlh;
import com.dhcc.piccbid.dto.flyAvgDay.FlyAvgDayDto;
//import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wy
 * @date 2019-10-15 14:35:33
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyAvgDay/flyAvgDay")
public class FlyAvgDayRest {

	@Resource
	private FlyAvgDayBlh flyAvgDayBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyAvgDayDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyAvgDayDto dto) {
		flyAvgDayBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyAvgDayDto saveRest(@RequestBody FlyAvgDayDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyAvgDayDto save(FlyAvgDayDto dto) {
		flyAvgDayBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyAvgDayDto deleteRest(@RequestBody FlyAvgDayDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyAvgDayDto delete(FlyAvgDayDto dto) {
		flyAvgDayBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyAvgDayDto findByIdRest(@RequestBody FlyAvgDayDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyAvgDayDto findById(FlyAvgDayDto dto) {
		flyAvgDayBlh.findById(dto);
		return dto;
	}
	
	
	@RequestMapping("flyAvgDay")
	public Page flyAvgDay(FlyAvgDayDto dto) {
		flyAvgDayBlh.flyAvgDay(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
}
