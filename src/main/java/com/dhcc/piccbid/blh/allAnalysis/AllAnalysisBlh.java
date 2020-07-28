package com.dhcc.piccbid.blh.allAnalysis;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.admin.AdminDto;
import com.dhcc.piccbid.dto.allAnalysis.AllAnalysisDto;
import com.dhcc.piccbid.dto.menu.MenuDto;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis;
import com.dhcc.piccbid.entity.allAnalysis.AllAnalysis2;
import com.dhcc.piccbid.service.allAnalysis.AllAnalysisService;

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
public class AllAnalysisBlh extends BaseAbstractBlh<AllAnalysisDto> {

	private static Log logger = LogFactory.getLog(AllAnalysisBlh.class);

	@Resource
	private AllAnalysisService allAnalysisService;

	public AllAnalysisBlh() {
		logger.debug("AllAnalysisBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=allAnalysisService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	public void search1(BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel = allAnalysisService.search1(dto);
		List<AllAnalysis> returnList=(List<AllAnalysis>) pageModel.getPageData();
		//不要删除这行代码，调用set是为以后 service层要加缓存
		pageModel.setPageData(returnList);
		dto.setPageModel(pageModel);
	}
	public void search2(HttpServletRequest request,BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		String gid = request.getParameter("gid");
		//调用service查询方法
		PageModel pageModel = allAnalysisService.search2(gid,dto);
		List<AllAnalysis2> returnList=(List<AllAnalysis2>) pageModel.getPageData();
		//不要删除这行代码，调用set是为以后 service层要加缓存
		pageModel.setPageData(returnList);
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		allAnalysisService.save(dto.getAllAnalysis());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		allAnalysisService.delete(dto.getAllAnalysis().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		AllAnalysisDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		AllAnalysis entity = allAnalysisService.findOne(dto.getAllAnalysis().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setAllAnalysis(entity);
	}
}
