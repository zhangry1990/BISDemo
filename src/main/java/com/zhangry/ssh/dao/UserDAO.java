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
package com.zhangry.ssh.dao;


import com.zhangry.common.page.Page;
import com.zhangry.common.page.QueryParameter;
import com.zhangry.data.hibernate.BaseDAO;
import com.zhangry.ssh.entity.User;

import java.util.List;
import java.util.Map;

/**
 * UserDAO接口
 *
 * @author yanyj
 * @date 2017/2/13.
 */
public interface UserDAO extends BaseDAO<User, String> {
    /**
     * 根据用户名获取对应用户信息
     *
     * @author yanyj
     * @date 2017/2/13
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);

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
    Page<User> getUserList(QueryParameter queryParameter, Map<String, Object> condition);

    /**
     * 获取用户树
     *
     * @author yanyj
     * @date 2017/2/13
     * @return
     */
    List<Map<String, Object>> getUserTree();
}
