package com.zhangry.service;

import com.zhangry.common.exception.ServiceException;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface LogService {
    void recordLog(Exception var1) throws ServiceException;
}