package com.dhcc.piccbid.entity.home;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MonitorPolygonalchart {
    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private int month;
    private String insurePersonType1;
    private String insurePersonType2;
    private String crowdType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getInsurePersonType1() {
        return insurePersonType1;
    }

    public void setInsurePersonType1(String insurePersonType1) {
        this.insurePersonType1 = insurePersonType1;
    }

    public String getInsurePersonType2() {
        return insurePersonType2;
    }

    public void setInsurePersonType2(String insurePersonType2) {
        this.insurePersonType2 = insurePersonType2;
    }

    public String getCrowdType() {
        return crowdType;
    }

    public void setCrowdType(String crowdType) {
        this.crowdType = crowdType;
    }
}
