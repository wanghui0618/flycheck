package com.dhcc.piccbid.service.abnormalHospitalStay;

import java.util.List;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;

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
public interface AbnormalHospitalStayService extends BaseService<AbnormalHospitalStay, String> {

	PageModel list(AbnormalHospitalStayDto dto);

	PageModel abnormalHospitalStay(AbnormalHospitalStayDto dto);

	PageModel countabnormalHospitalStay(AbnormalHospitalStayDto dto);

	SXSSFWorkbook exportExcelToSelf(AbnormalHospitalStayDto dto);

	PageModel detaileTable(AbnormalHospitalStayDto dto);

}
