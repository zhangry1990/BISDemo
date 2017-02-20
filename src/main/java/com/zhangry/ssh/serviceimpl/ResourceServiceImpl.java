/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/13
 */
package com.zhangry.ssh.serviceimpl;

import com.zhangry.common.constant.Constant;
import com.zhangry.common.util.AssertUtil;
import com.zhangry.common.util.MapperUtil;
import com.zhangry.common.util.StringUtil;

import com.zhangry.service.impl.BaseServiceImpl;
import com.zhangry.ssh.dao.ResourceDAO;
import com.zhangry.ssh.dao.RoleDAO;
import com.zhangry.ssh.entity.Resource;
import com.zhangry.ssh.entity.Role;
import com.zhangry.ssh.entity.User;
import com.zhangry.ssh.service.ResourceService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 资源接口实现
 * @author Zhouchao
 * @date 2017/2/13.
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource, String> implements ResourceService {

    @Autowired
    private ResourceDAO resourceDAO;

    @Autowired
    private RoleDAO roleDAO;

    /**
     * 获取资源树
     * @param condition
     * @param columns
     * @return
     */
    public String  getResourceTree(Map<String, Object> condition, String... columns)  {
        List<Resource> lstResource  = resourceDAO.getResourceTree(condition);
        return MapperUtil.convertToJson(lstResource, columns);
    }

    /**
     * 资源添加/修改
     * @param resource
     */
    @Override
    public void save(Resource resource) {
        resourceDAO.save(resource);
    }

    /**
     * 获取资源实体
     * @param id
     */
    @Override
    public Resource getResource(String id) {
        return resourceDAO.get(id);
    }

    /**
     * 资源删除
     * @param condition
     */
    public boolean deleteResource(Map<String, Object> condition) {
        String id = (String)condition.get("id");
        User user = (User)condition.get("user");
        //判断资源是否被使用
        if (!isResourUsed(id)) {
            Resource resource = resourceDAO.get(id);
            //判断用户名是否为空
            if (user != null) {
                resource.setDeletedUserId(user.getId());
                resource.setDeletedUserName(user.getFullName());
            }
            resource.setDeletedFlag(Constant.DELETEDFLAG_YES);
            resource.setDeletedTime(new Date());
            return true;
        }
        return false;
    }

    /**
     * 获取系统所有已启用的资源（Spring security 使用）
     *
     * @return
     *   Map： key=uri, value=roleIds
     */
    @Override
    public Map<String, String> getAllEnabledResources() {
        Map<String, String> requestMap = new HashMap<String, String>();

        List<Role> roles = roleDAO.getRoleList(new HashedMap<String, Object>());
        //获取角色所绑定资源
        Set<Resource> resources;
        for (Role role : roles) {
            resources = role.getResources();
            //获取资源uri
            for (Resource resource : resources) {
                String uri = resource.getResourceUri();
                if (StringUtil.isNullOrEmpty(uri)) {
                   continue;
                }
                //获取启用资源
                if (requestMap.containsKey(uri)) {
                    requestMap.put(uri, requestMap.get(uri) + "," + role.getId());
                    continue;
                }

                requestMap.put(uri, role.getId());
            }

        }

        return requestMap;
    }

    /**
     * 根据resourceId验证该资源是否被使用
     * @author anss
     * @date 2017/2/14
     * @param resourceId
     * @return{true：被使用；false：未被使用}
     */
    @Override
    public boolean isResourUsed(String resourceId) {
        AssertUtil.notEmpty(resourceId, "resourceId can not empty");
        //角色资源关联数目
        Integer roleResCount = resourceDAO.getRoleResourceCountByReourceId(resourceId);
        //大于0，被使用；反之，未被使用
        if (roleResCount > 0) {
            return true;
        } else {
            return false;
        }
    }

}
