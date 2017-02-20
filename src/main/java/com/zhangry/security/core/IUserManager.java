package com.zhangry.security.core;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface IUserManager extends ISecurityManager {
    UserDetails loadUserByUsername(String var1);
}