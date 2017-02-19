/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.daoimpl;

import com.thinvent.common.constant.Constant;
import com.thinvent.common.util.StringUtil;
import com.thinvent.data.hibernate.HibernateDAO;
import com.thinvent.wxgl.uc.dao.OrgDAO;
import com.thinvent.wxgl.uc.entity.Org;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hehao
 * @date 2017/2/12.
 */
@Repository
public class OrgDAOImpl extends HibernateDAO<Org, String> implements OrgDAO {

    /**
     * 根据父级组织Id获取组织（下拉框用）
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public List<Org> getOrgListByPId(String pId) {
        Map<String, Object> namedParameters = new HashMap<>(2);
        StringBuffer hql = new StringBuffer(" FROM Org org WHERE org.deletedFlag =:deletedFlag");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);
        //判断父ID是否空
       if (!StringUtil.isNullOrEmpty(pId)) {
            hql.append(" AND org.pOrg.id = :pId");
            namedParameters.put("pId", pId);
        }
        return find(hql, namedParameters);
    }

    /**
     * 根据条件获取组织列表树
     * @param condition
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public List<Map<String, Object>> getOrgTree(Map<String, Object> condition) {
        //父ID
        String pId = (String) condition.get("pId");

        Map<String, Object> namedParameters = new HashMap<>(2);

        StringBuffer sql = new StringBuffer("SELECT * FROM T_BASE_ORG org WHERE org.DELETED_FLAG =:deletedFlag");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);

        if (!StringUtil.isNullOrEmpty(pId)) {
            sql.append(" START WITH org.PARENT_ORG_ID =:pId CONNECT BY PARENT_ORG_ID = PRIOR ID ");
            namedParameters.put("pId", pId);
        }
        //sql.append("order by org.seq asc");

        return findBySql(sql.toString(), namedParameters);

    }

    /**
     * 根据父Id获取组织列表树
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public List<Map<String, Object>> getOrgTree(String pId) {

        Map<String, Object> namedParameters = new HashMap<>(2);
        StringBuffer sql = new StringBuffer("SELECT * FROM T_BASE_ORG org WHERE org.DELETED_FLAG =:deletedFlag");
        namedParameters.put("deletedFlag", Constant.DELETEDFLAG_NO);
        if (!StringUtil.isNullOrEmpty(pId)) {
            sql.append(" START WITH org.PARENT_ORG_ID =:pId CONNECT BY PARENT_ORG_ID = PRIOR ID ");
            namedParameters.put("pId", pId);
        }

        return findBySql(sql.toString(), namedParameters);
    }

}
