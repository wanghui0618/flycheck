package com.dhcc.piccbid.dto.role;

import java.util.List;
import com.dhcc.piccbid.entity.role.Role;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

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
public class RoleDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Role role;
	private List<Role> roles;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
