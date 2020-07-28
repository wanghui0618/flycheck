package com.dhcc.piccbid.entity.dictdiag;

import java.util.List;

public class TreeNode {

    private String id;

    private String parentId;

    private String name;

    private String parentLeaf;

    private String diagName;

    private String diagDesc;

    private String diagCode;

    private String parentIndex;

    private List<TreeNode> children;

    private String roleName;

    private String roleId;

    private String menuName;

    private String menuCode;

    private String menuUrl;
    
    private String owner;
    /**
     * "1"表示有下一级，"0"表示没有下一级
     */
    private String hasChildren;

    /**
     * 前端界面判断是否可以进行新增显示
     */
   private String isAdd;

    private String onclickBef;

    private String onclickAft;

    private String checked;

    
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

	public String getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(String isAdd) {
        this.isAdd = isAdd;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
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

    public String getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(String hasChildren) {
        this.hasChildren = hasChildren;
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

    /**
     * @return the menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName the menuName to set
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
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


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public String getParentLeaf() {
        return parentLeaf;
    }

    public void setParentLeaf(String parentLeaf) {
        this.parentLeaf = parentLeaf;
    }

    public String getDiagName() {
        return diagName;
    }

    public void setDiagName(String diagName) {
        this.diagName = diagName;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(String parentIndex) {
        this.parentIndex = parentIndex;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                ", parentLeaf='" + parentLeaf + '\'' +
                ", diagName='" + diagName + '\'' +
                ", diagDesc='" + diagDesc + '\'' +
                ", diagCode='" + diagCode + '\'' +
                ", parentIndex='" + parentIndex + '\'' +
                ", children=" + children +
                '}';
    }


}
