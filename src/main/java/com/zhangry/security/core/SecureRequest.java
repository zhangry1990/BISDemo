package com.zhangry.security.core;

/**
 * Created by zhangry on 2017/2/20.
 */
public class SecureRequest extends SecureObject implements ISecureRequest {
    private HttpServletRequest request;

    public SecureRequest() {
    }

    public SecureRequest(String secureId, HttpServletRequest request) {
        super(secureId);
        this.request = request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }
}