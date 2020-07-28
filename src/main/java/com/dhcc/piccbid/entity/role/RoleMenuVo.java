/**
 * 
 */
package com.dhcc.piccbid.entity.role;

import java.io.Serializable;
/**
 * 
* <p>标题: RoleMenuVo.java</p>
* <p>业务描述:DRGs绩效评价与控费系统</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2016</p>
* @author 李群
* @date 2016年6月1日
* @version V3.0
 */
public class RoleMenuVo implements Serializable {
     /**
		 * 字段: 字段名称
		 * 
		 * @Fields serialVersionUID : TODO
		 */
		private static final long serialVersionUID = 1L;

		private String roleCode;
		private String menuId;
		private String roleId;

		/**
		 * @return the roleId
		 */
		public String getRoleId() {
			return roleId;
		}

		/**
		 * @param roleId the roleId to set
		 */
		public void setRoleId(String roleId) {
			this.roleId = roleId;
		}

		/**
		 * @return roleCode
		 */
		public String getRoleCode() {
			return roleCode;
		}

		/**
		 * @param roleCode
		 *            roleCode
		 */
		public void setRoleCode(String roleCode) {
			this.roleCode = roleCode;
		}

		/**
		 * @return menuId
		 */
		public String getMenuId() {
			return menuId;
		}

		/**
		 * @param menuId
		 *            menuId
		 */
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}

	
}
