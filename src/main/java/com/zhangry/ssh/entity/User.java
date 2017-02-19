/*
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/16
 */
package com.zhangry.ssh.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thinvent.common.constant.Constant;
import com.thinvent.common.util.StringUtil;
import com.thinvent.data.hibernate.BaseEntity;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * 用户实体
 * @author Liupj
 * @date 2017/1/16.
 */
@Entity
@Table(name = "T_BASE_USER")
public class User extends BaseEntity  implements UserDetails {
    private static final long serialVersionUID = 1L;

    //用户名
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    //密码
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    //用户全名
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    //用户编号
    @Column(name = "USER_NO")
    private String userNo;

    //年龄
    @Column(name = "AGE")
    private Integer age;

    //性别（0：男；1：女）
    @Column(name = "SEX")
    private Integer sex;

    //民族
    @Column(name = "NATION")
    private String nation;

    //籍贯
    @Column(name = "ORIGIN")
    private String origin;

    //生日
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTHDAY")
    private Date birthday;

    //参加工作时间
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "WORK_DATE")
    private Date workDate;

    //联系电话1
    @Column(name = "PHONE_NUM1")
    private String phoneNum1;

    //联系电话2
    @Column(name = "PHONE_NUM2")
    private String phoneNum2;

    //电子邮箱
    @Column(name = "EMAIL")
    private String email;

    //任职状态(1: 在职；2：离职)
    @Column(name = "JOB_STATUS")
    private Integer jobStatus;

    //缺省选中资源
    @OneToOne
    @JoinColumn(name = "DEFAULT_RESOURCE_ID")
    private Resource defaultResource;

    /*启用状态（0-禁用；1-启用）*/
    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Integer enabled = 1;

    //排序号
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    //用户关联角色列表
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "T_BASE_USER_ROLE",
            joinColumns = {@JoinColumn(name="USER_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name="ROLE_ID", nullable = false)})
    @OrderBy("seq")
    @Where(clause = "ENABLED = 1 and DELETED_FLAG = 0")
    @JsonManagedReference
    private Set<Role> roles = new HashSet<>(0);

    //用户关联的组织用户实体列表
    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<OrgUser> orgUsers = new HashSet<>(0);

    //用户非兼职组织列表
    @Transient
    private Set<Org> orgList = new HashSet<>(0);

    public Set<Org> getOrgList() {
        if(orgList.size() > 0) {
            return orgList;
        }

        if (orgUsers.size() == 0) {
            throw new IllegalStateException(StringUtil.format("user must have at least one org, current userId = {0}", id));
        }

        Org org;
        for (OrgUser item : orgUsers) {
            if (item.getIsAliasuser() == Constant.INT_NO) {
                org = item.getOrg();
                org.setOrgUsers(new HashSet<OrgUser>());

                orgList.add(org);
            }
        }

        return orgList;
    }

    //用户第一个非兼职组织
    @Transient
    private Org org;

    public Org getOrg() {
        if(org != null) {
            return org;
        }

        if (orgList.size() == 0) {
            getOrgList();

            if (orgList.size() == 0) {
                return null;
            }
        }

        org = orgList.iterator().next();
        org.setOrgUsers(new HashSet<OrgUser>());

        return org;
    }

    //用户第一个非兼职组织ID
    @Transient
    private String orgId;

    //用户第一个非兼职组织名称
    @Transient
    private String orgName;

    public String getOrgId() {
        if (org == null) {
            getOrg();
        }

        orgId = (org == null ? null : org.getId());

        return orgId;
    }

    public String getOrgName() {
        if (org == null) {
            getOrg();
        }

        orgName = (org == null ? null : org.getOrgFullName());

        return orgName;
    }

    @Transient
    private List<Map<String, Object>> resources;


    /************************* override UserDetails 方法 ******************************/

    @Transient
    private List<GrantedAuthority> authsList;

    public void setAuthsList(List<GrantedAuthority> authsList) {
        this.authsList = authsList;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authsList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == Constant.ENABLED && deletedFlag.equals(Constant.DELETEDFLAG_NO);
    }

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Resource getDefaultResource() {
        return defaultResource;
    }

    public void setDefaultResource(Resource defaultResource) {
        this.defaultResource = defaultResource;
    }

    public Set<OrgUser> getOrgUsers() {
        return orgUsers;
    }

    public void setOrgUsers(Set<OrgUser> orgUsers) {
        this.orgUsers = orgUsers;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public List<Map<String, Object>> getResources() {
        return resources;
    }

    public void setResources(List<Map<String, Object>> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userNo='" + userNo + '\'' +
                ", age=" + age +
                ", defaultResource=" + defaultResource +
                ", orgList=" + orgList +
                ", org=" + org +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                "} ";
    }


}
