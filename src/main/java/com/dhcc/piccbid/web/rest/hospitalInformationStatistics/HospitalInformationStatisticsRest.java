package com.dhcc.piccbid.web.rest.hospitalInformationStatistics;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.dhcc.piccbid.blh.hospitalInformationStatistics.HospitalInformationStatisticsBlh;
import com.dhcc.piccbid.dto.hospitalInformationStatistics.HospitalInformationStatisticsDto;
import com.dhcc.piccbid.entity.page.Page;

@RestController
@RequestMapping("/dhccApi/hospitalInformationStatistics/hospitalInformationStatistics")
public class HospitalInformationStatisticsRest {
	
	@Resource
	private HospitalInformationStatisticsBlh hospitalInformationStatisticsBlh;
	
	@PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) HospitalInformationStatisticsDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(HospitalInformationStatisticsDto dto) {
    	hospitalInformationStatisticsBlh.list(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    
	//住院数据统计
	@RequestMapping("dataStatistics")
	public Page dataStatistics(HospitalInformationStatisticsDto dto) {
		hospitalInformationStatisticsBlh.dataStatistics(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	  //门诊数据统计
	@RequestMapping("outpatientDataStatistics")
	public  Page outpatientDataStatistics(HospitalInformationStatisticsDto dto) {
		hospitalInformationStatisticsBlh.outpatientDataStatistics(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	 //住院药品/诊疗/耗材占比
	@RequestMapping("drugsDiagnosisTreatment")
	public Page drugsDiagnosisTreatment(HospitalInformationStatisticsDto dto) {
		hospitalInformationStatisticsBlh.drugsDiagnosisTreatment(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
		
	//门诊药品/诊疗/耗材占比
	@RequestMapping("drugsDiagnosisTreatmentMed")
	public Page drugsDiagnosisTreatmentMed(HospitalInformationStatisticsDto dto) {
		hospitalInformationStatisticsBlh.drugsDiagnosisTreatmentMed(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	//住院各项目收费占比
	@RequestMapping("proportionChargingItems")
    public Page proportionChargingItems(HospitalInformationStatisticsDto dto) {
    	Page page = new Page();
    	hospitalInformationStatisticsBlh.proportionChargingItems(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//住院各项目收费占比
	@RequestMapping("proportionChargingItemsMed")
	public Page proportionChargingItemsMed(HospitalInformationStatisticsDto dto) {
	    Page page = new Page();
	    hospitalInformationStatisticsBlh.proportionChargingItemsMed(dto);
	    page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
	    return page;
	}
	
	//月度平均住院天数
	@RequestMapping("averageLengthOfStay")
    public Page averageLengthOfStay(HospitalInformationStatisticsDto dto) {
    	Page page = new Page();
    	hospitalInformationStatisticsBlh.averageLengthOfStay(dto);
    	page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	
	//住院科室费用top10
	@RequestMapping("departmentRanking")
	public Page departmentRanking(HospitalInformationStatisticsDto dto) {
		Page page = new Page();
		hospitalInformationStatisticsBlh.departmentRanking(dto);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	//门诊科室费用top10
	@RequestMapping("departmentRankingMed")
	public Page departmentRankingMed(HospitalInformationStatisticsDto dto) {
		Page page = new Page();
		hospitalInformationStatisticsBlh.departmentRankingMed(dto);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	//住院诊疗费用top10
	@RequestMapping("treatmentRanking")
	public Page treatmentRanking(HospitalInformationStatisticsDto dto) {
		Page page = new Page();
		hospitalInformationStatisticsBlh.treatmentRanking(dto);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
		
	//门诊诊疗费用top10
	@RequestMapping("treatmentRankingMed")
	public Page treatmentRankingMed(HospitalInformationStatisticsDto dto) {
		Page page = new Page();
		hospitalInformationStatisticsBlh.treatmentRankingMed(dto);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	//批量导出方法
	@RequestMapping(value="/expDate")
	public HospitalInformationStatisticsDto expDate(HospitalInformationStatisticsDto dto) {
		hospitalInformationStatisticsBlh.exportH(dto);
		return dto;
	}

}
