package com.dhcc.piccbid.entity.procedure;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Killer {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;
    private String sid;
    private String serial;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
