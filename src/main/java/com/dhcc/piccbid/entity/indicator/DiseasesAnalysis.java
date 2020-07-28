package com.dhcc.piccbid.entity.indicator;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DiseasesAnalysis {
    @Id
    @Column(name="ID", unique=true, nullable=false, length=32)
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;

    private String handdingInsName;
    private String orgName;
    private String mainDiagName;
    private String condition;
    private Double totalCost;
    private Double allTotalCost;
    private String totalProportion;
    private String total;
    private String projectType;
    private Double totalTimes;
    private String timeCost;
    private String orgCode;
    private String year;
    private String orgLevel;
    private String type;
    private Double eachPersonTime;
    private Double eachTimeCost;

    public Double getEachTimeCost() {
        return eachTimeCost;
    }

    public void setEachTimeCost(Double eachTimeCost) {
        this.eachTimeCost = eachTimeCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public Double getEachPersonTime() {
        return eachPersonTime;
    }

    public void setEachPersonTime(Double eachPersonTime) {
        this.eachPersonTime = eachPersonTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(String timeCost) {
        this.timeCost = timeCost;
    }

    public void setTotalTimes(Double totalTimes) {
        this.totalTimes = totalTimes;
    }


    public Double getTotalTimes() {
        return totalTimes;
    }



    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMainDiagName() {
        return mainDiagName;
    }

    public void setMainDiagName(String mainDiagName) {
        this.mainDiagName = mainDiagName;
    }

    public Double getAllTotalCost() {
        return allTotalCost;
    }

    public void setAllTotalCost(Double allTotalCost) {
        this.allTotalCost = allTotalCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getTotalProportion() {
        return totalProportion;
    }

    public void setTotalProportion(String totalProportion) {
        this.totalProportion = totalProportion;
    }
}
