package com.zhangry.ssh.dao;



import com.thinvent.common.page.Page;
import com.thinvent.common.page.QueryParameter;
import com.thinvent.data.hibernate.BaseDAO;
import com.thinvent.wxgl.uc.entity.DictionaryItems;

import java.util.List;


/**
 * @author huangwei
 * @date 2017/2/6.
 */
public interface DictionaryItemsDAO extends BaseDAO<DictionaryItems, String> {

    /**
     * 根据字典组编号获取字典项列表(带分页)
     * @author huangwei
     * @date 2017-02-10
     *
     */
     Page<DictionaryItems> getDicItemsPageByGroupKey(QueryParameter parameter, String groupKey);

    /**
     * 根据groupKey和itemKey获取系统字典项实体(验证相同字典组下，ItemKey不能重复)
     * @author huangwei
     * @param groupKey
     * @param itemKey
     * @return
     */
     DictionaryItems getDictItemsByGroupKeyAndItemKey(String groupKey, String itemKey);

    /**
     * 根据字典组编号获取字典项列表(不带分页)
     * @param groupKey
     * @return
     */
     List<DictionaryItems> getDicItemsPageByGroupKey(String groupKey);


}
