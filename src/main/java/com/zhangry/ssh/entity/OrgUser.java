/*
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/19
 */
package com.zhangry.ssh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 组织用户实体
 * @author Liupj
 * @date 2017/1/19.
 */
@Entity
@Table(name = "T_BASE_ORG_USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "ORG_ID"})})
public class OrgUser implements Serializable {
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, length = 32)
    private String id;

    /*是否为兼职用户（0-否；1-是）*/
    @Column(name = "IS_ALIASUSER", nullable = false, precision = 1)
    private Integer isAliasuser;

    /*用户实体*/
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    @JsonBackReference
    private User user;

    /*部门实体*/
    @ManyToOne
    @JoinColumn(name = "ORG_ID", nullable = false)
    @JsonBackReference
    private Org org;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsAliasuser() {
        return isAliasuser;
    }

    public void setIsAliasuser(Integer isAliasuser) {
        this.isAliasuser = isAliasuser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }
}
