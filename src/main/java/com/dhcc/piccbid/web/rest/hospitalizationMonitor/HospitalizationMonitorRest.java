package com.dhcc.piccbid.web.rest.hospitalizationMonitor;

import javax.annotation.Resource;

//import com.dhcc.piccbid.dto.coststatistics.HospitalCostStatisticsDto;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.hospitalizationMonitor.HospitalizationMonitorBlh;
import com.dhcc.piccbid.dto.hospitalizationMonitor.HospitalizationMonitorDto;
/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-19 17:33:36
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/hospitalizationMonitor/hospitalizationMonitor")
public class HospitalizationMonitorRest {

	@Resource
	private HospitalizationMonitorBlh hospitalizationMonitorBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) HospitalizationMonitorDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public HospitalizationMonitorDto saveRest(@RequestBody HospitalizationMonitorDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public HospitalizationMonitorDto save(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.save(dto);
		return dto;
	}

	@PostMapping(value="delete",consumes="application/json")
	public HospitalizationMonitorDto deleteRest(@RequestBody HospitalizationMonitorDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public HospitalizationMonitorDto delete(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.delete(dto);
		return dto;
	}

	@PostMapping(value="findById",consumes="application/json")
	public HospitalizationMonitorDto findByIdRest(@RequestBody HospitalizationMonitorDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public HospitalizationMonitorDto findById(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.findById(dto);
		return dto;
	}

	@PostMapping(value="yesterday",consumes="application/json")
	public PageModel yesterdayRest(@RequestBody(required=false) HospitalizationMonitorDto dto) {
		return this.yesterday(dto);
	}
	@RequestMapping("yesterday")
	public PageModel yesterday(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.yesterday(dto);
		return dto.getPageModel();
	}


	@PostMapping(value="inTop",consumes="application/json")
	public Page inTopRest(@RequestBody(required=false) HospitalizationMonitorDto dto) {
		return this.inTop(dto);
	}
	@RequestMapping("inTop")
	public Page inTop(HospitalizationMonitorDto dto) {
		hospitalizationMonitorBlh.inTop(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}

//	@PostMapping(value="cityInfo",consumes="application/json")
//	public PageModel cityInfoRest(@RequestBody(required=false) HospitalCostStatisticsDto dto) {
//		return this.cityInfo(dto);
//	}
//	@RequestMapping("cityInfo")
//	public PageModel cityInfo(HospitalCostStatisticsDto dto) {
//		hospitalizationMonitorBlh.cityInfo(dto);
//		return dto.getPageModel();
//	}
}
