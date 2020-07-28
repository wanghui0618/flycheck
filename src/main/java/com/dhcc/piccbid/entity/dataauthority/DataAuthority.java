package com.dhcc.piccbid.entity.dataauthority;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "T_PICCBID_DATAAUTHORITY")
public class DataAuthority implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "CITY_CODE")
    private String cityCode;
    @Column(name = "ORG_CODE")
    private String orgCode;
    @Column(name = "PARENT_ID")
    private String parentId;
    @Column(name = "PARENT_LEAF")
    private String parentLeaf;
    @Column(name = "TYPE")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
