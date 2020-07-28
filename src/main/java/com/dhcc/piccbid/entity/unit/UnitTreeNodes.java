package com.dhcc.piccbid.entity.unit;

import java.util.Date;
import java.util.List;

public class UnitTreeNodes {
    private String id;
    private String name;
    private String unitName;
    private String unitId;
    private String unitCode;
    private String unitAddress;
    private String phone;
    private String concat;
    private String parentId;
    private String parentLeaf;
    private String webUrl;
    private String email;
    private Date addDate;
    private String isUnit;
    private String checked;
    private String cityCode;
    private String isHospital;
    private String isOauth;
    private List<UnitTreeNodes> children;

    public String getIsOauth() {
        return isOauth;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public void setIsOauth(String isOauth) {
        this.isOauth = isOauth;
    }

    public String getIsHospital() {
        return isHospital;
    }

    public void setIsHospital(String isHospital) {
        this.isHospital = isHospital;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsUnit() {
        return isUnit;
    }

    public void setIsUnit(String isUnit) {
        this.isUnit = isUnit;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public List<UnitTreeNodes> getChildren() {
        return children;
    }

    public void setChildren(List<UnitTreeNodes> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentLeaf() {
        return parentLeaf;
    }

    public void setParentLeaf(String parentLeaf) {
        this.parentLeaf = parentLeaf;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
