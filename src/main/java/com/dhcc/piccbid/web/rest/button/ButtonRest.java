package com.dhcc.piccbid.web.rest.button;

import javax.annotation.Resource;

import com.dhcc.piccbid.blh.button.ButtonBlh;
import com.dhcc.piccbid.dto.button.ButtonDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author ll
 * @date 2019-07-10 09:17:54
 * @version V1.0
 */
@RestController
@RequestMapping("/dhccApi/button/button")
public class ButtonRest {

	@Resource
	private ButtonBlh buttonBlh;

	@PostMapping(value="list",consumes="application/json")
	public Page listRest(@RequestBody(required=false) ButtonDto dto) {
		return this.list(dto);
	}
	@RequestMapping("list")
	public Page list(ButtonDto dto) {
	    Page page=new Page();
		buttonBlh.list(dto);
		page.setData(dto.getPageModel().getPageData());
		page.setCount(String.valueOf(dto.getPageModel().getTotals()));
		return page;
	}

	@PostMapping(value="save",consumes="application/json")
	public ButtonDto saveRest(@RequestBody ButtonDto dto) {
		return this.save(dto);
	}
	@PostMapping("save")
	public ButtonDto save(ButtonDto dto) {
		buttonBlh.save(dto);
		return dto;
	}
	
	@PostMapping(value="delete",consumes="application/json")
	public ButtonDto deleteRest(@RequestBody ButtonDto dto) {
		return this.delete(dto);
	}
	@PostMapping("delete")
	public ButtonDto delete(ButtonDto dto) {
		buttonBlh.delete(dto);
		return dto;
	}
	
	@PostMapping(value="findById",consumes="application/json")
	public ButtonDto findByIdRest(@RequestBody ButtonDto dto) {
		return this.findById(dto);
	}
	@PostMapping("findById")
	public ButtonDto findById(ButtonDto dto) {
		buttonBlh.findById(dto);
		return dto;
	}
    /**
     * 按钮管理树
     */
    @RequestMapping("listAllTree")
    public String listAllTree(ButtonDto dto) {
        return buttonBlh.listAllTree(dto);
    }

    @PostMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody(required = false) ButtonDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(ButtonDto dto) {
        buttonBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    /**
     * 获取用户的已授权的按钮
     */
    @RequestMapping("getButtonAuthoByUserId")
    public String getButtonAuthoByUserId(ButtonDto dto) {
        return buttonBlh.getButtonAuthoByUserId(dto);
    }

}
