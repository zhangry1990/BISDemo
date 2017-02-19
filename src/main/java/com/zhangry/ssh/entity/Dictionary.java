/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thinvent.data.hibernate.BaseEntity;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 字典组实体
 * @author Liupj
 * @date 2017/2/6.
 */
@Entity
@Table(name = "T_BASE_DICTIONARY")
public class Dictionary extends BaseEntity {
    /*字典组KEY*/
    @Column(name = "GROUP_KEY", unique = true, nullable = false)
    private String groupKey;

    /*字典组名称*/
    @Column(name = "GROUP_NAME", nullable = false)
    private String groupName;

    /*启用状态（0-禁用；1-启用）*/
    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Integer enabled = 1;

    /*字典明细列表*/
    @OneToMany(mappedBy = "dictionary", orphanRemoval = true, cascade = CascadeType.ALL)
    @OrderBy("seq")
    @Where(clause = "ENABLED = 1 AND DELETED_FLAG = 0")
    @JsonManagedReference
    private Set<DictionaryItems> dictionaryItemses = new HashSet<>();

    /* default constructor */
    public Dictionary() {
    }

    public Dictionary(String id) {
        this.id = id;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Set<DictionaryItems> getDictionaryItemses() {
        return dictionaryItemses;
    }

    public void setDictionaryItemses(Set<DictionaryItems> dictionaryItemses) {
        this.dictionaryItemses = dictionaryItemses;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "groupKey='" + groupKey + '\'' +
                ", groupName='" + groupName + '\'' +
                ", enabled=" + enabled +
                ", dictionaryItemses=" + dictionaryItemses +
                "} " + super.toString();
    }
}
