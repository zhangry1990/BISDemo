/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/7
 */
package com.zhangry.ssh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 附件记录表
 *
 * @author Liupj
 * @date 2017/2/7.
 */
@Entity
@Table(name = "T_BASE_ATTACHMENT")
public class Attachment extends BaseEntity {

    //附件名称
    @Column(name = "NAME", nullable = false)
    private String name;

    //附件类型（1：文档；2：视频；3：音频；4：安装程序）
    @Column(name = "TYPE")
    private Integer type;

    //附件路径
    @Column(name = "PATH")
    private String path;

    //附件文件后缀，如： .docx, .exe
    @Column(name = "SUFFIX")
    private String suffix;

    //附件大小（KB）
    @Column(name = "KILO_SIZE")
    private Double kiloSize;

    //附件说明
    @Column(name = "DESCRIPTION")
    private String description;

    public Attachment() {
    }

    public Attachment(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Double getKiloSize() {
        return kiloSize;
    }

    public void setKiloSize(Double kiloSize) {
        this.kiloSize = kiloSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", path='" + path + '\'' +
                ", suffix='" + suffix + '\'' +
                ", kiloSize='" + kiloSize + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}
