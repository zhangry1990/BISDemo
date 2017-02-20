package com.zhangry.security.core;

import org.springframework.security.access.ConfigAttribute;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface ISecureObjectManager extends ISecurityManager {
    Map<String, Collection<ConfigAttribute>> loadAllConfigAttributes();

    Collection<ConfigAttribute> loadConfigAttributes(ISecureObject var1);
}