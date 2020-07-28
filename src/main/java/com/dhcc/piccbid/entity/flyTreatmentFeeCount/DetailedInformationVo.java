package com.dhcc.piccbid.entity.flyTreatmentFeeCount;

import com.dhcc.framework.common.PageModel;

import java.io.Serializable;

public class DetailedInformationVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String belong;
    private  String hisid;
    private  String itemId;
    private  String itemName;
    private  String itemIdHosp;
    private  String itemNameHosp;
    private  String unitPrice;
    private String num;
    private String cost;
    private String pCategory;
    private String pType;

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getHisid() {
        return hisid;
    }

    public void setHisid(String hisid) {
        this.hisid = hisid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemIdHosp() {
        return itemIdHosp;
    }

    public void setItemIdHosp(String itemIdHosp) {
        this.itemIdHosp = itemIdHosp;
    }

    public String getItemNameHosp() {
        return itemNameHosp;
    }

    public void setItemNameHosp(String itemNameHosp) {
        this.itemNameHosp = itemNameHosp;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getpCategory() {
        return pCategory;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }
}
