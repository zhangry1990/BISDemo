package com.zhangry.ssh.controller;

import com.zhangry.ssh.entity.User;

/**
 * Controller层基类，提供应用层共通处理
 *
 * @author zhaojian
 * @date 20170119
 */
public abstract class BaseController {

    public User currentUser() {
        return UserContext.getCurrentUser();
    }


}