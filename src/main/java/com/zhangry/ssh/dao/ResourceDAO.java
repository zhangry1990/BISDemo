/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.dao;

import com.thinvent.data.hibernate.BaseDAO;
import com.thinvent.wxgl.uc.entity.Resource;
import java.util.List;
import java.util.Map;

/**
 * 资源DAO
 * @author Zhouchao
 * @date 2017/2/13.
 */
public interface ResourceDAO extends BaseDAO<Resource, String> {

    /**
     * 获取资源树
     * @param condition
     * @return
     */
    List<Resource> getResourceTree(Map<String, Object> condition);

    /**
     * 根据资源Id获取角色资源关联数目
     * @author anss
     * @date 2017/2/14
     * @param resourceId
     * @return
     */
    Integer getRoleResourceCountByReourceId(String resourceId);

}
