package com.dhcc.piccbid.web.rest.medicalAnalysis;

import java.util.List;

import javax.annotation.Resource;

import com.dhcc.piccbid.dto.medicalAnalysis.MedicalResultVO;
///import com.dhcc.piccbid.dto.medicalCount.MedicalCountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.medicalAnalysis.MedicalAnalysisBlh;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.dto.medicalAnalysis.MedicalAnalysisDto;
import com.dhcc.piccbid.entity.home.HomeVo;
import com.dhcc.piccbid.entity.medicalAnalysis.HospitalVo;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wangyue
 * @date 2019-08-08 17:39:46
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/medicalAnalysis/medicalAnalysis")
public class MedicalAnalysisRest {

	@Resource
	private MedicalAnalysisBlh medicalAnalysisBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) MedicalAnalysisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.list(dto);
		return dto.getPageModel();
	}

	@PostMapping(value="save",consumes="application/json")
	public MedicalAnalysisDto saveRest(@RequestBody MedicalAnalysisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public MedicalAnalysisDto save(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public MedicalAnalysisDto deleteRest(@RequestBody MedicalAnalysisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public MedicalAnalysisDto delete(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public MedicalAnalysisDto findByIdRest(@RequestBody MedicalAnalysisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public MedicalAnalysisDto findById(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.findById(dto);
		return dto;
	}
	
	@RequestMapping("yearData")
	public Page yearData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.yearData(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("totalIllData")   //总病例数
	public Page totalIllData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.totalIllData(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("drugCostData")   //药品总费用
	public Page drugCostData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.drugCostData(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("serviceCostData")   //诊疗总费用
	public Page serviceCostData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.serviceCostData(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("materialCostData")   //诊疗总费用
	public Page materialCostData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.materialCostData(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("monthlyTrendsData")   //月度趋势
	public Page monthlyTrendsData(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.monthlyTrendsData(dto);
		//System.out.println(dto.getInFlag());
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	//@GetMapping("findHosNumber")
	@RequestMapping("findHosNumber")
	public Page findHosNumber(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.findHosNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
	
	/*@PostMapping(value = "findMonNumber", consumes = "application/json")
	public PageModel findMonNumberRest(@RequestBody(required = false) MedicalAnalysisDto dto) {
		return this.findMonNumber(dto);
	}*/
	@RequestMapping("findMonNumber")
	public Page findMonNumber(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.findMonNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
	
	@RequestMapping("singleDisease")
	public Page singleDisease(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.singleDisease(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
	  
	@RequestMapping("abnormalCost")   //异常分析
	public Page abnormalCost(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.abnormalCost(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
	@RequestMapping("abnormalCost2")   //异常分析
	public Page abnormalCost2(MedicalAnalysisDto dto) {
		medicalAnalysisBlh.abnormalCost2(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		//System.out.print(page);
		return page;
	}
    //基础业务系统统计
    @RequestMapping("medicaStatisticsForHomePage")
    public Page medicaStatisticsForHomePage(MedicalResultVO dto) {
        medicalAnalysisBlh.medicaStatisticsForHomePage(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
}
