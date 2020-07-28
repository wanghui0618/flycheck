package com.dhcc.piccbid.web.rest.dictdiag;

import com.dhcc.piccbid.blh.dictdiag.DictdiagBlh;
import com.dhcc.piccbid.dto.dictdiag.DictdiagDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
/**
 *
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zjx
 * @date 2019-04-30 10:13:57
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/dictdiag/dictdiag")
public class DictdiagRest {

	@Resource
	private DictdiagBlh dictdiagBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) DictdiagDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(DictdiagDto dto) {
		dictdiagBlh.list(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="listSecondVo",consumes="application/json")
	public Page listVoRest(@RequestBody(required=false) DictdiagDto dto) {
		return this.listVo(dto);
	}
	@RequestMapping("listSecondVo")
	public Page listVo(DictdiagDto dto) {
		dictdiagBlh.listSecondVo(dto);
		Page page = new Page();
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public DictdiagDto saveRest(@RequestBody DictdiagDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public DictdiagDto save(DictdiagDto dto) {
		dictdiagBlh.save(dto);
		return dto;
	}

	@PostMapping("saveFirst")
	public DictdiagDto saveFirst(DictdiagDto dto) {
		dictdiagBlh.saveFirst(dto);
		return dto;
	}

	@PostMapping("saveSecond")
	public DictdiagDto saveSecond(DictdiagDto dto) {
		dictdiagBlh.saveSecond(dto);
		return dto;
	}

	@PostMapping(value="delete",consumes="application/json")
	public DictdiagDto deleteRest(@RequestBody DictdiagDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public DictdiagDto delete(DictdiagDto dto) {
		dictdiagBlh.delete(dto);
		return dto;
	}

	@PostMapping(value="findById",consumes="application/json")
	public DictdiagDto findByIdRest(@RequestBody DictdiagDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public DictdiagDto findById(DictdiagDto dto) {
		dictdiagBlh.findById(dto);
		return dto;
	}
	@GetMapping(value="ztreeDiag", consumes="application/json")
	public String ztreeDiagRest(@RequestBody(required=false) DictdiagDto dto) {
		return this.ztreeDiag(dto);
	}
	@RequestMapping("ztreeDiag")
	//返回组装好的layui
	public String ztreeDiag(DictdiagDto dto) {
		return dictdiagBlh.ztreeDiag(dto);
	}
	@PostMapping("saveDiag")
	public String saveDiag(DictdiagDto dto){
		String string =dictdiagBlh.saveDiag(dto);
		return string;
	}
	@PostMapping("deleteDiag")
	public DictdiagDto deleteDiag(DictdiagDto dto){
		dictdiagBlh.deleteDiag(dto);
		return dto;
	}
	@PostMapping("updateDiag")
	public String updateDiag(DictdiagDto dto){
	String string=dictdiagBlh.updateDiag(dto);
		return string;
	}

}
