package com.zhangry.jssh.support;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by zhangry on 2017/2/20.
 */
public class UserContext {
    public UserContext() {
    }

    public static UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        } else {
            Object principal = authentication.getPrincipal();
            return principal != null && principal instanceof UserDetails?(UserDetails)principal:null;
        }
    }

    public static User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object obj = null;
        if(authentication != null) {
            obj = authentication.getPrincipal();
        }

        User user = null;
        if(obj != null && obj instanceof User) {
            user = (User)obj;
        }

        return user;
    }
}
