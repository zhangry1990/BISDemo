package com.zhangry.ssh.service;

import java.io.Serializable;

/**
 * Created by zhangry on 2017/2/17.
 */
public interface BaseService<T, PK extends Serializable> {
    T get(PK var1);
}