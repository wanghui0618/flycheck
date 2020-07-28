package com.dhcc.piccbid.web.rest.flyMedicalDetail;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyMedicalDetail.FlyMedicalDetailBlh;
import com.dhcc.piccbid.dto.flyMedicalDetail.FlyMedicalDetailDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-10-15 14:48:34
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyMedicalDetail/flyMedicalDetail")
public class FlyMedicalDetailRest {

	@Resource
	private FlyMedicalDetailBlh flyMedicalDetailBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyMedicalDetailDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyMedicalDetailDto dto) {
		flyMedicalDetailBlh.list(dto);
		return dto.getPageModel();
	}
	
	//检查费收费金额次数统计
	@RequestMapping("listVo")
	public Page listVo(FlyMedicalDetailDto dto) {
		flyMedicalDetailBlh.listVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyMedicalDetailDto saveRest(@RequestBody FlyMedicalDetailDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyMedicalDetailDto save(FlyMedicalDetailDto dto) {
		flyMedicalDetailBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyMedicalDetailDto deleteRest(@RequestBody FlyMedicalDetailDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyMedicalDetailDto delete(FlyMedicalDetailDto dto) {
		flyMedicalDetailBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyMedicalDetailDto findByIdRest(@RequestBody FlyMedicalDetailDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyMedicalDetailDto findById(FlyMedicalDetailDto dto) {
		flyMedicalDetailBlh.findById(dto);
		return dto;
	}
}
