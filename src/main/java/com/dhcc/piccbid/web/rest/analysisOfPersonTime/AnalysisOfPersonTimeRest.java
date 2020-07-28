package com.dhcc.piccbid.web.rest.analysisOfPersonTime;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.analysisOfPersonTime.AnalysisOfPersonTimeBlh;
import com.dhcc.piccbid.dto.analysisOfPersonTime.AnalysisOfPersonTimeDto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-09-23 15:00:27
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/analysisOfPersonTime/analysisOfPersonTime")
public class AnalysisOfPersonTimeRest {

	@Resource
	private AnalysisOfPersonTimeBlh analysisOfPersonTimeBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) AnalysisOfPersonTimeDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public AnalysisOfPersonTimeDto saveRest(@RequestBody AnalysisOfPersonTimeDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public AnalysisOfPersonTimeDto save(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public AnalysisOfPersonTimeDto deleteRest(@RequestBody AnalysisOfPersonTimeDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public AnalysisOfPersonTimeDto delete(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public AnalysisOfPersonTimeDto findByIdRest(@RequestBody AnalysisOfPersonTimeDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public AnalysisOfPersonTimeDto findById(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.findById(dto);
		return dto;
	}



	@PostMapping(value="table1",consumes="application/json")
	public PageModel table1Rest(@RequestBody(required=false) AnalysisOfPersonTimeDto dto) {
		return this.table1(dto);
	}
	@RequestMapping("table1")
	public PageModel table1(AnalysisOfPersonTimeDto dto) {
		try{
			if (dto.getAnalysisOfPersonTime().getHanddingInsCode() != null && !dto.getAnalysisOfPersonTime().getHanddingInsCode().equals("")) {
				String handdingCode = URLDecoder.decode(dto.getAnalysisOfPersonTime().getHanddingInsCode(), "UTF-8");
				dto.getAnalysisOfPersonTime().setHanddingInsCode(handdingCode);
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		analysisOfPersonTimeBlh.table1(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="table2",consumes="application/json")
	public PageModel table2Rest(@RequestBody(required=false) AnalysisOfPersonTimeDto dto) {
		return this.table2(dto);
	}
	@RequestMapping("table2")
	public PageModel table2(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.table2(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="table3",consumes="application/json")
	public Page table3Rest(@RequestBody(required=false) AnalysisOfPersonTimeDto dto) {
		return this.table3(dto);
	}
	@RequestMapping("table3")
	public Page table3(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.table3(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="table4",consumes="application/json")
	public Page table4Rest(@RequestBody(required=false) AnalysisOfPersonTimeDto dto) {
		return this.table4(dto);
	}
	@RequestMapping("table4")
	public Page table4(AnalysisOfPersonTimeDto dto) {
		analysisOfPersonTimeBlh.table4(dto);
		Page page=new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
}
