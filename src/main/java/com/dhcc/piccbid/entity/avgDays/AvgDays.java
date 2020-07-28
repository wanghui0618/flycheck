package com.dhcc.piccbid.entity.avgDays;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class AvgDays {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    @Column(name="ORG_NAME")
    private String orgName;
    @Column(name="COUNT_PERSON")
    private int  countPerson;
    @Column(name="STAY_LENGTH")
    private int stayLength;
    @Column(name="STAY_AVG")
    private int stayAvg;
    private String year;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getCountPerson() {
        return countPerson;
    }

    public void setCountPerson(int countPerson) {
        this.countPerson = countPerson;
    }

    public int getStayLength() {
        return stayLength;
    }

    public void setStayLength(int stayLength) {
        this.stayLength = stayLength;
    }

    public int getStayAvg() {
        return stayAvg;
    }

    public void setStayAvg(int stayAvg) {
        this.stayAvg = stayAvg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
