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
import com.dhcc.piccbid.blh.admin.BigdataBlh;
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
@RequestMapping("/dhccApi/bigdata/bigdata")
public class BigdataRest {

	@Resource
	private BigdataBlh bigdataBlh;
	
	
	@RequestMapping("AverageNumber")
	@ResponseBody
	public Page AverageNumber(AdminDto dto) {
		bigdataBlh.AverageNumber(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}
	
	
	@RequestMapping("OutDate")
	public List<superviseRuleIndex> OutDate(AdminDto dto) {
		// TODO Auto-generated method stub
		return bigdataBlh.OutDate(dto);
	}
	
	@RequestMapping("getName")
	public superviseRuleIndex getName() {
		return bigdataBlh.getName();
	}
	



	

}
