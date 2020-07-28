package com.dhcc.piccbid.service.abnormalanesthesia;

import java.util.List;
import java.util.Map;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.abnormalanesthesia.AbnormalAnesthesiaDto;
import com.dhcc.piccbid.entity.abnormalanesthesia.AbnormalAnesthesia;

public interface AbnormalAnesthesiaService {
	
public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(AbnormalAnesthesiaDto dto);
	
	public PageModel getAbnormalAnesthesia (AbnormalAnesthesiaDto dto);
	
	public PageModel getAbnormalAnesthesiabyhisidTable(AbnormalAnesthesiaDto dto);
	
	public List<AbnormalAnesthesia> exportD(AbnormalAnesthesiaDto dto);

}
