package com.dhcc.piccbid.entity.button;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the T_PICCBID_MENU database table.
 *
 */
@Entity
@Table(name="T_PICCBID_BUTTON")
public class Button implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="ID",unique=true, nullable=false, length=32)
    @GenericGenerator(name="idGenerator",strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    private String id;

    @Column(name="BUTTON_CODE")
    private String buttonCode;

    @Column(name="BUTTON_NAME")
    private String buttonName;

    @Column(name="PARENT_ID")
    private String parentId;

    @Column(name="PARENT_LEAF")
    private String parentLeaf;

    @Column(name="BUTTON_PAGE_NAME")
    private String buttonPageName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentLeaf() {
        return parentLeaf;
    }

    public void setParentLeaf(String parentLeaf) {
        this.parentLeaf = parentLeaf;
    }

    public String getButtonPageName() {
        return buttonPageName;
    }

    public void setButtonPageName(String buttonPageName) {
        this.buttonPageName = buttonPageName;
    }
}