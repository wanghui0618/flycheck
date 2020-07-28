package com.dhcc.piccbid.blh.indicator;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.indicator.DiseasesAnalysisDto;
import com.dhcc.piccbid.entity.indicator.DiseasesAnalysis;
import com.dhcc.piccbid.service.indicator.DiseasesAnalysisService;

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
public class DiseasesAnalysisBlh extends BaseAbstractBlh<DiseasesAnalysisDto> {

	private static Log logger = LogFactory.getLog(DiseasesAnalysisBlh.class);

	@Resource
	private DiseasesAnalysisService diseasesAnalysisService;

	public DiseasesAnalysisBlh() {
		logger.debug("DiseasesAnalysisBlh Init");
	}

	/**
	 *
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	/**
	 *
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		diseasesAnalysisService.save(dto.getDiseasesAnalysis());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
//		diseasesAnalysisService.delete(dto.getDiseasesAnalysis().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 *
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *
	 */
	public void findById(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);

		//调用对应的service查询某个实体的方法
//		DiseasesAnalysis entity = diseasesAnalysisService.findOne(dto.getDiseasesAnalysis().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
//		dto.setDiseasesAnalysis(entity);
	}


	public void listVo(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.listVo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}


	public void listVoTest(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.listVoTest(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void hopLevel(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.hopLevel(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void handdInfo(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.handdInfo(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	public void diseases(BaseAbstractDto basedto) {
		DiseasesAnalysisDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=diseasesAnalysisService.diseases(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
}
