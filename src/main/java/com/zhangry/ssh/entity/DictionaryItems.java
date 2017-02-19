/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinvent.data.hibernate.BaseEntity;

import javax.persistence.*;

/**
 * 字典项实体
 * @author Liupj
 * @date 2017/2/6.
 */
@Entity
@Table(name = "T_BASE_DICTIONARY_ITEMS", uniqueConstraints = {@UniqueConstraint(columnNames = {"DICTIONARY_ID", "ITEM_KEY" })})
public class DictionaryItems extends BaseEntity {

    /*所属字典组*/
    @ManyToOne
    @JoinColumn(name = "DICTIONARY_ID", nullable = false)
    @JsonBackReference
    private Dictionary dictionary;

    /*字典项编号*/
    @Column(name = "ITEM_KEY", nullable = false)
    private String itemKey;

    /*字典项值（全称）*/
    @Column(name = "ITEM_FULL_VALUE", nullable = false)
    private String itemFullValue;

    /*字典项值（简称）*/
    @Column(name = "ITEM_SHORT_VALUE")
    private String itemShortValue;

    /*父级字典项*/
    @OneToOne
    @JoinColumn(name = "PARENT_DICTIONARY_ITEM")
    private DictionaryItems pDictionaryItem;

    /*启用状态（0-禁用；1-启用）*/
    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Integer enabled = 1;

    /*排序号*/
    @Column(name = "SEQ", nullable = false)
    private Integer seq;

    /* default constructor */
    public DictionaryItems() {
    }

    public DictionaryItems(String id) {
        this.id = id;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemShortValue() {
        return itemShortValue;
    }

    public void setItemShortValue(String itemShortValue) {
        this.itemShortValue = itemShortValue;
    }

    public String getItemFullValue() {
        return itemFullValue;
    }

    public void setItemFullValue(String itemFullValue) {
        this.itemFullValue = itemFullValue;
    }

    public DictionaryItems getpDictionaryItem() {
        return pDictionaryItem;
    }

    public void setpDictionaryItem(DictionaryItems pDictionaryItem) {
        this.pDictionaryItem = pDictionaryItem;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "DictionaryItems{" +
                "dictionary=" + dictionary +
                ", itemKey='" + itemKey + '\'' +
                ", itemShortValue='" + itemShortValue + '\'' +
                ", itemFullValue='" + itemFullValue + '\'' +
                ", pDictionaryItem=" + pDictionaryItem +
                ", enabled=" + enabled +
                ", seq=" + seq +
                "} " + super.toString();
    }
}
