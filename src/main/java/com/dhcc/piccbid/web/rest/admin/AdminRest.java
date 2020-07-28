package com.dhcc.piccbid.web.rest.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.admin.AdminBlh;
import com.dhcc.piccbid.dto.admin.AdminDto;
//import com.dhcc.piccbid.dto.medical.MedicalDto;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-02 10:27:08
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/admin/admin")
public class AdminRest {

	@Resource
	private AdminBlh adminBlh;
	
//	@PostMapping(value = "listNumber", consumes = "application/json")
//	public PageModel listNumberRest(@RequestBody(required = false) MedicalDto dto) {
//		return this.listNumberRest(dto);
//	}
//
//	@RequestMapping("listNumber")
//	@ResponseBody
//	public Page listNumber(AdminDto dto) {
//		adminBlh.listNumber(dto);
//		Page page = new Page();
//		page.setData(dto.getPageModel().getPageData());
//		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
//		return page;
//	}
	
	@RequestMapping("IntegrityToday")
	public Page IntegrityToday(AdminDto dto) {
		adminBlh.IntegrityToday(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("TodayNumber")
	public Page TodayNumber(AdminDto dto) {
		adminBlh.TodayNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("YearData")
	public Page YearData(AdminDto dto) {
		adminBlh.YearData(dto);
		Page page = new Page();
		if(dto.getId()==22) {
			page.setCode(22);
		}else {
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		}
		return page;
	}
	
	@RequestMapping("InhosNumber")
	public Page InhosNumber(AdminDto dto) {
		adminBlh.InhosNumber(dto);
		Page page = new Page();
		if(dto.getId()==22) {
			page.setCode(22);
		}else {
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		}
		return page;
	}
	
	@RequestMapping("AreaNumber")
	public Page AreaNumber(AdminDto dto) {
		adminBlh.AreaNumber(dto);
		Page page = new Page();
		if(dto.getId()==22) {
			page.setCode(22);
		}else {
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		}
		return page;
	}
	
	@RequestMapping("QualityToday")
	public Page QualityToday(AdminDto dto) {
		adminBlh.QualityToday(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("RuleNumber")
	@ResponseBody
	public Page RuleNumber(AdminDto dto) {
		adminBlh.RuleNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("MonitorNumber")
	@ResponseBody
	public Page MonitorNumber(AdminDto dto) {
		adminBlh.MonitorNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("ProvinceNumber")
	@ResponseBody
	public Page ProvinceNumber(AdminDto dto) {
		adminBlh.ProvinceNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("TcNumber")
	@ResponseBody
	public Page TcNumber(AdminDto dto) {
		adminBlh.TcNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("AverageNumber")
	@ResponseBody
	public Page AverageNumber(AdminDto dto) {
		adminBlh.AverageNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	@RequestMapping("TcNumberDate")
	public List<superviseRuleIndex> TcNumberDate() {
		// TODO Auto-generated method stub
		return adminBlh.TcNumberDate();
	}
	
	
	@RequestMapping("OutDate")
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return adminBlh.OutDate(dto);
	}
	
	//查询城市字典
	@RequestMapping("findCityDict")
	public List<AdminVo> findCityDict(AdminDto dto) {
		return adminBlh.findCityDict(dto);
	}
	
	//查询机构字典
	@RequestMapping("findCityOrgDict")
	public List<AdminVo> findCityOrgDict(AdminDto dto) {
		return adminBlh.findCityOrgDict(dto);
	}
	
	//查询城市字典
	@RequestMapping("findCityNameDict")
	public List<AdminVo> findCityNameDict(AdminDto dto) {
		return adminBlh.findCityNameDict(dto);
	}
	
	//查询机构字典
	@RequestMapping("findOrgNameDict")
	public List<AdminVo> findOrgNameDict(AdminDto dto) {
		return adminBlh.findOrgNameDict(dto);
	}
	
	@RequestMapping("medicalNameDict")
	public List<AdminVo> medicalNameDict(AdminDto dto) {
		return adminBlh.medicalNameDict(dto);
	}
	//分页查询字典
	@RequestMapping("findDict")
	public Map<String, Object> findDict(AdminDto dto) {
		return adminBlh.findDict(dto);
	}
	
	@RequestMapping("getName")
	public superviseRuleIndex getName() {
		return adminBlh.getName();
	}
	



	

}
