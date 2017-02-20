package com.zhangry.security.core;

import java.lang.reflect.Method;

/**
 * Created by zhangry on 2017/2/20.
 */
public class SecureMethod extends SecureObject implements ISecureMethod {
    private Method method;

    public SecureMethod() {
    }

    public SecureMethod(String secureId, Method method) {
        super(secureId);
        this.method = method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return this.method;
    }
}
