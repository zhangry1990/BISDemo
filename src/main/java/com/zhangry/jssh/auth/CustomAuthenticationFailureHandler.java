package com.zhangry.jssh.auth;

import com.zhangry.common.exception.AuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private static Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailureHandler.class);

    public CustomAuthenticationFailureHandler() {
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if(e != null) {
            if(e instanceof BadCredentialsException) {
                e = new BadCredentialsException("登录失败：密码错误");
            } else {
                Throwable throwable = ((AuthenticationException)e).getCause();
                if(throwable != null && throwable instanceof AuthException) {
                    e = new InternalAuthenticationServiceException(String.format("登录失败：%s", new Object[]{((AuthException)throwable).getCasuseMessage()}));
                } else {
                    logger.error(((AuthenticationException)e).getMessage(), (Throwable)e);
                    e = new InternalAuthenticationServiceException("登录失败：系统异常");
                }
            }
        }

        request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", e);
        super.onAuthenticationFailure(request, response, (AuthenticationException)e);
    }
}
