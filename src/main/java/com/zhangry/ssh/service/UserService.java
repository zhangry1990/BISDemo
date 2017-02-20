/** Copyright (C) 2017 南京思创信息技术有限公司
*
* 版权所有。
*
* 类名　　       : UserService
* 功能概要       : 用户管理接口
* 创建日期       : 2017/2/13 yanyj
* 修改日期       : 无
*                : 无
*/
package com.zhangry.ssh.service;


import com.zhangry.common.page.QueryParameter;
import com.zhangry.service.BaseService;
import com.zhangry.ssh.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

/**
 *
 * UserService接口
 *
 * @author yanyj
 * @date 2017/2/13.
 */
public interface UserService extends BaseService<User, String> {

    /**
     * 用户 添加/修改
     *
     * @author yanyj
     * @date 2017/2/14
     * @param user 保存/更新的用户实体
     */
    void save(User user);

    /**
     * 根据用户名获取对应用户信息
     *
     * @author yanyj
     * @date 2017/2/14
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 逻辑删除用户
     *
     * @author yanyj
     * @date 2017/2/14
     * @param deleteInfo 删除信息  {deleteUserID ： 删除的用户ID; opUserID: 操作的用户ID}
     */
    boolean deleteUser(Map<String, Object> deleteInfo);

    /**
     * 分页获取用户列表
     *
     * @author yanyj
     * @date 2017/2/13
     * @param queryParameter 分页对象
     * @param condition 查询条件  {username:用户名（支持模糊查询）; fullName:姓名（支持模糊查询）;userNo: 用户编号;
     *                            jobStatus: 在职状态;enabled: 是否激活  }
     * @param columns 需要的列表字段
     * @return
     */
    String getUserPageList(QueryParameter queryParameter, Map<String, Object> condition, String... columns);

    /**
     * 获取用户树
     *
     * @author yanyj
     * @date 2017/2/13
     * @return
     */
    String getUserTree();

    /**
     *  根据用户名加载用户详细信息（spring security用）
     *
     * @author yanyj
     * @date 2017/2/14
     * @param userName 用户名
     * @return
     */
    UserDetails loadUserByUsername(String userName);
}
