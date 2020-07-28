package com.dhcc.piccbid.entity.procedure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Procedure {
    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private String pdName;
    private String pdModule;
    private String pdXm;
    private String pdCode;
    private String pdDesc;
    private String type;
    private String status;
    private String pdTable;
    private boolean success;
    private String runningType;
    private String time;
    private  String flag;
    private  String year;
    private  String hospNum;

    public String getHospNum() {
        return hospNum;
    }

    public void setHospNum(String hospNum) {
        this.hospNum = hospNum;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRunningType() {
        return runningType;
    }

    public void setRunningType(String runningType) {
        this.runningType = runningType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPdTable() {
        return pdTable;
    }

    public void setPdTable(String pdTable) {
        this.pdTable = pdTable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPdName() {
        return pdName;
    }

    public void setPdName(String pdName) {
        this.pdName = pdName;
    }

    public String getPdModule() {
        return pdModule;
    }

    public void setPdModule(String pdModule) {
        this.pdModule = pdModule;
    }

    public String getPdXm() {
        return pdXm;
    }

    public void setPdXm(String pdXm) {
        this.pdXm = pdXm;
    }

    public String getPdCode() {
        return pdCode;
    }

    public void setPdCode(String pdCode) {
        this.pdCode = pdCode;
    }

    public String getPdDesc() {
        return pdDesc;
    }

    public void setPdDesc(String pdDesc) {
        this.pdDesc = pdDesc;
    }
}
