package com.dhcc.piccbid.web.rest.allAnalysis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.allAnalysis.AllAnalysisBlh;
import com.dhcc.piccbid.dto.allAnalysis.AllAnalysisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangjieli
 * @date 2019-08-25 16:54:00
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/allAnalysis/allAnalysis")
public class AllAnalysisRest {

	@Resource
	private AllAnalysisBlh allAnalysisBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) AllAnalysisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(AllAnalysisDto dto) {
		allAnalysisBlh.list(dto);
		return dto.getPageModel();
	}
	

	@RequestMapping("search1")
	public Page search1(AllAnalysisDto dto) {
		allAnalysisBlh.search1(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("search2")
	public Page search2(HttpServletRequest request,AllAnalysisDto dto) {
		allAnalysisBlh.search2(request,dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public AllAnalysisDto saveRest(@RequestBody AllAnalysisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public AllAnalysisDto save(AllAnalysisDto dto) {
		allAnalysisBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public AllAnalysisDto deleteRest(@RequestBody AllAnalysisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public AllAnalysisDto delete(AllAnalysisDto dto) {
		allAnalysisBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public AllAnalysisDto findByIdRest(@RequestBody AllAnalysisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public AllAnalysisDto findById(AllAnalysisDto dto) {
		allAnalysisBlh.findById(dto);
		return dto;
	}
}
