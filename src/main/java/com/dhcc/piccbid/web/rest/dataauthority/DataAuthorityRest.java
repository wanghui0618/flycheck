package com.dhcc.piccbid.web.rest.dataauthority;

import com.dhcc.piccbid.blh.dataauthority.DataAuthorityBlh;
import com.dhcc.piccbid.dto.dataauthority.DataAuthorityDto;
import com.dhcc.piccbid.entity.page.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author tangjianbo
 * @version V1.0
 * @date 2019-06-25 09:36:34
 */
@RestController
@RequestMapping("/dhccApi/dataauthority/dataauthority")
public class DataAuthorityRest {

    @Resource
    private DataAuthorityBlh dataAuthorityBlh;

    @PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) DataAuthorityDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(DataAuthorityDto dto) {
        dataAuthorityBlh.list(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @PostMapping(value = "save", consumes = "application/json")
    public DataAuthorityDto saveRest(@RequestBody DataAuthorityDto dto) {
        return this.save(dto);
    }
    @PostMapping("save")
    public DataAuthorityDto save(DataAuthorityDto dto) {
        dataAuthorityBlh.save(dto);
        return dto;
    }

    @PostMapping(value = "delete", consumes = "application/json")
    public DataAuthorityDto deleteRest(@RequestBody DataAuthorityDto dto) {
        return this.delete(dto);
    }

    @PostMapping("delete")
    public DataAuthorityDto delete(DataAuthorityDto dto) {
        dataAuthorityBlh.delete(dto);
        return dto;
    }

    @PostMapping(value = "findById", consumes = "application/json")
    public DataAuthorityDto findByIdRest(@RequestBody DataAuthorityDto dto) {
        return this.findById(dto);
    }

    @PostMapping("findById")
    public DataAuthorityDto findById(DataAuthorityDto dto) {
        dataAuthorityBlh.findById(dto);
        return dto;
    }

    @RequestMapping(value = "listAllTree", consumes = "application/json")
    public String listAllTreeRest(@RequestBody DataAuthorityDto dto) {
        return this.listAllTree(dto);
    }

    @RequestMapping("listAllTree")
    public String listAllTree(DataAuthorityDto dto) {
        String jsonString = dataAuthorityBlh.listAllTree(dto);
        return jsonString;
    }

    @RequestMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody DataAuthorityDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(DataAuthorityDto dto) {
        dataAuthorityBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        //page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    @RequestMapping(value = "findCity", consumes = "application/json")
    public DataAuthorityDto findCityRest(@RequestBody DataAuthorityDto dto) {
        return this.findCity(dto);
    }

    @RequestMapping("findCity")
    public DataAuthorityDto findCity(DataAuthorityDto dto) {
        dataAuthorityBlh.findCity(dto);
        return dto;
    }

    @RequestMapping(value = "findOrg", consumes = "application/json")
    public DataAuthorityDto findOrgRest(@RequestBody DataAuthorityDto dto) {
        return this.findOrg(dto);
    }

    @RequestMapping("findOrg")
    public DataAuthorityDto findOrg(DataAuthorityDto dto) {
        dataAuthorityBlh.findOrg(dto);
        return dto;
    }

}
