package com.dhcc.piccbid.entity.menu;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_PICCBID_MENU database table.
 * 
 */
@Entity
@Table(name="T_PICCBID_MENU")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID",unique=true, nullable=false, length=32)
	@GenericGenerator(name="idGenerator",strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	private String id;

	@Column(name="MENU_CODE")
	private String menuCode;

	@Column(name="MENU_NAME")
	private String menuName;

	@Column(name="MENU_URL")
	private String menuUrl;

	@Column(name="PARENT_ID")
	private String parentId;

	@Column(name="PARENT_LEAF")
	private String parentLeaf;

	@Column(name="ONCLICK_BEF")
	private String onclickBef;

	@Column(name="ONCLICK_AFT")
	private String onclickAft;
	
	@Column(name="OWNER")
	private String owner;
	
	
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Menu() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentLeaf() {
		return this.parentLeaf;
	}

	public void setParentLeaf(String parentLeaf) {
		this.parentLeaf = parentLeaf;
	}

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
}