package com.dhcc.piccbid.web.rest.user;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.blh.user.UserBlh;
import com.dhcc.piccbid.dto.user.UserDto;
import com.dhcc.piccbid.entity.page.Page;

/**
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @version V1.0
 * @date 2019-01-09 12:41:56
 */
@RestController
@RequestMapping("/dhccApi/user/user")
public class UserRest {

    @Resource
    private UserBlh userBlh;

    @PostMapping(value = "list", consumes = "application/json")
    public Page listRest(@RequestBody(required = false) UserDto dto) {
        return this.list(dto);
    }

    @RequestMapping("list")
    public Page list(UserDto dto) {
        userBlh.list(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }


    @PostMapping(value = "userTree", consumes = "application/json")
    public String userTreeRest(@RequestBody(required = false) UserDto dto) {
        return this.userTree(dto);
    }

    @RequestMapping("userTree")
    public String userTree(UserDto dto) {
        String jsonString = userBlh.userTree(dto);
        return jsonString;
    }

    /**
     * 新增用户
     * @param dto
     * @return
     */

    @PostMapping(value = "save", consumes = "application/json")
    public UserDto saveRest(@RequestBody UserDto dto) {
        return this.findById(dto);
    }

    @PostMapping("save")
    public UserDto save(UserDto dto) {
        userBlh.save(dto);
        return dto;
    }

    /**
     * 用户菜单授权保存
     * @param dto
     * @return
     */

    @PostMapping(value = "saveAutho", consumes = "application/json")
    public UserDto saveAuthoRest(@RequestBody UserDto dto) {
        return this.saveAutho(dto);
    }

    @PostMapping("saveAutho")
    public UserDto saveAutho(UserDto dto) {
        userBlh.saveAutho(dto);
        return dto;
    }

    /**
     * 用户数据授权
     * @param dto
     * @return
     */

    @PostMapping(value = "saveDataAutho", consumes = "application/json")
    public UserDto saveDataAuthoRest(@RequestBody UserDto dto) {
        return this.saveDataAutho(dto);
    }

    @PostMapping("saveDataAutho")
    public UserDto saveDataAutho(UserDto dto) {
        userBlh.saveDataAutho(dto);
        return dto;
    }

    /**
     * 用户按钮授权
     * @param dto
     * @return
     */
 @PostMapping(value = "saveButtonAutho", consumes = "application/json")
    public UserDto saveButtonAuthoRest(@RequestBody UserDto dto) {
        return this.saveButtonAutho(dto);
    }

    @PostMapping("saveButtonAutho")
    public UserDto saveButtonAutho(UserDto dto) {
        userBlh.saveButtonAutho(dto);
        return dto;
    }

    @PostMapping("auditing")
    public UserDto auditing(UserDto dto) {
        userBlh.auditing(dto);
        return dto;
    }

    @PostMapping("reauditing")
    public UserDto reauditing(UserDto dto) {
        userBlh.reauditing(dto);
        return dto;
    }

    @PostMapping("delete")
    public UserDto delete(UserDto dto) {
        userBlh.delete(dto);
        return dto;
    }

    @PostMapping(value = "findById", consumes = "application/json")
    public UserDto findByIdRest(@RequestBody UserDto dto) {
        return this.findById(dto);
    }

    @PostMapping("findById")
    public UserDto findById(UserDto dto) {
        userBlh.findById(dto);
        return dto;
    }

    @GetMapping(value = "login")
    public UserDto login(UserDto dto) {
        userBlh.login(dto);
        return dto;
    }
    
    
    @PostMapping(value = "listNumber", consumes = "application/json")
	public PageModel listNumberRest(@RequestBody(required = false) UserDto dto) {
		return this.listNumberRest(dto);
	}
    
    @RequestMapping("listNumber")
    public Page listNumber(UserDto dto) {
        userBlh.listNumber(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    
    @GetMapping(value = "latestLogin")
    public Page latestLogin(UserDto dto) {
        userBlh.latestLogin(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @GetMapping(value = "register")
    public UserDto register(UserDto dto) {
        userBlh.register(dto);
        return dto;
    }

    @GetMapping(value = "forget")
    public UserDto forget(UserDto dto) {
        userBlh.forget(dto);
        return dto;
    }

    @GetMapping(value = "setNewPassword")
    public UserDto setNewPassword(UserDto dto) {
        userBlh.setNewPassword(dto);
        return dto;
    }

    @GetMapping(value = "changePassword")
    public UserDto changePassword(UserDto dto) {
        userBlh.changePassword(dto);
        return dto;
    }

   /* @GetMapping(value = "updatePhoto")
    public UserDto updatePhoto(UserDto dto) {
        userBlh.updatePhoto(dto);
        return dto;
    }*/
    
    @RequestMapping(value="/updatePhoto",produces = {"application/json"})
	public void updatePhoto(String id,String photo) {
		userBlh.updatePhoto(id,photo);
	}
    
    @GetMapping(value = "updateNew")
    public UserDto updateNew(UserDto dto) {
        userBlh.updateNew(dto);
        return dto;
    }

    @GetMapping(value = "logout")
    public UserDto logout(UserDto dto) {
        userBlh.logout(dto);
        return dto;
    }

    @PostMapping(value = "listUserVo", consumes = "application/json")
    public Page listUserVoRest(@RequestBody(required = false) UserDto dto) {
        return this.listUserVo(dto);
    }

    @RequestMapping("listUserVo")
    public Page listUserVo(UserDto dto) {
        userBlh.listUserVo(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }
    
    @PostMapping(value = "query", consumes = "application/json")
    public Page queryRest(@RequestBody(required = false) UserDto dto) {
        return this.query(dto);
    }

    @RequestMapping("query")
    public Page query(UserDto dto) {
        userBlh.query(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @PostMapping(value = "listTreeTable", consumes = "application/json")
    public Page listTreeTableRest(@RequestBody(required = false) UserDto dto) {
        return this.listTreeTable(dto);
    }

    @RequestMapping("listTreeTable")
    public Page listTreeTable(UserDto dto) {
        userBlh.listTreeTable(dto);
        Page page = new Page();
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
        return page;
    }

    @RequestMapping("listNewLogin")
    public Page listNewLogin(UserDto dto,String num){
    	Page page = new Page();
    	userBlh.listNewLogin(dto,num);
        page.setData(dto.getPageModel().getPageData());
        page.setCount(String.valueOf(dto.getPageModel().getTotals()));
    	return page;
    }
    /**
     * 用户的角色变更后，审核通过后，将用户之前角色的权限删除改为之后角色的权限
     */
    @RequestMapping("roleChange")
    public UserDto roleChange(UserDto dto) {
        userBlh.roleChange(dto);
        return dto;
    }

    /**
     * 批量创建医院用户
     todo:先创建医院角色，在给角色授权，给用户绑定该角色，创建用户,刷新存储过程（用户只有一个医院的数据权限）
     * @param dto
     * @return
     */

    @PostMapping(value = "creatHospUsers", consumes = "application/json")
    public String lcreatHospUsersRest() {
        return this.creatHospUsers();
    }

    @RequestMapping("creatHospUsers")
    public String creatHospUsers() {
        userBlh.creatHospUsers();
        return "success";
    }

}
