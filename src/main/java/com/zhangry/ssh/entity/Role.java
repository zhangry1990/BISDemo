/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/16
 */
package com.zhangry.ssh.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.zhangry.data.hibernate.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体
 * @author Liupj
 * @date 2017/1/16.
 */
@Entity
@Table(name = "T_BASE_ROLE")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //角色名称
    @Column(name = "ROLE_NAME", nullable = false)
    private String roleName;

    //备注
    @Column(name = "REMARKS")
    private String remarks;

    /*启用状态（0-禁用；1-启用）*/
    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Integer enabled = 1;

    //序号
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    //角色关联用户列表
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_BASE_USER_ROLE",
            joinColumns = {@JoinColumn(name="ROLE_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name="USER_ID", nullable = false)})
    @Where(clause = "ENABLED = 1 and DELETED_FLAG = 0")
    @JsonManagedReference
    private Set<User> users = new HashSet<User>(0);

    //角色关联资源列表
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "T_BASE_ROLE_RESOURCE",
            joinColumns = {@JoinColumn(name="ROLE_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name="RESOURCE_ID", nullable = false)})
    @Where(clause = "ENABLED = 1 and DELETED_FLAG = 0")
    @JsonManagedReference
    private Set<Resource> resources = new HashSet<Resource>(0);

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", seq=" + seq +
                '}';
    }
}
