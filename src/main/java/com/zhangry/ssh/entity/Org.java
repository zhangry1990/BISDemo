/*
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/19
 */
package com.zhangry.ssh.entity;

import com.thinvent.data.hibernate.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 组织实体
 * @author Liupj
 * @date 2017/1/19.
 */
@Entity
@Table(name = "T_BASE_ORG")
public class Org extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //组织名称(全称)
    @Column(name = "ORG_FULL_NAME", nullable = false)
    private String orgFullName;

    //组织简称
    @Column(name = "ORG_SHORT_NAME")
    private String orgShortName;

    //组织编号
    @Column(name = "ORG_NO")
    private String orgNo;

    //父级组织
    @OneToOne
    @JoinColumn(name = "PARENT_ORG_ID")
    private Org pOrg;

    //联系电话1
    @Column(name = "PHONE_NUM1")
    private String phoneNum1;

    //联系电话2
    @Column(name = "PHONE_NUM2")
    private String phoneNum2;

    //组织描述
    @Column(name = "ORG_DESCRIPTION")
    private String orgDescription;

    //地址
    @Column(name = "ADDRESS")
    private String address;

    //成立日期
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ESTABLISH_DATE")
    private Date establishDate;

    //排序号
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    //组织关联的组织用户列表
    @OneToMany(mappedBy = "org")
    private Set<OrgUser> orgUsers = new HashSet<>(0);

    /* default constructor */
    public Org() {}

    /* constructor with primary key */
    public Org(String id) {
        this.id = id;
    }

    public String getOrgFullName() {
        return orgFullName;
    }

    public void setOrgFullName(String orgFullName) {
        this.orgFullName = orgFullName;
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public Org getpOrg() {
        return pOrg;
    }

    public void setpOrg(Org pOrg) {
        this.pOrg = pOrg;
    }

    public String getPhoneNum1() {
        return phoneNum1;
    }

    public void setPhoneNum1(String phoneNum1) {
        this.phoneNum1 = phoneNum1;
    }

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Set<OrgUser> getOrgUsers() {
        return orgUsers;
    }

    public void setOrgUsers(Set<OrgUser> orgUsers) {
        this.orgUsers = orgUsers;
    }

    @Override
    public String toString() {
        return "Org{" +
                "orgFullName='" + orgFullName + '\'' +
                ", orgNo='" + orgNo + '\'' +
                ", pOrg=" + pOrg +
                ", phoneNum1='" + phoneNum1 + '\'' +
                ", phoneNum2='" + phoneNum2 + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                "} ";
    }
}
