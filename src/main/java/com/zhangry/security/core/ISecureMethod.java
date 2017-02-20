package com.zhangry.security.core;

import java.lang.reflect.Method;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface ISecureMethod extends ISecureObject {
    Method getMethod();
}
