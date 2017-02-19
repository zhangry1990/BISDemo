/**
 * Copyright (C) 2017 南京思创信息技术有限公司
 * <p>
 * 版权所有。
 * <p>
 * 功能概要    :
 * 做成日期    : 2017/2/6
 */
package com.zhangry.ssh.service;

import com.thinvent.service.BaseService;
import com.thinvent.wxgl.uc.entity.Org;
import java.util.List;
import java.util.Map;

/**
 * @author hehao
 * @date 2017/2/13.
 */
public interface OrgService extends BaseService<Org, String> {

    /**
     * 组织新增/修改
     * @param org
     */
    void save(Org org);

    /**
     * 组织逻辑删除
     * @param condition
     */
    boolean deleteOrg(Map<String, Object> condition);

    /**
     * 组织列表树
     * @param condition
     * @param columns
     */
    String getOrgTree(Map<String, Object> condition, String... columns);

    /**
     * 根据父级组织Id获取组织（下拉框用）
     * @param pId
     */
    List<Org> getOrgListByPId(String pId);

    /**
     * 下拉框tree
     * @param pId
     */
    String getOrgTree(String pId);

}
