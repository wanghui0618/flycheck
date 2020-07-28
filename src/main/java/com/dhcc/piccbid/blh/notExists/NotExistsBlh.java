package com.dhcc.piccbid.blh.notExists;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.dto.notExists.NotExistsDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.notExists.NotExists;
import com.dhcc.piccbid.service.notExists.NotExistsService;

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
public class NotExistsBlh extends BaseAbstractBlh<NotExistsDto> {

	private static Log logger = LogFactory.getLog(NotExistsBlh.class);

	@Resource
	private NotExistsService notExistsService;

	public NotExistsBlh() {
		logger.debug("NotExistsBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	/*public void list(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=notExistsService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	*//**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 *//*
	public void save(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
	//	notExistsService.save(dto.getNotExists());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	*//**
	 * 
	 * 删除
	 * @param basedto
	 *//*
	public void delete(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		notExistsService.delete(dto.getNotExists().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	*//**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 *//*
	public void findById(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		//NotExists entity = notExistsService.findOne(dto.getNotExists().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
	//	dto.setNotExists(entity);
	}

	public void abnormalHospitalStay(NotExistsDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=notExistsService.notExists(dto);
		dto.setPageModel(pageModel);
	}

	public void countabnormalHospitalStay(NotExistsDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		notExistsService.countabnormalHospitalStay(dto);
	}
	
	
	
	 public void searchDetail(BaseAbstractDto basedto) {
		 NotExistsDto dto = super.getExactlyDto(basedto);
	        //调用service查询方法
	        PageModel pageModel=notExistsService.searchDetail(dto);
	        //不要删除这行代码，调用set是为以后 service层要加缓存
	        dto.setPageModel(pageModel);
	    }*/
	
	public void notExists(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=notExistsService.notExists(dto);
		dto.setPageModel(pageModel);
	}
	
	
	public void notExists1(BaseAbstractDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=notExistsService.notExists1(dto);
		dto.setPageModel(pageModel);
	}
	
	
	public void countabnormalHospitalStay1(NotExistsDto basedto) {
		NotExistsDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		notExistsService.countabnormalHospitalStay1(dto);
	}
	
	 public void searchMutex(BaseAbstractDto basedto) {
		 NotExistsDto dto = super.getExactlyDto(basedto);
	        //调用service查询方法
	        PageModel pageModel=notExistsService.searchMutex(dto);
	        //不要删除这行代码，调用set是为以后 service层要加缓存
	        dto.setPageModel(pageModel);
	    }
	 public void searchMutex1(BaseAbstractDto basedto) {
		 NotExistsDto dto = super.getExactlyDto(basedto);
	        //调用service查询方法
	        PageModel pageModel=notExistsService.searchMutex1(dto);
	        //不要删除这行代码，调用set是为以后 service层要加缓存
	        dto.setPageModel(pageModel);
	    }
	 
	// 导出excel
		public SXSSFWorkbook exportExcelToSelf(HttpServletRequest request,NotExistsDto dto) {
			String param = dto.getParam();
			JSONObject json= (JSONObject)JSONObject.parse(param);
			if(json!=null) {
				dto.setDialogType(json.getString("dialogType"));
				dto.setHospType(json.getString("getOrgName"));
				dto.setHospValue(json.getString("orgCode"));
				dto.setDischargeType(json.getString("dischargeType"));
				dto.setDischargeValue(json.getString("dischargeValue"));
				dto.setLimite(json.getString("limite"));
				dto.setMutex(json.getString("mutex"));
				dto.setItemHospType(json.getString("itemHospType"));
				dto.setItemType(json.getString("itemType"));
				dto.setMybbj(json.getString("mybbj"));
				dto.setMybj(json.getString("mybj"));
			}
			return notExistsService.exportExcelToSelf(request,dto);
		}
		
		public XSSFWorkbook exportExcel() {
	        return notExistsService.exportExcel();
	    }
}
