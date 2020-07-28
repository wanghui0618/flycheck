package com.dhcc.piccbid.entity.dataauthority;

import com.dhcc.piccbid.entity.treenode.BaseTreeNode;

import java.io.Serializable;
import java.util.List;

public class DataAhthorityTreeNode extends BaseTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cityCode;

    private String orgCode;

    private List<DataAhthorityTreeNode> children;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public List<DataAhthorityTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<DataAhthorityTreeNode> children) {
        this.children = children;
    }
}
