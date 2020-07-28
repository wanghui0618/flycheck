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
public interface BigdataService extends BaseService<Admin, String> {

	PageModel AverageNumber(AdminDto dto);
	
	List<superviseRuleIndex> OutDate(AdminDto dto);
	
	superviseRuleIndex  getName();

}
