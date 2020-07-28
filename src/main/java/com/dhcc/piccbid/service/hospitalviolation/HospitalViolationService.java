package com.dhcc.piccbid.service.hospitalviolation;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.hospitalviolation.HospitalViolationDto;
import com.dhcc.piccbid.entity.hospitalviolation.HospitalViolation;
import com.dhcc.piccbid.entity.violationdetails.SysVerifyVo;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author jpp
 * @date 2019-05-27 17:22:38
 * @version V1.0
 */
public interface HospitalViolationService extends BaseService<HospitalViolation, String> {


	PageModel listVo(HospitalViolationDto dto);

	PageModel list(HospitalViolationDto dto);

	List<SysVerifyVo> violationSpread(HospitalViolationDto dto);

	XSSFWorkbook exportExcelToSelf(String status,String handdingInsName);
	
    PageModel hopVio(HospitalViolationDto dto);
    
    PageModel medVio(HospitalViolationDto dto);

}
