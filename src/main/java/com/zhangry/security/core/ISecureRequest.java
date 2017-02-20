package com.zhangry.security.core;

/**
 * Created by zhangry on 2017/2/20.
 */
public interface ISecureRequest extends ISecureObject {
    HttpServletRequest getRequest();
}
