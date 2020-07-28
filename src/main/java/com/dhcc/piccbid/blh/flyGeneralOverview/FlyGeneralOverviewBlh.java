package com.dhcc.piccbid.blh.flyGeneralOverview;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.dict.DictDto;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import com.dhcc.piccbid.service.flyGeneralOverview.FlyGeneralOverviewService;

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
public class FlyGeneralOverviewBlh extends BaseAbstractBlh<FlyGeneralOverviewDto> {

	private static Log logger = LogFactory.getLog(FlyGeneralOverviewBlh.class);

	@Resource
	private FlyGeneralOverviewService flyGeneralOverviewService;

	public FlyGeneralOverviewBlh() {
		logger.debug("FlyGeneralOverviewBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=flyGeneralOverviewService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		flyGeneralOverviewService.save(dto.getFlyGeneralOverview());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		flyGeneralOverviewService.delete(dto.getFlyGeneralOverview().getHisid());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		FlycheckMedical entity = flyGeneralOverviewService.findOne(dto.getFlyGeneralOverview().getHisid());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyGeneralOverview(entity);
	}
	
	public void FlyGeneralOverviewDtoFind(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind(dto);
		dto.setPageModel(pageModel);
	}

	public SXSSFWorkbook getData(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		String queryParams=dto.getParam();
		JSONObject json= (JSONObject)JSONObject.parse(queryParams);
		if(json!=null) {
			if(dto.getFlyGeneralOverviewVo()==null) {
				dto.setFlyGeneralOverviewVo(new FlyGeneralOverviewVo());
			}
			dto.getFlyGeneralOverviewVo().setHospitalId(json.getString("flyGeneralOverviewVo.hospitalId"));
			dto.getFlyGeneralOverviewVo().setHospitalName(json.getString("flyGeneralOverviewVo.hospitalName"));
			dto.getFlyGeneralOverviewVo().setVisitingRoute(json.getString("flyGeneralOverviewVo.visitingRoute"));
			dto.getFlyGeneralOverviewVo().setBenefitGroupId(json.getString("flyGeneralOverviewVo.benefitGroupId"));
			dto.getFlyGeneralOverviewVo().setYear(json.getString("flyGeneralOverviewVo.year"));
			dto.getFlyGeneralOverviewVo().setPeopleNumber(json.getString("flyGeneralOverviewVo.peopleNumber"));
			dto.getFlyGeneralOverviewVo().setPeopleNumber2(json.getString("flyGeneralOverviewVo.peopleNumber2"));
			dto.getFlyGeneralOverviewVo().setMedicalTotal(json.getString("flyGeneralOverviewVo.medicalTotal"));
			dto.getFlyGeneralOverviewVo().setMedicalBmi(json.getString("flyGeneralOverviewVo.medicalBmi"));
			dto.getFlyGeneralOverviewVo().setAvgCost(json.getString("flyGeneralOverviewVo.avgCost"));
			dto.getFlyGeneralOverviewVo().setAvgDay(json.getString("flyGeneralOverviewVo.avgDay"));
			dto.getFlyGeneralOverviewVo().setDrugShare(json.getString("flyGeneralOverviewVo.drugShare"));
		}
		return flyGeneralOverviewService.getData(dto);
	}
	
	public void FlyGeneralOverviewDtoFind1(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind1(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind2(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind2(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind3(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind3(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind4(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind4(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind5(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind5(dto);
		dto.setPageModel(pageModel);
	}
	
	public void FlyGeneralOverviewDtoFind6(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FlyGeneralOverviewDtoFind6(dto);
		dto.setPageModel(pageModel);
	}

	public void FindMedicalSumAmount(BaseAbstractDto basedto) {
		FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		PageModel pageModel = flyGeneralOverviewService.FindMedicalSumAmount(dto);
		dto.setPageModel(pageModel);
	}

    public void findHospitalName(BaseAbstractDto basedto) {
		
    	FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		List<FlyGeneralOverviewVo> list = flyGeneralOverviewService.findHospitalName(dto.getFlyGeneralOverviewVo());
		
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyGeneralOverviewVos(list);
	}
    
public void findHospitalId(BaseAbstractDto basedto) {
		
    	FlyGeneralOverviewDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		List<FlyGeneralOverviewVo> list = flyGeneralOverviewService.findHospitalId(dto.getFlyGeneralOverviewVo());
		
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setFlyGeneralOverviewVos(list);
	}
}
