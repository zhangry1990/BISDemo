package com.zhangry.ssh.controller;

import com.thinvent.web.servlet.handler.CustomSimpleMappingExceptionResolver;
import com.thinvent.wxgl.security.support.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 默认页面控制器，如：登录页、首页、异常页面等
 *
 * @author zhaojian
 * @date 20170119
 */
@Controller
public class DefaultController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/")
    public String rootPage(Model model, HttpServletResponse response){
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void indexPage(HttpServletRequest request, HttpServletResponse response) {
        String firstUri = UserContext.getCurrentUser().getDefaultResource().getResourceUri();

        try {
            response.sendRedirect(request.getContextPath() + firstUri);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @RequestMapping("/signin")
    public String signInPage() {
        return "signin";
    }

    @RequestMapping("/signout")
    public String signOutPage() {
        return "signout";
    }

    @RequestMapping("/building")
    public String buildingPage() {
        return "building";
    }

    @RequestMapping("/errors/error_csrf")
    public ModelAndView errorCSRF(HttpServletRequest request, Model model) {
        return getModelAndViewWithException("errors/error_csrf", request);
    }

    @RequestMapping("/errors/error_403")
    public ModelAndView error403(HttpServletRequest request, Model model) {
        return getModelAndViewWithException("errors/error_403", request);
    }

    @RequestMapping("/errors/uncatched")
    public ModelAndView error(HttpServletRequest request, Model model) {
        return getModelAndViewWithException("errors/error_uncatched", request);
    }

    private ModelAndView getModelAndViewWithException(String viewName, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("error_code", request.getAttribute("javax.servlet.error.status_code"));

        Throwable ex = (Throwable) request.getAttribute("exception");
        if (ex == null) {
            ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
        }
        mv.addObject(CustomSimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE, ex);
        return mv;
    }
}
