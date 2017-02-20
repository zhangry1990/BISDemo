/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/1/16
 */
package com.zhangry.ssh.daoimpl;


import com.zhangry.common.constant.Constant;
import com.zhangry.common.page.Page;
import com.zhangry.common.page.QueryParameter;
import com.zhangry.data.hibernate.HibernateDAO;
import com.zhangry.ssh.dao.DictionaryItemsDAO;
import com.zhangry.ssh.entity.DictionaryItems;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangwei
 * @date 2017/2/14.
 */
@Repository
public class DictionaryItemsDAOImpl extends HibernateDAO<DictionaryItems, String> implements DictionaryItemsDAO {

    /**
     * 根据字典组编号获取字典项列表(带分页)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public Page<DictionaryItems> getDicItemsPageByGroupKey(QueryParameter parameter, String groupKey) {
        Map<String, Object> namedParameters = new HashMap<String, Object>(3);
        StringBuffer hql = new StringBuffer("from DictionaryItems dictionaryItems where 1=1");
        hql.append(" and dictionaryItems.dictionary.groupKey =:groupKey");
        namedParameters.put("groupKey",groupKey);

        hql.append(" and dictionaryItems.deletedFlag =:deletedFlag ");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);

        hql.append(" and dictionaryItems.enabled =:enabled");
        namedParameters.put("enabled",Constant.ENABLED);
        return findPageInCache(parameter, hql.toString(),namedParameters);
    }

    /**
     * 根据groupKey和itemKey获取系统字典项实体(验证相同字典组下，ItemKey不能重复)
     * @author huangwei
     * @param groupKey
     * @param itemKey
     * @return
     */
    @Override
    public DictionaryItems getDictItemsByGroupKeyAndItemKey(String groupKey, String itemKey) {

        Map<String, Object> namedParameters = new HashMap<String, Object>(3);
        StringBuffer hql = new StringBuffer(" from DictionaryItems dictionaryItems where 1 = 1");
        hql.append(" and dictionaryItems.dictionary.groupKey = :groupKey");
        namedParameters.put("groupKey", groupKey);

        hql.append(" and dictionaryItems.itemKey = :itemKey");
        namedParameters.put("itemKey", itemKey);

        hql.append(" and dictionaryItems.enabled = :enabled");
        namedParameters.put("enabled", Constant.ENABLED);

        return findUnique(hql, namedParameters);
    }

    /**
     * 根据字典组编号获取字典项列表(不带分页)
     * @param groupKey
     * @return
     */
    @Override
    public List<DictionaryItems> getDicItemsPageByGroupKey(String groupKey) {

        Map<String, Object> namedParameters = new HashMap<String, Object>(3);
        StringBuffer hql = new StringBuffer("from DictionaryItems dictionaryItems where 1 = 1");
        hql.append(" and dictionaryItems.dictionary.groupKey = :groupKey");
        namedParameters.put("groupKey",groupKey);

        hql.append(" and dictionaryItems.deletedFlag = :deletedFlag ");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);

        hql.append(" and dictionaryItems.enabled = :enabled");
        namedParameters.put("enabled", Constant.ENABLED);
        return find(hql.toString(),namedParameters);
    }
}
