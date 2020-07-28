package com.dhcc.piccbid.blh.dataComparison;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.framework.app.blh.BaseAbstractBlh;
import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.PageModel;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;
import com.dhcc.piccbid.dto.abnormalHospitalStay.AbnormalHospitalStayDto;
import com.dhcc.piccbid.dto.dataComparison.DataComparisonDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayexcle;
import com.dhcc.piccbid.entity.dataComparison.DataComparison;
import com.dhcc.piccbid.service.dataComparison.DataComparisonService;

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
public class DataComparisonBlh extends BaseAbstractBlh<DataComparisonDto> {

	private static Log logger = LogFactory.getLog(DataComparisonBlh.class);

	@Resource
	private DataComparisonService dataComparisonService;

	public DataComparisonBlh() {
		logger.debug("DataComparisonBlh Init");
	}

	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * 
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.list(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * 
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用对应的service保存方法
		dataComparisonService.save(dto.getDataComparison());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 删除
	 * 
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用对应的service删除方法
		dataComparisonService.delete(dto.getDataComparison().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}

	/**
	 * 
	 * 根据ID查询实体的方法
	 * 
	 * @param: basedto
	 * 
	 */
	public void findById(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);

		// 调用对应的service查询某个实体的方法
		DataComparison entity = dataComparisonService.findOne(dto.getDataComparison().getId());
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setDataComparison(entity);
	}

	public void table1(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table1(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table2(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table2(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table3(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table3(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table45(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table45(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table6(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table6(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table7(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table7(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table8_1(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table8_1(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	public void table8_2(BaseAbstractDto basedto) {
		DataComparisonDto dto = super.getExactlyDto(basedto);
		// 调用service查询方法
		PageModel pageModel = dataComparisonService.table8_2(dto);
		// 不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}

	// 导出excel
	public SXSSFWorkbook exportExcelToSelf(DataComparisonDto dto) {
		String param = dto.getParam();
		JSONObject json= (JSONObject)JSONObject.parse(param);
		if(json!=null) {
			dto.setDiagType(json.getString("diagType"));
			dto.setPersonType(json.getString("personType"));
			dto.setOrgCode1(json.getString("orgCode1"));
			dto.setOrgCode2(json.getString("orgCode2"));
			dto.setBalanceDate(json.getString("balanceDate"));
			dto.setYear(json.getString("year"));
		}
		return dataComparisonService.exportExcelToSelf(dto);
	}
}
