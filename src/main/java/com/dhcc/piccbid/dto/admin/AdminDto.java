package com.dhcc.piccbid.dto.admin;

import java.util.List;
import com.dhcc.piccbid.entity.admin.Admin;
import com.dhcc.piccbid.entity.admin.AdminDict;
import com.dhcc.piccbid.entity.admin.AdminVo;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author heqiang
 * @date 2019-08-02 10:27:08
 * @version V1.0
 */
public class AdminDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Admin admin;
	private List<Admin> admins;
	private AdminVo adminVo;
	private List<AdminVo> adminVos;
	private AdminDict adminDict;
	private List<AdminDict> adminDicts;
	private String keyword;
	private String dictName;
	private String roleId;
	private String inFlag;
	private String handdingName;
	private String number;
	
	
	
	

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the handdingName
	 */
	public String getHanddingName() {
		return handdingName;
	}

	/**
	 * @param handdingName the handdingName to set
	 */
	public void setHanddingName(String handdingName) {
		this.handdingName = handdingName;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}
	
	

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getInFlag() {
		return inFlag;
	}

	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public AdminVo getAdminVo() {
		return adminVo;
	}

	public void setAdminVo(AdminVo adminVo) {
		this.adminVo = adminVo;
	}

	public List<AdminVo> getAdminVos() {
		return adminVos;
	}

	public void setAdminVos(List<AdminVo> adminVos) {
		this.adminVos = adminVos;
	}


	public AdminDict getAdminDict() {
		return adminDict;
	}

	public void setAdminDict(AdminDict adminDict) {
		this.adminDict = adminDict;
	}

	public List<AdminDict> getAdminDicts() {
		return adminDicts;
	}

	public void setAdminDicts(List<AdminDict> adminDicts) {
		this.adminDicts = adminDicts;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
