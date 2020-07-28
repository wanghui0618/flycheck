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
import com.dhcc.piccbid.service.admin.BigdataService;

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
public class BigdataBlh extends BaseAbstractBlh<AdminDto> {

	private static Log logger = LogFactory.getLog(BigdataBlh.class);

	@Resource
	private BigdataService bigdataService;

	public BigdataBlh() {
		logger.debug("AdminBlh Init");
	}
	
	public void AverageNumber(BaseAbstractDto basedto) {
		AdminDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=bigdataService.AverageNumber(dto);
		dto.setPageModel(pageModel);
	}
	
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return bigdataService.OutDate(dto);
	}
	
	public superviseRuleIndex getName(){
		return bigdataService.getName();
	}
	
	

}
