package com.dhcc.piccbid.service.hospitalLevelRmacy;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.hospitalLevelRmacy.HospitalLevelRmacyDto;
import com.dhcc.piccbid.entity.hospitalLevelRmacy.HospitalLevelRmacy;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-11-27 10:00:37
 * @version V1.0
 */
public interface HospitalLevelRmacyService extends BaseService<HospitalLevelRmacy, String> {
    /***
     * 限医院等级用药主单
     * @param dto
     * @return
     */
	PageModel limitedHospitalLevelZd(HospitalLevelRmacyDto dto);
	
	/***
     * 限医院等级用药明细
     * @param dto
     * @return
     */
	PageModel limitedHospitalLevelMx(HospitalLevelRmacyDto dto);
	/**
	 * 体检式入院明细
	 * @param dto
	 * @return
	 */
	PageModel physicalExaminationMx(HospitalLevelRmacyDto dto);
	
	/***
     * 主单统计
     * @param dto
     * @return
     */
	PageModel countMedical(HospitalLevelRmacyDto dto);
	
	/***
     * 明细统计
     * @param dto
     * @return
     */
	PageModel countMx(HospitalLevelRmacyDto dto);
	
	/***
	 * 导出
	 * @param dto
	 * @return
	 */
	SXSSFWorkbook exportExcel(HospitalLevelRmacyDto dto);
	
}
