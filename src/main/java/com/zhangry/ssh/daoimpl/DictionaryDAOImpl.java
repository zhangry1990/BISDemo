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
import com.zhangry.data.hibernate.HibernateDAO;
import com.zhangry.ssh.dao.DictionaryDAO;
import com.zhangry.ssh.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**sss
 * @author huangwei
 * @date 2017/2/14.
 */
@Repository
public class DictionaryDAOImpl extends HibernateDAO<Dictionary, String> implements DictionaryDAO {

    /**
     * 根据groupKey获取系统字典组实体(验证GroupKey重复)
     * @author huangwei
     * @date 2017-02-10
     *
     */
    @Override
    public Dictionary getDictionaryByGroupKey(String GroupKey) {

        Map<String, Object> namedParameters = new HashMap<String, Object>(2);
        StringBuffer hql = new StringBuffer(" from Dictionary dictionary where 1 = 1");

        hql.append(" and dictionary.groupKey = :groupKey");
        namedParameters.put("groupKey", GroupKey);

        hql.append(" and dictionary.enabled = :enabled");
        namedParameters.put("enabled", Constant.ENABLED);
        return findUnique(hql.toString(),namedParameters);
    }

    /**
     * 获取字典组列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Dictionary> getDictionaryList() {
        Map<String, Object> namedParameters = new HashMap<String, Object>(2);
        StringBuffer hql = new StringBuffer(" from Dictionary dictionary where dictionary.deletedFlag = :deletedFlag");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);

        hql.append(" and dictionary.enabled = :enabled");
        namedParameters.put("enabled", Constant.ENABLED);
        return find(hql.toString(),namedParameters);
    }


}
