/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.service;

import com.zhangry.common.page.QueryParameter;
import com.zhangry.service.BaseService;
import com.zhangry.ssh.entity.Dictionary;
import com.zhangry.ssh.entity.DictionaryItems;

import java.util.List;
import java.util.Map;

/**
 * @author huangwei
 * @date 2017/2/6.
 */
public interface DictionaryService extends BaseService<Dictionary, String> {

    /**
     * 根据字典组编号获取字典项列表(带分页)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    String getDicItemsPageByGroupKey(QueryParameter parameter, String groupKey, String... columns);

    /**
     * 保存字典项(实体)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    void saveDictionaryItems(DictionaryItems dictionaryItems);

    /**
     * 根据字典项主键id获取字典项实体
     * @author huangwei
     * @date 2017-02-10
     *
     */
    DictionaryItems getDictionaryItemsById(String Id);

    /**
     * 删除字典项
     * @author huangwei
     * @date 2017-02-10
     *
     */
    void deleteDictionaryItems(Map<String, Object> condition);

    /**
     * 根据groupKey和itemKey获取系统字典项实体(验证相同字典组下，ItemKey不能重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    DictionaryItems getDictItemsByGroupKeyAndItemKey(String groupKey, String itemKey);

    /**
     * 根据groupKey获取系统字典组实体(验证GroupKey重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    Dictionary getDictionaryByGroupKey(String GroupKey);

    /**
     * 保存字典组
     * @author huangwei
     * @date 2017-02-10
     *
     */
    void save(Dictionary dictionary);

    /**
     * 根据字典组ID获取字典组实体
     * @author huangwei
     * @date 2017-02-10
     *
     */
    Dictionary getDictionaryById(String Id);

    /**
     * 删除字典组
     * @author huangwei
     * @date 2017-02-10
     *
     */
    boolean deleteDictionary(Map<String, Object> condition);

    /**
     * 根据字典组编号获取字典项列表(不带分页)
     * @param columns
     * @param groupKey
     * @return
     */
    String getDiItemsByGroupKey(String groupKey, String... columns);

    /**
     * 获取字典组列表
     * @return
     * @throws Exception
     */
    List<Dictionary> getDictionaryList();
}
