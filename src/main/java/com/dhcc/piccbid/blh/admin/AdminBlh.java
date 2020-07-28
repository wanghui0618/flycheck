package com.dhcc.piccbid.blh.admin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.framework.web.context.WebContextHolder;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.AdminDict;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;
import com.dhcc.piccbid.entity.user.User;
import com.dhcc.piccbid.service.admin.AdminService;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author CodeGenUtils
 * @version V1.0
 */
@Component
public class AdminBlh extends BaseAbstractBlh<AdminDto> {

	private static Log logger = LogFactory.getLog(AdminBlh.class);

	@Resource
	private AdminService adminService;

	public AdminBlh() {
		logger.debug("AdminBlh Init");
	}
	
	public void listNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.listNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	
	public void IntegrityToday(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.IntegrityToday(dto);
		List<Admin> returnList=(List<Admin>) pageModel.getPageData();
		if(returnList!=null) {
			for (Admin admin : returnList) {
				admin.setEffectNumber(admin.getTotalNumber()-admin.getUneffectNumber());
				admin.setEffective(bs(admin.getEffectNumber(),admin.getTotalNumber()));
				
			}
		}
		pageModel.setPageData(returnList);
		dto.setPageModel(pageModel);
	}
	
	public void TodayNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.TodayNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void YearData(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		if(user!=null) {
			String roleId = user.getRoleId();
			dto.setRoleId(roleId);
		}else if(user==null) {
			dto.setId(22);
			return;
		}
		PageModel pageModel=adminService.YearData(dto);
		dto.setPageModel(pageModel);
	}
	
	public void InhosNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		if(user!=null) {
			String roleId = user.getRoleId();
			dto.setRoleId(roleId);
		}else if(user==null) {
			dto.setId(22);
			return;
		}
		PageModel pageModel=adminService.InhosNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void AreaNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		User user = (User)WebContextHolder.getContext().getRequest().getSession().getAttribute("user");
		if(user!=null) {
			String roleId = user.getRoleId();
			dto.setRoleId(roleId);
		}else if(user==null) {
			dto.setId(22);
			return;
		}
		PageModel pageModel=adminService.AreaNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void QualityToday(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.QualityToday(dto);
		List<Admin> returnList=(List<Admin>) pageModel.getPageData();
		if(returnList!=null) {
			for (Admin admin : returnList) {
				admin.setUneffectNumber(admin.getTotalNumber()-admin.getEffectNumber());
			}
		}
		pageModel.setPageData(returnList);
		dto.setPageModel(pageModel);
	}
	
	public void RuleNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.RuleNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void MonitorNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.MonitorNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void ProvinceNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.ProvinceNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void TcNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.TcNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public void AverageNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=adminService.AverageNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public List<superviseRuleIndex> TcNumberDate() {
		// TODO Auto-generated method stub
		return adminService.TcNumberDate();
	}
	
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return adminService.OutDate(dto);
	}
	
	/**
	 * 查询城市字典
	 * @return
	 */
	public List<AdminVo> findCityDict(AdminDto dto) {
		return adminService.findCityDict(dto);
	}
	
	/**
	 * 查询机构字典
	 * @return
	 */
	public List<AdminVo> findCityOrgDict(AdminDto dto) {
		return adminService.findCityOrgDict(dto);
	}
	
	/**
	 * 查询城市字典
	 * @return
	 */
	public List<AdminVo> findCityNameDict(AdminDto dto) {
		return adminService.findCityNameDict(dto);
	}
	
	/**
	 * 查询机构字典
	 * @return
	 */
	public List<AdminVo> findOrgNameDict(AdminDto dto) {
		return adminService.findOrgNameDict(dto);
	}
	
	public List<AdminVo> medicalNameDict(AdminDto dto) {
		return adminService.medicalNameDict(dto);
	}
	
	
	private int bs(int a ,int b){
	    return (int)((new BigDecimal((float) a / b).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
	}
	
	public Map<String, Object> findDict(BaseAbstractDto basedto) {
		Map<String, Object> result = new HashMap<String, Object>();
		AdminDto dto = super.getExactlyDto(basedto);
		if (dto.getPageModel() == null) {
			dto.setPageModel(new PageModel());
		}
		PageModel pageModel=adminService.findDict(dto);
		List<AdminDict> list=(List<AdminDict>) pageModel.getPageData();
	    for (AdminDict adminDict : list) {
	    	adminDict.setName(adminDict.getName().trim());
		}
		result.put("total", pageModel.getTotals());  
        result.put("rows", list); 
        return result;
	}
	
	public superviseRuleIndex getName(){
		return adminService.getName();
	}
	
	

}
