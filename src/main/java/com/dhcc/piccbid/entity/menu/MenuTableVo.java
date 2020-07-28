/**
 * 
 */
package com.dhcc.piccbid.entity.menu;

import java.io.Serializable;
/**
 * 
* <p>标题: menuMenuVo.java</p>
* <p>业务描述:DRGs绩效评价与控费系统</p>
* <p>公司:东华软件股份公司</p>
* <p>版权:dhcc2016</p>
* @author 李群
* @date 2016年6月1日
* @version V3.0
 */
public class MenuTableVo implements Serializable {
     /**
		 * 字段: 字段名称
		 * 
		 * @Fields serialVersionUID : TODO
		 */
		private static final long serialVersionUID = 1L;

		private String menuCode;
		private String tableId;
		private String menuId;
		private String menuUrl;
		private String onclickBef;
		private String onclickAft;
		
		
		public String getOnclickBef() {
			return onclickBef;
		}
		public void setOnclickBef(String onclickBef) {
			this.onclickBef = onclickBef;
		}
		public String getOnclickAft() {
			return onclickAft;
		}
		public void setOnclickAft(String onclickAft) {
			this.onclickAft = onclickAft;
		}
		/**
		 * @return the menuCode
		 */
		public String getMenuCode() {
			return menuCode;
		}
		/**
		 * @param menuCode the menuCode to set
		 */
		public void setMenuCode(String menuCode) {
			this.menuCode = menuCode;
		}
		/**
		 * @return the tableId
		 */
		public String getTableId() {
			return tableId;
		}
		/**
		 * @param tableId the tableId to set
		 */
		public void setTableId(String tableId) {
			this.tableId = tableId;
		}
		/**
		 * @return the menuId
		 */
		public String getMenuId() {
			return menuId;
		}
		/**
		 * @param menuId the menuId to set
		 */
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
		/**
		 * @return the menuUrl
		 */
		public String getMenuUrl() {
			return menuUrl;
		}
		/**
		 * @param menuUrl the menuUrl to set
		 */
		public void setMenuUrl(String menuUrl) {
			this.menuUrl = menuUrl;
		}
		

	
}
