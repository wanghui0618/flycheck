package com.dhcc.piccbid.entity.home;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Regionalfunds {
    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    @Column(name="CITYNAME")
    private String cityName;
    private String handdingInsName;
    @Column(name="ORG_NAME")
    private String orgName;
    @Column(name="PLACE")
    private String place;
    @Column(name="FUNDS")
    private String funds;
    @Column(name="FUNDSPROPORTION")
    private float fundsProportion;


    public String getHanddingInsName() {
        return handdingInsName;
    }

    public void setHanddingInsName(String handdingInsName) {
        this.handdingInsName = handdingInsName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFunds() {
        return funds;
    }

    public void setFunds(String funds) {
        this.funds = funds;
    }

    public float getFundsProportion() {
        return fundsProportion;
    }

    public void setFundsProportion(float fundsProportion) {
        this.fundsProportion = fundsProportion;
    }
}
