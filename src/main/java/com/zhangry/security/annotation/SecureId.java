package com.zhangry.security.annotation;

import java.lang.annotation.*;

/**
 * Created by zhangry on 2017/2/20.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SecureId {
    String value();
}
