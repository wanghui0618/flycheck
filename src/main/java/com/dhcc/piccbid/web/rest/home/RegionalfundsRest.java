package com.dhcc.piccbid.web.rest.home;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.home.MonitorPolygonalchartDto;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.home.RegionalfundsBlh;
import com.dhcc.piccbid.dto.home.RegionalfundsDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-15 16:58:47
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/home/regionalfunds")
public class RegionalfundsRest {

	@Resource
	private RegionalfundsBlh regionalfundsBlh;

	@PostMapping(value = "list", consumes = "application/json")
	public PageModel listRest(@RequestBody(required = false) RegionalfundsDto dto) {
		return this.list(dto);
	}

	@RequestMapping("list")
	public PageModel list(RegionalfundsDto dto) {
		regionalfundsBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value = "save", consumes = "application/json")
	public RegionalfundsDto saveRest(@RequestBody RegionalfundsDto dto) {
		return this.save(dto);
	}

	@PostMapping("save")
	public RegionalfundsDto save(RegionalfundsDto dto) {
		regionalfundsBlh.save(dto);
		return dto;
	}

	@PostMapping(value = "delete", consumes = "application/json")
	public RegionalfundsDto deleteRest(@RequestBody RegionalfundsDto dto) {
		return this.delete(dto);
	}

	@PostMapping("delete")
	public RegionalfundsDto delete(RegionalfundsDto dto) {
		regionalfundsBlh.delete(dto);
		return dto;
	}

	@PostMapping(value = "findById", consumes = "application/json")
	public RegionalfundsDto findByIdRest(@RequestBody RegionalfundsDto dto) {
		return this.findById(dto);
	}

	@PostMapping("findById")
	public RegionalfundsDto findById(RegionalfundsDto dto) {
		regionalfundsBlh.findById(dto);
		return dto;
	}

	@PostMapping(value = "listVo", consumes = "application/json")
	public Page listVoRest(@RequestBody(required = false) RegionalfundsDto dto) {
		return this.listVo(dto);
	}

	@RequestMapping("listVo")
	public Page listVo(RegionalfundsDto dto) {
		regionalfundsBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}

	@PostMapping(value = "listVo2", consumes = "application/json")
	public Page listVoRest2(@RequestBody(required = false) MonitorPolygonalchartDto dto) {
		return this.listVo2(dto);
	}

	@RequestMapping("listVo2")
	public Page listVo2(MonitorPolygonalchartDto dto) {
		regionalfundsBlh.listVo2(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}

	@PostMapping(value = "totalTimes", consumes = "application/json")
	public Page totalTimesRest2(@RequestBody(required = false) HospitalizationMonitorDto dto) {
		return this.totalTimes(dto);
	}

	@RequestMapping("totalTimes")
	public Page totalTimes(HospitalizationMonitorDto dto) {
		regionalfundsBlh.totalTimes(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
	
	@PostMapping(value="bigDataHomepage",consumes="application/json")
	public RegionalfundsDto bigDataHomepageRest(@RequestBody RegionalfundsDto dto) {
		return this.bigDataHomepageRest(dto);
	}
	@PostMapping("bigDataHomepage")
	public RegionalfundsDto bigDataHomepage(RegionalfundsDto dto) {
		regionalfundsBlh.bigDataHomepage(dto);
		return dto;
	}
}
