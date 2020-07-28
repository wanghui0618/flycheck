package com.dhcc.piccbid.web.rest.flyTreatmentFeeCount;

import javax.annotation.Resource;

import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.flyTreatmentFeeCount.FlyTreatmentFeeCountBlh;
import com.dhcc.piccbid.dto.flyTreatmentFeeCount.FlyTreatmentFeeCountDto;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author zfm
 * @date 2019-10-17 22:02:46
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/flyTreatmentFeeCount/flyTreatmentFeeCount")
public class FlyTreatmentFeeCountRest {

	@Resource
	private FlyTreatmentFeeCountBlh flyTreatmentFeeCountBlh;


	@RequestMapping("list")
	public Page list(FlyTreatmentFeeCountDto dto) {
	    Page page=new Page();
		flyTreatmentFeeCountBlh.list(dto);
		page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

    @RequestMapping("ultrasoundlist")
    public Page ultrasoundlist(FlyTreatmentFeeCountDto dto) {
        Page page = new Page();
        flyTreatmentFeeCountBlh.ultrasoundlist(dto);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
	@PostMapping(value="save",consumes="application/json")
	public FlyTreatmentFeeCountDto saveRest(@RequestBody FlyTreatmentFeeCountDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public FlyTreatmentFeeCountDto save(FlyTreatmentFeeCountDto dto) {
		flyTreatmentFeeCountBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public FlyTreatmentFeeCountDto deleteRest(@RequestBody FlyTreatmentFeeCountDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public FlyTreatmentFeeCountDto delete(FlyTreatmentFeeCountDto dto) {
		flyTreatmentFeeCountBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public FlyTreatmentFeeCountDto findByIdRest(@RequestBody FlyTreatmentFeeCountDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public FlyTreatmentFeeCountDto findById(FlyTreatmentFeeCountDto dto) {
		flyTreatmentFeeCountBlh.findById(dto);
		return dto;
	}
}
