package com.dhcc.piccbid.entity.treenode;

import java.io.Serializable;
import java.util.List;

public class BaseTreeNode implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String parentId;
    private String parentLeaf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
