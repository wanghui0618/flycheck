package com.dhcc.piccbid.service.sametimeHospital;

import java.util.List;
import java.util.Map;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.sametimeHospital.SametimeHospitalDto;
import com.dhcc.piccbid.entity.sametimeHospital.SametimeHospital;

public interface SametimeHospitalService {
	
	public List<Map<String, Object>> getTotalNumberOfCasesAndTotalAmount(SametimeHospitalDto dto);
	
	public PageModel getSametimeHospital (SametimeHospitalDto dto);
	
	public PageModel getSametimeHospitalbyhisidTable(SametimeHospitalDto dto);
	
	public List<SametimeHospital> exportD(SametimeHospitalDto dto);

}
