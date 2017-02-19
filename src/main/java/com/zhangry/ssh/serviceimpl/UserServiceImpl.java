/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 *
 * 版权所有。
 *
 * 类名　　       : UserServiceImpl
 * 功能概要       : UserService接口实现
 * 创建日期       : 2017/02/13 yanyj
 * 修改日期       : 无
 *                : 无
 */
package com.zhangry.ssh.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.thinvent.common.constant.Constant;
import com.thinvent.common.page.Page;
import com.thinvent.common.page.QueryParameter;
import com.thinvent.common.util.AssertUtil;
import com.thinvent.common.util.CollectionUtil;
import com.thinvent.common.util.MapperUtil;
import com.thinvent.service.impl.BaseServiceImpl;
import com.thinvent.wxgl.uc.dao.UserDAO;
import com.thinvent.wxgl.uc.entity.OrgUser;
import com.thinvent.wxgl.uc.entity.Resource;
import com.thinvent.wxgl.uc.entity.Role;
import com.thinvent.wxgl.uc.entity.User;
import com.thinvent.wxgl.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.util.*;

/**
 * UserService 实现类
 *
 * @author yanyj
 * @date 2017/2/13.
 */
@Service
@Path("/users")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {


    @Autowired
    private UserDAO userDAO;


    /**
     * 用户 添加/修改
     *
     * @author yanyj
     * @date 2017/2/14
     * @param user 保存/更新的用户实体
     */
    @Override
    public void save(User user) {

        userDAO.save(user);
    }

    /**
     * 根据用户名获取对应用户信息
     *
     * @author yanyj
     * @date 2017/2/14
     * @param username 用户名
     * @return
     */
    @Override
    public User getUserByUsername(String username) {

        return userDAO.getUserByUsername(username);
    }


    /**
     * 逻辑删除用户
     *
     * @author yanyj
     * @date 2017/2/14
     * @param deleteInfo 删除信息  {deleteUserID ： 删除的用户ID; opUserID: 操作的用户ID}
     */
    @Override
    public boolean deleteUser(Map<String, Object> deleteInfo) {

        //FIXME: 传递多个用户删除

        String deletedUserIDStr = (String) deleteInfo.get("deletedUserIDs");
        String[] deletedUserIDArray = deletedUserIDStr.split(",");
        String opUserID = (String) deleteInfo.get("opUserID");

        //删除操作用户
        User opUser = userDAO.get(opUserID);
        //被删除用户列表
        List<User> deletedUserList = userDAO.get(Arrays.asList(deletedUserIDArray));

        //有用户有关联角色时所有用户都不可删除
        for(User deletedUser : deletedUserList) {

            if(!deletedUser.getRoles().isEmpty()) {
                return false;
            }
        }

        //删除用户
        for(User deletedUser : deletedUserList) {
            //逻辑删除， 用户删除信息更新
            deletedUser.setDeletedFlag(Constant.DELETEDFLAG_YES);
            deletedUser.setDeletedTime(new Date());

            if (opUser != null) {
                deletedUser.setDeletedUserId(opUser.getId());
                deletedUser.setDeletedUserName(opUser.getUsername());
            }

            //删除用户的机构关系
            deletedUser.getOrgUsers().clear();
        }

        return true;

    }


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
    @Override
    public String getUserPageList(QueryParameter queryParameter, Map<String, Object> condition, String... columns) {


        Page<User> page = userDAO.getUserList(queryParameter,condition);

        //将结果集转换为带分页的JSON
        return MapperUtil.convertToJson(page, columns);
    }

    /**
     * 获取用户树
     *
     * @author yanyj
     * @date 2017/2/13
     * @return
     */
    @Override
    public String getUserTree() {

        //将获取的结果集转换成Tree型ListMap
        List<Map<String, Object>> userTreeList = CollectionUtil.listToTree(userDAO.getUserTree(), "ID",
                "PID" );


        String userTreejson = MapperUtil.convertToJson(userTreeList);

        return userTreejson;
    }


    /**
     *  根据用户名加载用户详细信息（spring security用）
     *
     * @author yanyj
     * @date 2017/2/14
     * @param username 用户名
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        AssertUtil.notEmpty(username, "username cannot be null or empty.");

        // 查询用户信息
        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            throw new IllegalStateException(username + "没有找到!");
        }

        //获取用户第一个非兼职组织信息
        user.getOrgId();
        user.getOrgName();

        //获取用户菜单树
        Set<Resource> userResSet = new HashSet<>();
        Set<Role> roles = user.getRoles();

        Set<Resource> resources;

        for(Role role : roles) {
            resources = role.getResources();
            for (Resource item : resources) {
                userResSet.add(item);
            }
        }

        List<Resource> userResList = new ArrayList<>(userResSet);

        Collections.sort(userResList, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });

        List<Map<String, Object>> resMapList = new ArrayList<>(userResList.size());

        Map<String, Object> map;
        Map<String, Object> pMap;
        for (Resource resource : userResList) {
             if (resource.getType() != 0) {
                continue;
             }

             map = MapperUtil.convertToMap(JSON.toJSONString(resource));
             pMap = (Map<String, Object>) map.get("pResource");

             if (pMap == null) {
                 map.put("pId", null);
             } else {
                 map.put("pId", pMap.get("id"));
             }

             map.remove("pResource");

            resMapList.add(map);
        }

        user.setResources(CollectionUtil.listToTree(resMapList, "id", "pId"));


        //设置用户登录后默认选中的菜单（优先选中用户设置的默认菜单，如果默认菜单没权限，设置为当前第一个可跳转的菜单）
        setDefaultResource(user, userResList);


        //获取当前用户拥有的权限
        List<GrantedAuthority> authsList = Lists.newArrayList();

        for (Role role : roles) {
            authsList.add(new SimpleGrantedAuthority(role.getId()));
        }

        authsList.add(new SimpleGrantedAuthority("user"));


        //忽略用户不关系的Collection数据
        user.setAuthsList(authsList);
        user.setOrgUsers(new HashSet<OrgUser>());
        user.setRoles(new HashSet<Role>());


        return user;
    }

    /*设置用户登陆后默认选中菜单*/
    private void setDefaultResource(User user, List<Resource> resources) {
        final Resource defaultResource = user.getDefaultResource();

        boolean havePermission = false;

        if (defaultResource != null) {
            Resource target = CollectionUtil.find(resources, new Predicate<Resource>() {
                @Override
                public boolean apply(Resource resource) {
                    return resource.getId().equals(defaultResource.getId());
                }
            });

            havePermission = (target != null);
        }

        if (!havePermission) {
            Resource firstLinkToRes = findTargetResource(user.getResources(), resources);
            user.setDefaultResource(firstLinkToRes);
        }

    }

    /**
     * 查找用户第一个可跳转的url
     * @param menuTreeList
     * @param resources
     * @return
     */
    private Resource findTargetResource(List<Map<String, Object>> menuTreeList, List<Resource> resources) {
        for (final Map<String, Object> menu : menuTreeList) {
            List<Map<String, Object>> children = (List<Map<String, Object>>)menu.get("children");

            if (children.size() == 0) {
                if (menu.get("resourceUri") != null) {
                    return CollectionUtil.find(resources, new Predicate<Resource>() {
                        @Override
                        public boolean apply(Resource resource) {
                            return resource.getId().equals(menu.get("id"));
                        }
                    });
                }

            } else {
                return findTargetResource(children, resources);
            }
        }

        throw new IllegalStateException("用户权限无权限或设置错误");
    }
}
