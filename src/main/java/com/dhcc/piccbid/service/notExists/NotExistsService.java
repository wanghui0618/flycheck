package com.dhcc.piccbid.service.notExists;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.PersonalInformationInquiryDto;
import com.dhcc.piccbid.dto.notExists.NotExistsDto;
import com.dhcc.piccbid.dto.physicalExaminationAdmission.PhysicalExaminationAdmissionDto;
import com.dhcc.piccbid.entity.notExists.NotExists;
import com.dhcc.piccbid.entity.notExists.NotExistsDetail;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author wanghui
 * @date 2019-11-23 13:31:41
 * @version V1.0
 */
public interface NotExistsService extends BaseService<NotExistsDetail, String> {

	/*PageModel list(NotExistsDto dto);

	

	PageModel countabnormalHospitalStay(NotExistsDto dto);
	

	
	PageModel searchDetail(NotExistsDto dto);*/
	
	//项目查询
	PageModel notExists(NotExistsDto dto);
	
	//项目条件查询
	PageModel notExists1(NotExistsDto dto);
	
	//明细
	PageModel countabnormalHospitalStay1(NotExistsDto dto);
	
	//互斥项目查询
	PageModel searchMutex(NotExistsDto dto);
	
	//互斥项目查询展示表
	PageModel searchMutex1(NotExistsDto dto);
	
	//导出
	SXSSFWorkbook exportExcelToSelf(HttpServletRequest request,NotExistsDto dto);
	
	//项目查询系统导出
	XSSFWorkbook exportExcel();
}
