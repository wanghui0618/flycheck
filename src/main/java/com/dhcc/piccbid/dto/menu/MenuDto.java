package com.dhcc.piccbid.dto.menu;

import java.util.HashMap;
import java.util.List;

import com.dhcc.piccbid.entity.button.Button;
import com.dhcc.piccbid.entity.menu.Menu;
import com.dhcc.framework.transmission.dto.BaseAbstractDto;

/**
 * 
 * 描述: TODO<br/>
 * 公司: 东华软件股份公司<br/>
 * 版权: dhcc2017<br/>
 *
 * @author WJL
 * @date 2019-06-12 09:51:27
 * @version V1.0
 */
public class MenuDto extends BaseAbstractDto {

	private static final long serialVersionUID = 1L;

	private Menu menu;
	private List<Menu> menus;
	private String inFlag;
	private HashMap<String,String> data;
	private Button button;

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    /**
	 * @return the data
	 */
	public HashMap<String, String> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(HashMap<String, String> data) {
		this.data = data;
	}

	/**
	 * @return the inFlag
	 */
	public String getInFlag() {
		return inFlag;
	}

	/**
	 * @param inFlag the inFlag to set
	 */
	public void setInFlag(String inFlag) {
		this.inFlag = inFlag;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
}
