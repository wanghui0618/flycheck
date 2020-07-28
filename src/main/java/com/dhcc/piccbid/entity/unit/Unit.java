package com.dhcc.piccbid.entity.unit;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="T_PICCBID_UNIT")
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID", unique=true, nullable=false, length=32)
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;

    @Column(name="PHONE")
    private String phone;

    @Column(name="UNIT_NAME")
    private String unitName;

    @Column(name="UNIT_CODE")
    private String unitCode;

    @Column(name="UNIT_ADDRESS")
    private String unitAddress;

    @Column(name="CONCAT")
    private String concat;

    @Column(name="EMAIL")
    private String email;

    @Column(name="WEB_URL")
    private String webUrl;

    @Column(name="PARENT_ID")
    private String parentId;

    @Column(name="PARENT_LEAF")
    private String parentLeaf;

    @Column(name="IS_UNIT")
    private String isUnit;

    @Column(name="CITY_CODE")
    private String cityCode;

   @Column(name="IS_OAUTH")
    private String isOauth;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="ADD_DATE")
    private Date addDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="UPDATE_DATE")
    private Date updateDate;


    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsUnit() {
        return isUnit;
    }

    public void setIsUnit(String isUnit) {
        this.isUnit = isUnit;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getIsOauth() {
        return isOauth;
    }

    public void setIsOauth(String isOauth) {
        this.isOauth = isOauth;
    }
}
