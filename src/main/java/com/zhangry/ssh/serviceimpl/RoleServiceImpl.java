/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.serviceimpl;

import com.thinvent.common.constant.Constant;
import com.thinvent.common.page.Page;
import com.thinvent.common.page.QueryParameter;
import com.thinvent.common.util.AssertUtil;
import com.thinvent.common.util.MapperUtil;
import com.thinvent.common.util.StringUtil;
import com.thinvent.service.impl.BaseServiceImpl;
import com.thinvent.wxgl.uc.dao.ResourceDAO;
import com.thinvent.wxgl.uc.dao.RoleDAO;
import com.thinvent.wxgl.uc.dao.UserDAO;
import com.thinvent.wxgl.uc.entity.Resource;
import com.thinvent.wxgl.uc.entity.Role;
import com.thinvent.wxgl.uc.entity.User;
import com.thinvent.wxgl.uc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 角色接口实现
 * @author anss
 * @date 2017/2/10.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {

    //角色DAO
    @Autowired
    private RoleDAO roleDAO;
    //用户DAO
    @Autowired
    private UserDAO userDAO;
    //资源DAO
    @Autowired
    private ResourceDAO resourceDAO;

    /**
     * 分页查询角色列表
     * @author anss
     * @date 2017/2/13
     * @param queryParameter
     * @param condition
     * @param columns
     * @return
     */
    @Override
    public String getRoleSPageByCondition(QueryParameter queryParameter, Map<String, Object> condition, String... columns) {
        Page<Role> rolePage = roleDAO.getRolesPage(queryParameter, condition, columns);
        String strResult = MapperUtil.convertToJson(rolePage, columns);
        return strResult;
    }

    /**
     * 保存角色(添加/修改)
     * @author anss
     * @date 2017/2/13
     * @param role
     */
    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    /**
     * 根据角色主键Id获取实体
     * @author anss
     * @date 2017/2/13
     * @param id
     * @return Role
     */
    @Override
    public Role getRole(String id) {
        Role role = roleDAO.get(id);
        //用户角色集合
        role.getUsers().size();
        //角色资源集合
        role.getResources().size();
        return role;
    }

    /**
     * 判断角色名称是否存在
     * @author anss
     * @date 2017/2/13
     * @param roleName
     * @return ture（已存在）或false（不存在）
     */
    @Override
    public boolean isExistRoleName(String roleName) {
        AssertUtil.notEmpty(roleName, "can not empty");
        return roleDAO.isExistRoleName(roleName);
    }

    /**
     * 根据条件删除角色（支持批量逻辑删除）
     * @author anss
     * @date 2017/2/13
     * @param condition{idS:多个之间以“，”逗号分割角色Id集合；user:用户实体}
     * @return {true:删除成功；false：删除失败（存在未被解除的级联关系）}
     */
    @Override
    public boolean deleteRole(Map<String, Object> condition) {
        //角色IdS集合
        String strIdS = (String) condition.get("idS");
        AssertUtil.notEmpty(strIdS, "idS can not empty");
        //用户实体
        User user = (User) condition.get("user");

        //以逗号分割，转成数组
        String[] idArray = strIdS.split(",");
        List<Role> lstRole = roleDAO.get(Arrays.asList(idArray));
        //验证是否存在未被解除的用户角色、角色资源级联关系；如果存在，直接返回false
        for (Role role : lstRole) {
            if (role.getUsers().size() > 0 || role.getResources().size() > 0) {
                return false;
            }
        }
        //遍历存入删除时间、删除用户id、删除用户名称、删除标识
        for (Role role : lstRole) {
            role.setDeletedTime(new Date());
            //判断用户实体是否为空
            if (user != null) {
                role.setDeletedUserId(user.getId());
                role.setDeletedUserName(user.getUsername());
            }
            role.setDeletedFlag(Constant.DELETEDFLAG_YES);
        }
        return true;
    }

    /**
     * 给用户赋角色
     * @author anss
     * @date 2017/2/13
     * @param condition{roleId：角色id； userIdS：多个之间以“，”逗号分割用户id集合}
     */
    @Override
    public void saveUserRole(Map<String, Object> condition) {
        //角色id
        String strRoleId = (String) condition.get("roleId");
        AssertUtil.notEmpty(strRoleId, "roleId can not empty");
        //用户idS集合
        String strUserIdS = (String) condition.get("userIdS");

        //判断用户IdS集合是否为空
        List<User> lstUser = new ArrayList<>();
        //strUserIdS不为空时，取出相应实体List
        if (!StringUtil.isNullOrEmpty(strUserIdS)) {
            //以逗号分割，转成数组
            String[] userIdArray = strUserIdS.split(",");
            lstUser = userDAO.get(Arrays.asList(userIdArray));
        }
        Role role = roleDAO.get(strRoleId);
        //清空用户角色集合
        role.getUsers().clear();
        //添加用户角色集合
        role.getUsers().addAll(lstUser);
    }

    /**
     * 给角色赋权限（给角色添加资源）
     * @author anss
     * @date 2017/2/13
     * @param condition{roleId：角色id； resourceIdS：多个之间以“，”逗号分割资源id集合}
     */
    @Override
    public void saveRoleResource(Map<String, Object> condition) {
        //角色id
        String strRoleId = (String) condition.get("roleId");
        AssertUtil.notEmpty(strRoleId, "roleId can not empty");
        //资源idS集合
        String strResourceIdS = (String) condition.get("resourceIdS");

        List<Resource> lstResource= new ArrayList<>();
        //判断资源idS集合不为空时，取出相应实体List
        if (!StringUtil.isNullOrEmpty(strResourceIdS)) {
            //以逗号分割，转成数组
            String[] resourceArray = strResourceIdS.split(",");
            lstResource = resourceDAO.get(Arrays.asList(resourceArray));
        }
        Role role = roleDAO.get(strRoleId);
        //清空角色资源集合
        role.getResources().clear();
        //添加角色资源集合
        role.getResources().addAll(lstResource);
    }

    /**
     * 根据条件获取已启用的角色列表（不分页）
     * @author anss
     * @date 2017/2/13
     * @param condition
     * @param columns
     * @return
     */
    @Override
    public String getRoleList(Map<String, Object> condition, String... columns) {
        List<Role> lstRole = roleDAO.getRoleList(condition);
        String lstRoleResult = MapperUtil.convertToJson(lstRole, columns);
        return lstRoleResult;
    }
}
