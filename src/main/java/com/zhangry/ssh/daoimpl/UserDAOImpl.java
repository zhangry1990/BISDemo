/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 *
 * 版权所有。
 *
 * 类名　　       : UserDAO
 * 功能概要       : UserDAO接口
 * 创建日期       : 2017/02/13 yanyj
 * 修改日期       : 无
 *                : 无
 */
package com.zhangry.ssh.daoimpl;


import com.zhangry.common.page.Page;
import com.zhangry.common.page.QueryParameter;
import com.zhangry.common.util.StringUtil;
import com.zhangry.data.hibernate.HibernateDAO;
import com.zhangry.ssh.dao.UserDAO;
import com.zhangry.ssh.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanyj
 * @date 2017/2/13.
 */
@Repository
public class UserDAOImpl extends HibernateDAO<User, String> implements UserDAO {


    /**
     * 根据用户名获取对应用户信息
     *
     * @author yanyj
     * @date 2017/2/13
     * @param username 用户名
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("deletedFlag", 0);

        return findUnique(params);
    }

    /**
     * 分页获取用户列表
     *
     * @author yanyj
     * @date 2017/2/13
     * @param queryParameter 分页对象
     * @param condition 查询条件  {username:用户名（支持模糊查询）; fullName:姓名（支持模糊查询）;userNo: 用户编号;
     *                            jobStatus: 在职状态;enabled: 是否激活  }
     * @return
     */
    @Override
    public Page<User> getUserList(QueryParameter queryParameter, Map<String, Object> condition) {

        StringBuilder hql = new StringBuilder("FROM User WHERE ");

        //防止condition为空
        if (condition == null) {
            condition = new HashMap<String, Object>();
        }

        String username = (String) condition.get("username");
        String fullName = (String) condition.get("fullName");
        String userNo = (String) condition.get("userNo");
        String jobStatus = (String) condition.get("jobStatus");
        String enabled= (String) condition.get("enabled");


        //用户名
        if(!StringUtil.isNullOrEmpty(username)) {
            hql.append("username LIKE :username and ");
            condition.put("username" , "%" + username + "%");
        }

        //姓名
        if(!StringUtil.isNullOrEmpty(fullName)) {
            hql.append("fullName LIKE :fullName and ");
            condition.put("fullName" , "%" + fullName + "%");
        }

        //工作状态
        if(!StringUtil.isNullOrEmpty(jobStatus)) {
            hql.append("jobStatus = :jobStatus and ");
        }

        //激活状态
        if(!StringUtil.isNullOrEmpty(enabled)) {
            hql.append("enabled = :enabled and ");
        }

        //用户编号
        if(!StringUtil.isNullOrEmpty(userNo)) {
            hql.append("userNo = :userNo and ");
        }

        //Todo: 查询条件待定
        hql.append("deletedFlag = :deletedFlag");
        condition.put("deletedFlag", Constant.DELETEDFLAG_NO);

        hql.append(" order by seq asc, createdTime desc");

        return this.findPage(queryParameter, hql.toString(), condition);
    }

    /**
     * 获取用户树
     *
     * @author yanyj
     * @date 2017/2/13
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserTree() {

        //Todo：查询的数据格式待定，未完成
        String sql = "select u.ID,u.username as text, ou.org_id as pid from t_base_user u, t_base_org_user ou where u.id = ou.user_id "
                    + "union all select id , org_full_name as text, parent_org_id as pid from t_base_org ";

        return findBySql(sql);
    }
}
