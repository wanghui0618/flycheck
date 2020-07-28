package com.dhcc.piccbid.web.rest.flyMedicalInhos;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyMedicalInhos.FlyMedicalInhosBlh;
import com.dhcc.piccbid.dto.flyMedicalInhos.FlyMedicalInhosDto;
import com.dhcc.piccbid.entity.page.Page;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author xukeyong
 * @date 2019-10-31 11:40:47
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyMedicalInhos/flyMedicalInhos")
public class FlyMedicalInhosRest {

	@Resource
	private FlyMedicalInhosBlh flyMedicalInhosBlh;

	@PostMapping(value="list",consumes="application/json")
	public PageModel listRest(@RequestBody(required=false) FlyMedicalInhosDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public PageModel list(FlyMedicalInhosDto dto) {
		flyMedicalInhosBlh.list(dto);
		return dto.getPageModel();
	}
	
	/*
	 * 根据年份进行查找
	 */
	@RequestMapping("findByYear")
	public Page findByYear(FlyMedicalInhosDto dto) {
		
		flyMedicalInhosBlh.findByYear(dto);
		
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		
		return page;
		
	}
	
	/*
	 * 根据月份进行查找
	 */
	@RequestMapping("findByMonth")
	public Page findByMonth(FlyMedicalInhosDto dto) {
		
		flyMedicalInhosBlh.findByMonth(dto);
		
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		
		return page;
		
	}

	@PostMapping(value="save",consumes="application/json")
	public FlyMedicalInhosDto saveRest(@RequestBody FlyMedicalInhosDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyMedicalInhosDto save(FlyMedicalInhosDto dto) {
		flyMedicalInhosBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyMedicalInhosDto deleteRest(@RequestBody FlyMedicalInhosDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyMedicalInhosDto delete(FlyMedicalInhosDto dto) {
		flyMedicalInhosBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyMedicalInhosDto findByIdRest(@RequestBody FlyMedicalInhosDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyMedicalInhosDto findById(FlyMedicalInhosDto dto) {
		flyMedicalInhosBlh.findById(dto);
		return dto;
	}
}
