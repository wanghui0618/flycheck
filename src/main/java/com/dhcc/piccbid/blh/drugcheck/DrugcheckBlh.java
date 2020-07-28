package com.dhcc.piccbid.blh.drugcheck;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.drugcheck.DrugcheckDto;
import com.dhcc.piccbid.service.drugcheck.DrugcheckService;

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
public class DrugcheckBlh extends BaseAbstractBlh<DrugcheckDto> {

	private static Log logger = LogFactory.getLog(DrugcheckBlh.class);

	@Resource
	private DrugcheckService drugcheckService;

	public DrugcheckBlh() {
		logger.debug("DrugcheckBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DrugcheckDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=drugcheckService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
}
