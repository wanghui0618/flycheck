package com.dhcc.piccbid.entity.flyTreatmentFeeCount;

import javax.persistence.*;

@Entity
public class FlyTreatmentFeeCount {
    @Id
    private  String id;

    private String item;
    private String frequency ;
    private String year;
    private String month;
    private String belong;
    private String sum;

    public FlyTreatmentFeeCount() {
    }

    public FlyTreatmentFeeCount(String id, String item, String frequency, String year, String month, String belong, String sum) {
        this.id = id;
        this.item = item;
        this.frequency = frequency;
        this.year = year;
        this.month = month;
        this.belong = belong;
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
