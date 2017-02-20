/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.serviceimpl;


import com.zhangry.common.constant.Constant;
import com.zhangry.common.page.Page;
import com.zhangry.common.page.QueryParameter;
import com.zhangry.common.util.AssertUtil;
import com.zhangry.common.util.MapperUtil;
import com.zhangry.service.impl.BaseServiceImpl;
import com.zhangry.ssh.dao.DictionaryDAO;
import com.zhangry.ssh.dao.DictionaryItemsDAO;
import com.zhangry.ssh.entity.Dictionary;
import com.zhangry.ssh.entity.DictionaryItems;
import com.zhangry.ssh.entity.User;
import com.zhangry.ssh.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统字典Service实现
 * @author huangwei
 * @date 2017/2/6.
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary, String> implements DictionaryService {

    @Autowired
    private DictionaryItemsDAO dictionaryItemsDAO;

    @Autowired
    private DictionaryDAO dictionaryDAO;


    /**
     * 根据字典组编号获取字典项列表(带分页)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public String getDicItemsPageByGroupKey(QueryParameter parameter, String groupKey, String ... columns) {
        AssertUtil.notEmpty(groupKey, "groupKey cannot be null or empty. ");
        Page<DictionaryItems> result = dictionaryItemsDAO.getDicItemsPageByGroupKey(parameter,groupKey);
        return MapperUtil.convertToJson(result,columns);
    }

    /**
     * 根据字典组编号获取字典项列表(不带分页)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public String getDiItemsByGroupKey(String groupKey, String... columns) {
        AssertUtil.notEmpty(groupKey, "groupKey cannot be null or empty. ");
        List<DictionaryItems> lstDic = dictionaryItemsDAO.getDicItemsPageByGroupKey(groupKey);
        return MapperUtil.convertToJson(lstDic,columns);
    }

    /**
     * 获取字典组列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Dictionary> getDictionaryList()  {
        return dictionaryDAO.getDictionaryList();
    }

    /**
     * 保存字典项(实体)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public void saveDictionaryItems(DictionaryItems dictionaryItems) {
        dictionaryItemsDAO.save(dictionaryItems);

    }

    /**
     * 根据字典项主键id获取字典项实体
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public DictionaryItems getDictionaryItemsById(String Id) {
        DictionaryItems  dictionaryItems = dictionaryItemsDAO.get(Id);
        return dictionaryItems;
    }

    /**
     * 删除字典项
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public void deleteDictionaryItems(Map<String, Object> condition) {
        String id = (String)condition.get("id");
        DictionaryItems dictionaryItems = dictionaryItemsDAO.get(id);
        User user = (User)condition.get("user");
        if (user != null) {
            dictionaryItems.setDeletedUserId(user.getId());
            dictionaryItems.setDeletedUserName(user.getUsername());
        }
        dictionaryItems.setDeletedFlag(Constant.DELETEDFLAG_YES);
        dictionaryItems.setDeletedTime(new Date());
    }

    /**
     * 根据groupKey和itemKey获取系统字典项实体(验证相同字典组下，ItemKey不能重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public DictionaryItems getDictItemsByGroupKeyAndItemKey(String groupKey, String itemKey) {
        AssertUtil.notEmpty(groupKey, "groupKey cannot be null or empty. ");
        AssertUtil.notEmpty(itemKey, "itemKey cannot be null or empty. ");
        DictionaryItems dictionaryItems = dictionaryItemsDAO.getDictItemsByGroupKeyAndItemKey(groupKey,itemKey);
        dictionaryItems.setDictionary(null);
        return dictionaryItems;
    }

    /**
     * 根据groupKey获取系统字典组实体(验证GroupKey重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public Dictionary getDictionaryByGroupKey(String GroupKey) {
        AssertUtil.notEmpty(GroupKey, "groupKey cannot be null or empty. ");
        Dictionary dictionary = dictionaryDAO.getDictionaryByGroupKey(GroupKey);
        dictionary.getDictionaryItemses().clear();

        return dictionary;
    }

    /**
     * 保存字典组
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public void save(Dictionary dictionary) {
        dictionaryDAO.save(dictionary);

    }

    /**
     * 根据字典组ID获取字典组实体
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public Dictionary getDictionaryById(String Id) {
        Dictionary dictionary = dictionaryDAO.get(Id);
        return dictionary;
    }

    /**
     * 删除字典组
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public boolean deleteDictionary(Map<String, Object> condition) {
        String id = (String)condition.get("id");
        Dictionary dictionary = dictionaryDAO.get(id);
        if (dictionary.getDictionaryItemses().size() > 0) {
            return false;
        }
        User user = (User)condition.get("user");
        if (user != null ) {
            dictionary.setDeletedUserId(user.getId());
            dictionary.setDeletedUserName(user.getUsername());
        }
        dictionary.setDeletedFlag(Constant.DELETEDFLAG_YES);
        dictionary.setDeletedTime(new Date());
        return true;
    }



}
