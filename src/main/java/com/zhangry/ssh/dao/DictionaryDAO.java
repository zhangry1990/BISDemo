package com.zhangry.ssh.dao;

import com.zhangry.data.hibernate.BaseDAO;
import com.zhangry.ssh.entity.Dictionary;

import java.util.List;


/**
 * @author huangwei
 * @date 2017/2/6.
 */
public interface DictionaryDAO extends BaseDAO<Dictionary, String> {
    /**
     * 根据groupKey获取系统字典组实体(验证GroupKey重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    public Dictionary getDictionaryByGroupKey(String GroupKey);

    /**
     * 获取字典组列表
     * @return
     * @throws Exception
     */
    public List<Dictionary> getDictionaryList();
}
