package com.zhangry.service;

import java.io.Serializable;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface BaseService<T, PK extends Serializable> {
    T get(PK var1);
}
