package com.dhcc.piccbid.entity.role;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * <p>标题: Role.java</p>
 * <p>业务描述:Drgs绩效分析及控费系统</p>
 * <p>公司:东华软件股份公司</p>
 * <p>版权:dhcc2016</p>
 *
 * @author 寇祥
 * @version V1.0
 * @date 2016年5月16日
 */
@Entity
@Table(name = "T_PICCBID_ROLE")
public class Role implements Serializable {

    /**
     * 字段:      字段名称
     *
     * @Fields serialVersionUID : TODO
     */
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;


    @Column(name = "ROLE_CODE")
    private String roleCode;


    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column(name = "PARENT_LEAF")
    private String parentLeaf;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "IS_HOSPITAL")
    private String isHospital;


    public String getIsHospital() {
        return isHospital;
    }

    public void setIsHospital(String isHospital) {
        this.isHospital = isHospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getParentLeaf() {
        return parentLeaf;
    }

    public void setParentLeaf(String parentLeaf) {
        this.parentLeaf = parentLeaf;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
