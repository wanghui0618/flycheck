package com.dhcc.piccbid.entity.analysisOfPersonTime;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class AnalysisOfPersonTime {
    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private int month;
    private String condition;
    private  String year;
    private String type;
    private double  totalPerson;
    private double  totalTime;
    private double  eachPersonTime;
    private String orgName;
    private String orgCode;
    private String handdingInsName;
    private String handdingInsCode;

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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTotalPerson() {
        return totalPerson;
    }

    public void setTotalPerson(double totalPerson) {
        this.totalPerson = totalPerson;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public double getEachPersonTime() {
        return eachPersonTime;
    }

    public void setEachPersonTime(double eachPersonTime) {
        this.eachPersonTime = eachPersonTime;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getHanddingInsName() {
        return handdingInsName;
    }

    public void setHanddingInsName(String handdingInsName) {
        this.handdingInsName = handdingInsName;
    }

    public String getHanddingInsCode() {
        return handdingInsCode;
    }

    public void setHanddingInsCode(String handdingInsCode) {
        this.handdingInsCode = handdingInsCode;
    }
}
