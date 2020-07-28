package com.dhcc.piccbid.blh.abnormalHospitalStay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.dhcc.piccbid.dto.findDict.FindDictDto;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayVo;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStayexcle;
import com.dhcc.piccbid.entity.abnormalHospitalStay.AbnormalHospitalStay;
import com.dhcc.piccbid.entity.findDict.FindDictVo;
import com.dhcc.piccbid.entity.unreasonableAdmission.UnreasonableAdmission;
import com.dhcc.piccbid.service.abnormalHospitalStay.AbnormalHospitalStayService;

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
public class AbnormalHospitalStayBlh extends BaseAbstractBlh<AbnormalHospitalStayDto> {

	private static Log logger = LogFactory.getLog(AbnormalHospitalStayBlh.class);

	@Resource
	private AbnormalHospitalStayService abnormalHospitalStayService;

	public AbnormalHospitalStayBlh() {
		logger.debug("AbnormalHospitalStayBlh Init");
	}
	
	/**
	 * 
	 * 进入某个列表的入口方法（分页查询方法）
	 * @param basedto
	 */
	public void list(BaseAbstractDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		//调用service查询方法
		PageModel pageModel=abnormalHospitalStayService.list(dto);
		//不要删除这行代码，调用set是为以后 service层要加缓存
		dto.setPageModel(pageModel);
	}
	
	/**
	 * 
	 * 保存对象，若无ID则新建对象，若有ID则更新对象
	 * @param basedto
	 */
	public void save(BaseAbstractDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		//调用对应的service保存方法
		abnormalHospitalStayService.save(dto.getAbnormalHospitalStay());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 删除
	 * @param basedto
	 */
	public void delete(BaseAbstractDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		//调用对应的service删除方法
		abnormalHospitalStayService.delete(dto.getAbnormalHospitalStay().getId());
		dto.setOperateSuccess(BaseConstants.OPERATE_SUCCESS);
	}
	
	/**
	 * 
	 * 根据ID查询实体的方法
	 * @param: basedto
	 *  
	 */
	public void findById(BaseAbstractDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		
		//调用对应的service查询某个实体的方法
		AbnormalHospitalStay entity = abnormalHospitalStayService.findOne(dto.getAbnormalHospitalStay().getId());
		//不要删除这行代码，调用set是为以后 service层要加缓存 
		dto.setAbnormalHospitalStay(entity);
	}

	public void abnormalHospitalStay(AbnormalHospitalStayDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		PageModel pageModel=abnormalHospitalStayService.abnormalHospitalStay(dto);
		dto.setPageModel(pageModel);
	}

	public void countabnormalHospitalStay(AbnormalHospitalStayDto basedto) {
		AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
		if(dto.getPageModel()==null) {
			dto.setPageModel(new PageModel());
		}
		abnormalHospitalStayService.countabnormalHospitalStay(dto);
	}

	//导出excel
		public SXSSFWorkbook exportExcelToSelf(AbnormalHospitalStayDto dto) {
			String params = dto.getParams();
			JSONObject json= (JSONObject)JSONObject.parse(params);
			if(json!=null) {
				if(dto.getAbnormalHospitalStayexcle()==null) {
					dto.setAbnormalHospitalStayexcle(new AbnormalHospitalStayexcle());
				}
				dto.getAbnormalHospitalStayexcle().setCode(json.getString("code"));
				
				dto.getAbnormalHospitalStayexcle().setHospitalName(json.getString("hospitalName"));
				dto.getAbnormalHospitalStayexcle().setIndate(json.getString("indate"));
				dto.getAbnormalHospitalStayexcle().setOutdate(json.getString("outdate"));
				dto.getAbnormalHospitalStayexcle().setPaydate(json.getString("paydate"));
				dto.getAbnormalHospitalStayexcle().setZyts(json.getString("zyts"));
				dto.getAbnormalHospitalStayexcle().setAdmissionDiseaseName(json.getString("admissionDiseaseName"));
			}
			return abnormalHospitalStayService.exportExcelToSelf(dto);		
		}

		public void detaileTable(AbnormalHospitalStayDto basedto) {
			AbnormalHospitalStayDto dto = super.getExactlyDto(basedto);
			PageModel pageModel=abnormalHospitalStayService.detaileTable(dto);
			dto.setPageModel(pageModel);
		}
}
