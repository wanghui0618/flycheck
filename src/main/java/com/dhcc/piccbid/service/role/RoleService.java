package com.dhcc.piccbid.service.role;

import com.dhcc.framework.app.service.common.BaseService;
import com.dhcc.framework.common.PageModel;
import com.dhcc.piccbid.dto.role.RoleDto;
import com.dhcc.piccbid.entity.role.Role;

import java.util.List;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author gzw
 * @date 2019-01-14 15:42:21
 * @version V1.0
 */
public interface RoleService extends BaseService<Role, String> {

	PageModel list(RoleDto dto);
	PageModel search(RoleDto dto);
	String roleList(RoleDto dto);
	String roleAllList(RoleDto dto);
	List<Role> roles(RoleDto dto);
	PageModel listTreeTable(RoleDto dto);
    PageModel listTreeTableNew(RoleDto roleDto);
	void deleteArray(String[] arrays);
	void deleteRoleAuthorityArrays(String[] arrays);
	void deleteRoleAuthority(String id);
    void deleteUserAuthorityByRoleId(String id);
	void deleteUserAuthority(String id);
	int isExist(RoleDto roleDto);
	void saveAutho(RoleDto dto);
	void saveDataAutho(RoleDto dto);
    void saveRoleButtonAutho(RoleDto dto);
}
