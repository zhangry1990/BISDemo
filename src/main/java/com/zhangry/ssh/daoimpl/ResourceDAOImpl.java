package com.zhangry.ssh.daoimpl;

import com.zhangry.common.constant.Constant;
import com.zhangry.common.util.StringUtil;
import com.zhangry.data.hibernate.HibernateDAO;
import com.zhangry.ssh.dao.ResourceDAO;
import com.zhangry.ssh.entity.Resource;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Zhouchao
 * @date 2017/2/13.
 */
@Repository
public class ResourceDAOImpl extends HibernateDAO<Resource, String> implements ResourceDAO {

    private final String HQL = StringUtil.format(" FROM {0} resource WHERE resource.deletedFlag = {1} ", entityClass.getName(), String.valueOf(Constant.DELETEDFLAG_NO));

    /**
     * 获取资源树
     * @param condition
     * @return
     */
    @Override
    public List<Resource> getResourceTree(Map<String, Object> condition) {
        //定义Map,存放参数
        Map<String, Object> namedParameters = new HashMap<String, Object>(1);
        //查询SQl
        StringBuffer hql = new StringBuffer(HQL);

        return find(hql, namedParameters);
    }

    /**
     * 根据资源Id获取角色资源关联数目
     * @author anss
     * @date 2017/2/14
     * @param resourceId
     * @return
     */
    @Override
    public Integer getRoleResourceCountByReourceId(String resourceId) {
        Map<String, Object> paramMap = new HashMap<String, Object>(1);
        String SQL = "SELECT COUNT(*) AS ROLERESCOUNT FROM T_BASE_ROLE_RESOURCE WHERE RESOURCE_ID =:RESOURCEID";
        paramMap.put("RESOURCEID", resourceId);
        List<Map<String, Object>> lstMap = this.findBySql(SQL, paramMap);
        //角色资源关联数目
        String strRoleResCount = null;
        for (Map<String, Object> map : lstMap) {
            strRoleResCount = String.valueOf(map.get("ROLERESCOUNT"));
        }
        return Integer.valueOf(strRoleResCount);
    }

}
