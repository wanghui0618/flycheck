package com.dhcc.piccbid.entity.drugsAndInspectionStatistics;

public class DrugsAndInspectionStatisticsVO {
    private String itemCodeIns;
    private String itemCodeHos;
    private String  frequency;
    private String totalNumber;
    private String totalCost;
    private String itemNameIns;
    private String itemNameHos;

    public String getItemCodeHos() {
        return itemCodeHos;
    }

    public void setItemCodeHos(String itemCodeHos) {
        this.itemCodeHos = itemCodeHos;
    }

    public String getItemNameHos() {
        return itemNameHos;
    }

    public void setItemNameHos(String itemNameHos) {
        this.itemNameHos = itemNameHos;
    }

    public String getItemNameIns() {
        return itemNameIns;
    }

    public void setItemNameIns(String itemNameIns) {
        this.itemNameIns = itemNameIns;
    }

    public String getItemCodeIns() {
        return itemCodeIns;
    }

    public void setItemCodeIns(String itemCodeIns) {
        this.itemCodeIns = itemCodeIns;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
}
