package com.dhcc.piccbid.web.rest.flyScreenMainDiagnosis;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyScreenMainDiagnosis.FlyScreenMainDiagnosisBlh;
import com.dhcc.piccbid.dto.flyScreenMainDiagnosis.FlyScreenMainDiagnosisDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-24 17:15:53
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyScreenMainDiagnosis/flyScreenMainDiagnosis")
public class FlyScreenMainDiagnosisRest {

	@Resource
	private FlyScreenMainDiagnosisBlh flyScreenMainDiagnosisBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) FlyScreenMainDiagnosisDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(FlyScreenMainDiagnosisDto dto) {
		flyScreenMainDiagnosisBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyScreenMainDiagnosisDto saveRest(@RequestBody FlyScreenMainDiagnosisDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyScreenMainDiagnosisDto save(FlyScreenMainDiagnosisDto dto) {
		flyScreenMainDiagnosisBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyScreenMainDiagnosisDto deleteRest(@RequestBody FlyScreenMainDiagnosisDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyScreenMainDiagnosisDto delete(FlyScreenMainDiagnosisDto dto) {
		flyScreenMainDiagnosisBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyScreenMainDiagnosisDto findByIdRest(@RequestBody FlyScreenMainDiagnosisDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyScreenMainDiagnosisDto findById(FlyScreenMainDiagnosisDto dto) {
		flyScreenMainDiagnosisBlh.findById(dto);
		return dto;
	}
}
