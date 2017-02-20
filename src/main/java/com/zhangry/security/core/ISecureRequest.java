package com.zhangry.security.core;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface ISecureRequest extends ISecureObject {
    HttpServletRequest getRequest();
}
