package com.zhangry.security.web.access;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private String errorPage = null;
    private String csrfErrorPage = null;

    public CustomAccessDeniedHandler() {
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String errPage = null;
        request.setAttribute("exception", accessDeniedException);
        response.setStatus(403);
        if(HttpUtil.isAjaxRequest(request)) {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter dispatcher = response.getWriter();
            dispatcher.print(accessDeniedException.getMessage());
        } else {
            if(!(accessDeniedException instanceof MissingCsrfTokenException) && !(accessDeniedException instanceof InvalidCsrfTokenException)) {
                if(!response.isCommitted()) {
                    errPage = this.errorPage;
                }
            } else {
                errPage = this.csrfErrorPage;
            }

            RequestDispatcher dispatcher1 = request.getRequestDispatcher(errPage);
            dispatcher1.forward(request, response);
        }

    }

    public void setErrorPage(String errorPage) {
        if(!StringUtils.isBlank(errorPage) && errorPage.startsWith("/")) {
            this.errorPage = errorPage;
        } else {
            throw new IllegalArgumentException("error page must begin with \'/\'");
        }
    }

    public void setCsrfErrorPage(String csrfErrorPage) {
        if(!StringUtils.isBlank(csrfErrorPage) && csrfErrorPage.startsWith("/")) {
            this.csrfErrorPage = csrfErrorPage;
        } else {
            throw new IllegalArgumentException("csrf error page must begin with \'/\'");
        }
    }
}
