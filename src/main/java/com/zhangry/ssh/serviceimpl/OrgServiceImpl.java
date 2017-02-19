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
import com.thinvent.common.util.AssertUtil;
import com.thinvent.common.util.MapperUtil;
import com.thinvent.service.impl.BaseServiceImpl;
import com.thinvent.wxgl.uc.dao.OrgDAO;
import com.thinvent.wxgl.uc.entity.Org;
import com.thinvent.wxgl.uc.entity.OrgUser;
import com.thinvent.wxgl.uc.entity.User;
import com.thinvent.wxgl.uc.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hehao
 * @date 2017/2/13.
 */
@Service
public class OrgServiceImpl extends BaseServiceImpl<Org, String> implements OrgService {

    @Autowired
    private OrgDAO orgDAO;

    /**
     * 组织新增/修改
     *
     * @param org
     */
    @Override
    public void save(Org org) {
        orgDAO.save(org);
    }

    /**
     * 组织逻辑删除
     * @param condition
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public boolean deleteOrg(Map<String, Object> condition) {
        //组织Id
        String orgId = (String) condition.get("id");
        AssertUtil.notEmpty(orgId, "orgId cannot be empty");
        //根据组织ID获取组织实体
        Org org = orgDAO.get(orgId);
        //根据组织ID取组织用户关联
        Set<OrgUser> orgUsers = org.getOrgUsers();
        //判断组织关联是否空
        if (orgUsers.size() > 0) {
            //不为空返回false
            return false;
        }
        //用户实体
        User user = (User) condition.get("User");
        //删除标记
        org.setDeletedFlag(Constant.DELETEDFLAG_YES);
        //用户不为空
        if (user != null) {
            //用户ID
            org.setDeletedUserId(user.getId());
            //用户名称
            org.setDeletedUserId(user.getFullName());
        }
        //删除时间
        org.setDeletedTime(new Date());
        return true;
    }

    /**
     * 组织列表树
     * @param condition
     * @param columns
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public String getOrgTree(Map<String, Object> condition, String... columns) {

        List<Map<String, Object>> res = orgDAO.getOrgTree(condition);

        return MapperUtil.convertToJson(res, columns);
    }

    /**
     * 根据父级组织Id获取组织（下拉框用）
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public List<Org> getOrgListByPId(String pId) {
        return orgDAO.getOrgListByPId(pId);
    }

    /**
     * 下拉框tree
     * @param pId
     * @author hehao
     * @date 2017-02-13
     */
    @Override
    public String getOrgTree(String pId) {

        List<Map<String, Object>> res = orgDAO.getOrgTree(pId);

        return MapperUtil.convertToJson(res);
    }
}
