package com.dhcc.piccbid.service.admin;

import java.util.List;
import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.piccbid.entity.admin.superviseRuleIndex;

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
public interface AdminService extends BaseService<Admin, String> {

	/*PageModel list(AdminDto dto);*/
	
	PageModel listNumber(AdminDto dto);
	
	PageModel IntegrityToday(AdminDto dto);
	
	PageModel QualityToday(AdminDto dto);
	
	PageModel TodayNumber(AdminDto dto);
	
	PageModel RuleNumber(AdminDto dto);
	
	PageModel MonitorNumber(AdminDto dto);
	
	PageModel ProvinceNumber(AdminDto dto);
	
	PageModel TcNumber(AdminDto dto);
	
	PageModel YearData(AdminDto dto);
	
	PageModel InhosNumber(AdminDto dto);
	
	PageModel AreaNumber(AdminDto dto);
	
	PageModel AverageNumber(AdminDto dto);
	
	List<superviseRuleIndex> TcNumberDate();
	
	List<superviseRuleIndex> OutDate(AdminDto dto);
	
	List<AdminVo> findCityDict(AdminDto dto);
	
	List<AdminVo> findCityOrgDict(AdminDto dto);
	
	List<AdminVo> findCityNameDict(AdminDto dto);
	
	List<AdminVo> findOrgNameDict(AdminDto dto);
	
	List<AdminVo> medicalNameDict(AdminDto dto);
	
	PageModel findDict(AdminDto dto);
	
	superviseRuleIndex  getName();

}
