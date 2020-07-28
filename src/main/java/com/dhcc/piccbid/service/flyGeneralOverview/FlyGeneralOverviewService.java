package com.dhcc.piccbid.service.flyGeneralOverview;

import java.util.List;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.flyGeneralOverview.FlyGeneralOverviewDto;
import com.dhcc.piccbid.dto.unreasonableAdmission.UnreasonableAdmissionDto;
import com.dhcc.piccbid.entity.dict.DictRequestVo;
import com.dhcc.piccbid.entity.dict.DictResponseVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlyGeneralOverviewVo;
import com.dhcc.piccbid.entity.flycheckMedical.FlycheckMedical;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author liufaxing
 * @date 2019-11-24 12:40:05
 * @version V1.0
 */
public interface FlyGeneralOverviewService extends BaseService<FlycheckMedical, String> {

	PageModel list(FlyGeneralOverviewDto dto);

	SXSSFWorkbook getData(FlyGeneralOverviewDto dto);

	PageModel FlyGeneralOverviewDtoFind(FlyGeneralOverviewDto dto);
	
	PageModel FlyGeneralOverviewDtoFind1(FlyGeneralOverviewDto dto);
	
	PageModel FlyGeneralOverviewDtoFind2(FlyGeneralOverviewDto dto);
	
	PageModel FlyGeneralOverviewDtoFind3(FlyGeneralOverviewDto dto);
	
	PageModel FlyGeneralOverviewDtoFind4(FlyGeneralOverviewDto dto);
	
    PageModel FlyGeneralOverviewDtoFind5(FlyGeneralOverviewDto dto);
	
	PageModel FlyGeneralOverviewDtoFind6(FlyGeneralOverviewDto dto);

	PageModel FindMedicalSumAmount(FlyGeneralOverviewDto dto);

	public List<FlyGeneralOverviewVo> findHospitalName(FlyGeneralOverviewVo flyGeneralOverviewVo);
	
	public List<FlyGeneralOverviewVo> findHospitalId(FlyGeneralOverviewVo flyGeneralOverviewVo);
}
