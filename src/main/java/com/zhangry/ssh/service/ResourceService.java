/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.service;


import com.zhangry.ssh.entity.Resource;

import java.util.Map;

/**
 * 资源接口
 * @author Zhouchao
 * @date 2017/2/13.
 */
public interface ResourceService extends BaseService<Resource, String> {

    /**
     * 获取资源树
     * @param condition
     * @param columns
     * @return
     */
    String  getResourceTree(Map<String, Object> condition, String... columns);

    /**
     * 资源添加/修改
     * @param resource
     */
    void save(Resource resource);

    /**
     * 获取资源实体
     * @param id
     */
    Resource getResource(String id);

    /**
     * 资源删除
     * @param condition
     */
    boolean deleteResource(Map<String, Object> condition);

    /**
     * 获取系统所有已启用的资源（Spring security 使用）
     * @return
     */
    Map<String, String> getAllEnabledResources();

    /**
     * 根据resourceId验证该资源是否被使用
     * @author anss
     * @date 2017/2/14
     * @param resourceId
     * @return{true：被使用；false：未被使用}
     */
    boolean isResourUsed(String resourceId);

}
