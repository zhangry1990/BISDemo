/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/24
 */
package com.zhangry.ssh.entity;

import com.thinvent.data.hibernate.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 资源实体，包括菜单资源和按钮级权限资源
 * @author Liupj
 * @date 2017/1/24.
 */
@Entity(name = "T_BASE_RESOURCE")
public class Resource extends BaseEntity {

    //资源名称
    @Column(name = "RESOURCE_NAME", nullable = false)
    private String resourceName;

    //资源URI
    @Column(name = "RESOURCE_URI")
    private String resourceUri;

    //父级资源
    @OneToOne
    @JoinColumn(name = "PARENT_ID")
    private Resource pResource;

    //资源编码
    @Column(name = "RESOURCE_CODE")
    private String resourceCode;

    //启用状态(0: 禁用；1：启用)
    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Integer enabled = 1;

    //资源类别（0: 菜单；1：按钮级权限）
    @Column(name = "TYPE", nullable = false, precision = 1)
    private Integer type;

    //项目编号(基础信息：UC)
    @Column(name = "APP_NO", nullable = false)
    private String appNo;

    //排序号
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    /* default constructor */
    public Resource() {}

    public Resource(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Resource getpResource() {
        return pResource;
    }

    public void setpResource(Resource pResource) {
        this.pResource = pResource;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    @Override
    public String toString() {
        return "Resource {" +
                "id=" + id + '\'' +
                "resourceName='" + resourceName + '\'' +
                ", resourceUri='" + resourceUri + '\'' +
                ", pResourceId=" + pResource +
                ", seq=" + seq +
                ", resourceCode='" + resourceCode + '\'' +
                ", enabled=" + enabled +
                ", type=" + type +
                "} ";
    }
}
