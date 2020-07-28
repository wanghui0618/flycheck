/**
 * 
 */
package com.dhcc.piccbid.web.rest.dataCompare;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.dto.hosanalysis.HosanalysisDto;

import com.dhcc.piccbid.entity.hosanalysis.HosanalysisVo;

import com.dhcc.piccbid.entity.page.Page;

/**
 * @author xtl
 *
 */
@RestController
@RequestMapping("/dhccApi/dataCompare/dataCompare")
public class DataCompareRest {

	/*@Resource
	private SuperviseResBlh superviseResBlh;*/



	@RequestMapping("countcompare")
	public Page countcompare(FlyGeneralOverviewDto dto) {
		//flyGeneralOverviewBlh.FlyGeneralOverviewDtoFind(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}
	/*
	@RequestMapping("getInhosDayByAreaList")
	@ResponseBody
	public Page getInhosDayByAreaList(HosanalysisDto dto) {
		Page page = new Page();
		hosanalysisBlh.getInhosDayByArea(dto);
		// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
		// Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	// getInhosDayByHosList
	@RequestMapping("getInhosDayByHosList")
	@ResponseBody
	public Page getInhosDayByHosList(HosanalysisDto dto) {
		Page page = new Page();
		hosanalysisBlh.getInhosDayByHosList(dto);
		// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
		// Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	// getConditionByCondition
	@RequestMapping("getConditionByCondition")
	public Page getConditionByCondition(HosanalysisDto dto) {
		Page page = new Page();
		List<HosanalysisVo> list = hosanalysisBlh.getConditionByCondition(dto);

		page.setData(list);
		page.setCount(String.valueOf(list.size()));
		return page;

	}

	// getConditionList 得到病情下拉列表
	@RequestMapping("getConditionList")
	public List<HosanalysisVo> getConditionList() {

		return hosanalysisBlh.getConditionList();
	}

	// getConditionByConditionList
	@RequestMapping("getConditionByConditionList")
	@ResponseBody
	public Page getConditionByCList(HosanalysisDto dto) {
		Page page = new Page();
		hosanalysisBlh.getConditionByCList(dto);
		// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
		// Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	@RequestMapping("getConditionByName")
	public Page getConditionByName(String name) {
		Page page = new Page();
		List<HosanalysisVo> list = hosanalysisBlh.getConditionByName(name);
		page.setData(list);
		page.setCount(String.valueOf(list.size()));
		return page;

	}

	@RequestMapping("getConditionByNameList")
	public Page getConditionByNameList(HosanalysisDto dto, String name) {
		Page page = new Page();
		hosanalysisBlh.getConditionByName(dto, name);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getPageSize()));
		return page;

	}
	
	//门诊次均费用 病情的信息 
	
	// getConditionByCondition
		@RequestMapping("getConditionByMZCondition")
		public Page getConditionByMZCondition() {
			Page page = new Page();
			List<HosanalysisVo> list = hosanalysisBlh.getConditionByMZCondition();

			page.setData(list);
			page.setCount(String.valueOf(list.size()));
			return page;

		}

		// getConditionList 得到病情下拉列表
		@RequestMapping("getMZConditionList")
		public List<HosanalysisVo> getMZConditionList() {

			return hosanalysisBlh.getConditionList();
		}

		// getConditionByConditionList
		@RequestMapping("getConditionByMZConditionList")
		@ResponseBody
		public Page getConditionByMZConditionList(HosanalysisDto dto) {
			Page page = new Page();
			hosanalysisBlh.getConditionByMZConditionList(dto);
			// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
			// Page page = new Page();
			page.setData(dto.getPageModel().getPageData());
			page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			return page;

			// page.setCount(list.size());
			// List<HosanalysisVo> list =
			// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
			// return list;

		}

		@RequestMapping("getMZConditionByName")
		public Page getMZConditionByName(String name) {
			Page page = new Page();
			List<HosanalysisVo> list = hosanalysisBlh.getMZConditionByName(name);
			page.setData(list);
			page.setCount(String.valueOf(list.size()));
			return page;

		}

		@RequestMapping("getMZConditionByNameList")
		public Page getMZConditionByNameList(HosanalysisDto dto, String name) {
			Page page = new Page();
			hosanalysisBlh.getMZConditionByNameList(dto, name);
			page.setData(dto.getPageModel().getPageData());
			page.setCount(String.valueOf(dto.getPageModel().getPageSize()));
			return page;

		}
		
		//
		//住院次均费用 病情的信息 
		
		// getConditionByCondition
			@RequestMapping("getConditionByZYCondition")
			public Page getConditionByZYCondition() {
				Page page = new Page();
				List<HosanalysisVo> list = hosanalysisBlh.getConditionByZYCondition();

				page.setData(list);
				page.setCount(String.valueOf(list.size()));
				return page;

			}

			
			// getConditionByConditionList
			@RequestMapping("getConditionByZYConditionList")
			@ResponseBody
			public Page getConditionByZYConditionList(HosanalysisDto dto) {
				Page page = new Page();
				hosanalysisBlh.getConditionByZYConditionList(dto);
				// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
				// Page page = new Page();
				page.setData(dto.getPageModel().getPageData());
				page.setCount(String.valueOf(dto.getPageModel().getTotals()));
				return page;

				// page.setCount(list.size());
				// List<HosanalysisVo> list =
				// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
				// return list;

			}
			
			@RequestMapping("getConditionByZYConditionList2")
			@ResponseBody
			public Page getConditionByZYConditionList2(HosanalysisDto dto) {
				Page page = new Page();
				hosanalysisBlh.getConditionByZYConditionList2(dto);
				// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
				// Page page = new Page();
				page.setData(dto.getPageModel().getPageData());
				page.setCount(String.valueOf(dto.getPageModel().getTotals()));
				return page;

				// page.setCount(list.size());
				// List<HosanalysisVo> list =
				// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
				// return list;

			}
			
			@RequestMapping("getConditionByZYConditionList3")
			@ResponseBody
			public Page getConditionByZYConditionList3(HosanalysisDto dto) {
				Page page = new Page();
				hosanalysisBlh.getConditionByZYConditionList3(dto);
				// List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByArea();
				// Page page = new Page();
				page.setData(dto.getPageModel().getPageData());
				page.setCount(String.valueOf(dto.getPageModel().getTotals()));
				return page;

				// page.setCount(list.size());
				// List<HosanalysisVo> list =
				// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
				// return list;

			}
			
			@RequestMapping("getZYConditionByName")
			public Page getZYConditionByName(String name) {
				Page page = new Page();
				List<HosanalysisVo> list = hosanalysisBlh.getZYConditionByName(name);
				page.setData(list);
				page.setCount(String.valueOf(list.size()));
				return page;

			}

			@RequestMapping("getZYConditionByNameList")
			public Page getZYConditionByNameList(HosanalysisDto dto, String name) {
				Page page = new Page();
				hosanalysisBlh.getZYConditionByNameList(dto, name);
				page.setData(dto.getPageModel().getPageData());
				page.setCount(String.valueOf(dto.getPageModel().getPageSize()));
				return page;

			}
			
	
	//getZYMTitleInfo
	//住院天数 头信息
	@RequestMapping("getZYTitleInfo")
	public String getZYTitleInfo(){
		
		return hosanalysisBlh.getZYTitleInfo();
	} 
	//门诊次均  头信息 getMZMTitleInfo
	@RequestMapping("getMZMTitleInfo")
	public String getMZMTitleInfo(){
		
		return hosanalysisBlh.getMZMTitleInfo();
	} 

	//住院次均  头信息 getZYMTitleInfo
		@RequestMapping("getZYMTitleInfo")
		public String getZYMTitleInfo(){
			
			return hosanalysisBlh.getZYMTitleInfo();
		} 
	
	@RequestMapping("getCostByArea")
	public List<HosanalysisVo> getCostByArea() {
		return hosanalysisBlh.getCostByArea();

	}

	@RequestMapping("getInhosDayByHos")
	public Page getInhosDayByHos(HosanalysisDto dto) {
		List<HosanalysisVo> list = hosanalysisBlh.getInhosDayByHos(dto);
		
		Page page = new Page();
		page.setData(list);
		page.setCount(String.valueOf(list.size()));
		return page;

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	@RequestMapping("getInhosDayByMonth")
	@ResponseBody
	public List<HosanalysisVo> getInhosDayByMonth() {
		return hosanalysisBlh.getInhosDayByMonth();

	}

	// getPCostByHos
	@RequestMapping("getPCostByHos")
	public Page getPCostByHos() {
		List<HosanalysisVo> list = hosanalysisBlh.getPCostByHos();
		if (list.size() > 10 && list != null) {
			Page page = new Page();
			page.setData(list.subList(0, 9));
			return page;
		} else if (list.size() < 10 && list.size() > 1 && list != null) {
			Page page = new Page();
			page.setData(list);
			return page;
		} else {
			return null;
		}

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	// getPCostByArea
	@RequestMapping("getPCostByArea")
	public Page getPCostByArea() {
		List<HosanalysisVo> list = hosanalysisBlh.getPCostByArea();

		Page page = new Page();
		page.setData(list);
		page.setCount(String.valueOf(list.size()));
		return page;

		// page.setCount(list.size());
		// List<HosanalysisVo> list =
		// hosanalysisBlh.getInhosDayByArea().subList(0, 9);
		// return list;

	}

	// getPCostByYear

	// 门诊
	@RequestMapping("getPPersonCostByYear")
	@ResponseBody
	public List<HosanalysisVo> getPPersonCostByYear() {
		return hosanalysisBlh.getPPersonCostByYear();

	}

	// 住院 getPPersonCostByYearZY
	@RequestMapping("getPPersonCostByYearZY")
	@ResponseBody
	public List<HosanalysisVo> getPPersonCostByYearZY() {
		return hosanalysisBlh.getPPersonCostByYearZY();

	}

	// 住院的
	@RequestMapping("getPCostByYear")
	@ResponseBody
	public List<HosanalysisVo> getPCostByYear() {
		return hosanalysisBlh.getPCostByYear();

	}

	// 门诊的
	@RequestMapping("getPCostByYearMZ")
	@ResponseBody
	public List<HosanalysisVo> getPCostByYearMZ() {
		return hosanalysisBlh.getPCostByYearMZ();

	}

	// 门诊
	@RequestMapping("getPCostByMonth")
	@ResponseBody
	public List<HosanalysisVo> getPCostByMonth() {
		return hosanalysisBlh.getPCostByMonth();

	}

	// 住院!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	@RequestMapping("getPCostByMonthZY")
	@ResponseBody
	public List<HosanalysisVo> getPCostByMonthZY(MedicalAnalysisDto dto) {
		String inflag = dto.getInFlag();
		//System.out.print(inflag);
		return hosanalysisBlh.getPCostByMonthZY(dto);

	}

	// 门诊 医院的次均费用
	@RequestMapping("getAllYYDataMZ")
	@ResponseBody
	public List<HosanalysisVo> getAllYYDataMZ() {
		return hosanalysisBlh.getAllYYDataMZ();

	}
	
	//getAllYYDataMZList  门诊医院次均费用 所有 数据带分页 查询额
	@RequestMapping("getAllYYDataMZList")
	@ResponseBody
	public Page getAllAreaDataMZList(HosanalysisDto dto) {
		hosanalysisBlh.getAllAreaDataMZList(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	//getAllAreaDataMZList  住院统筹区次均费用 所有数据 带分页查询
	@RequestMapping("getAllAreaDataZYList")
	@ResponseBody
	public Page getAllAreaDataZYList(HosanalysisDto dto) {
		hosanalysisBlh.getAllAreaDataZYList(dto);
		Page page = new Page();
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		page.setData(dto.getPageModel().getPageData());
		return page;
	}
	// 住院得top10
	// 
	@RequestMapping("getAllYYDataZR")
	@ResponseBody
	public List<HosanalysisVo> getAllYYDataZR(MedicalAnalysisDto dto) {
		String inFlag =  dto.getInFlag();
		//System.out.println(inFlag);
		return hosanalysisBlh.getAllYYDataZR(dto);

	}
	//住院的医院次均费用 所有数据带分页带查询 getAllYYDataZYList
	// getAllAreaDataMZ  统筹区的所有 数据 带分页 带查询
			@RequestMapping("getAllYYDataZYList")
			@ResponseBody
			public Page getAllYYDataZYList(HosanalysisDto dto) {
				hosanalysisBlh.getAllYYDataZYList(dto);
				Page page = new Page();
				page.setCount(String.valueOf(dto.getPageModel().getTotals()));
				page.setData(dto.getPageModel().getPageData());
				return page;
			}
	//
	// getAllAreaDataMZ  统筹区的top10 数据
	@RequestMapping("getAllAreaDataMZ")
	@ResponseBody
	public List<HosanalysisVo> getAllAreaDataMZ() {
		return hosanalysisBlh.getAllAreaDataMZ();

	}
	// getAllAreaDataMZ  统筹区的所有 数据 带分页 带查询
		@RequestMapping("getAllAreaDataMZList")
		@ResponseBody
		public Page getAllAreaDataMZ(HosanalysisDto dto) {
			hosanalysisBlh.getAllAreaDataMZ(dto);
			Page page = new Page();
			page.setCount(String.valueOf(dto.getPageModel().getTotals()));
			page.setData(dto.getPageModel().getPageData());
			return page;
		}
	// getAllAreaDataZR
	@RequestMapping("getAllAreaDataZR")
	@ResponseBody
	public List<HosanalysisVo> getAllAreaDataZR(MedicalAnalysisDto dto) {
		return hosanalysisBlh.getAllAreaDataZR(dto);

	}*/

