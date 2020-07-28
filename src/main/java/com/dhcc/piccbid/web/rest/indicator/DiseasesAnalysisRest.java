package com.dhcc.piccbid.web.rest.indicator;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.indicator.DiseasesAnalysisBlh;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liuzhiheng
 * @date 2019-08-22 12:22:11
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/indicator/diseasesAnalysis")
public class DiseasesAnalysisRest {

	@Resource
	private DiseasesAnalysisBlh diseasesAnalysisBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public DiseasesAnalysisDto saveRest(@RequestBody DiseasesAnalysisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public DiseasesAnalysisDto save(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.save(dto);
		return dto;
	}

	@PostMapping(value="delete",consumes="application/json")
	public DiseasesAnalysisDto deleteRest(@RequestBody DiseasesAnalysisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public DiseasesAnalysisDto delete(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.delete(dto);
		return dto;
	}

	@PostMapping(value="findById",consumes="application/json")
	public DiseasesAnalysisDto findByIdRest(@RequestBody DiseasesAnalysisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public DiseasesAnalysisDto findById(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.findById(dto);
		return dto;
	}

	@PostMapping(value="listVo",consumes="application/json")
	public Page listVoRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.listVo(dto);
	}
	@RequestMapping("listVo")
	public Page listVo(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}


	@PostMapping(value="listVoTest",consumes="application/json")
	public Page listVoTestRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.listVoTest(dto);
	}
	@RequestMapping("listVoTest")
	public Page listVoTest(DiseasesAnalysisDto dto) {
		try{
			if (dto.getDiseasesAnalysis().getCondition() != null && !dto.getDiseasesAnalysis().getCondition().equals("")) {
				String handdingCode = URLDecoder.decode(dto.getDiseasesAnalysis().getCondition(), "UTF-8");
				dto.getDiseasesAnalysis().setCondition(handdingCode);
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		diseasesAnalysisBlh.listVoTest(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}


	@PostMapping(value="handdInfo",consumes="application/json")
	public Page handdInfoRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.handdInfo(dto);
	}
	@RequestMapping("handdInfo")
	public Page handdInfo(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.handdInfo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	@PostMapping(value="diseases",consumes="application/json")
	public Page diseasesRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.diseases(dto);
	}
	@RequestMapping("diseases")
	public Page diseases(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.diseases(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}


	@PostMapping(value="hopLevel",consumes="application/json")
	public Page hopLevelRest(@RequestBody(required=false) DiseasesAnalysisDto dto) {
		return this.hopLevel(dto);
	}
	@RequestMapping("hopLevel")
	public Page hopLevel(DiseasesAnalysisDto dto) {
		diseasesAnalysisBlh.hopLevel(dto);
		Page page = new Page();
		page.setMsg(String.valueOf(dto.getPageModel().getPageNo()));
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		page.setCount("0");
		return page;
	}
}
