package com.zhangry.ssh.dao;

import com.zhangry.data.hibernate.BaseDAO;
import com.zhangry.ssh.entity.Org;

import java.util.List;
import java.util.Map;

/**
 * @author hehao
 * @date 2017/2/13.
 */
public interface OrgDAO extends BaseDAO<Org, String> {
    /**
     * 根据父级组织Id获取组织（下拉框用）
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    List<Org> getOrgListByPId(String pId);

    /**
     * 根据条件获取组织列表树
     * @param condition
     * @author hehao
     * @date 2017-02-13
     */
    List<Map<String, Object>> getOrgTree(Map<String, Object> condition);

    /**
     * 组织列表树(下拉框)
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    List<Map<String, Object>> getOrgTree(String pId);

}
