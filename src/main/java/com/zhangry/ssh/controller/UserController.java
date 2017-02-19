package com.zhangry.ssh.controller;


import com.thinvent.wxgl.uc.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构管理
 *
 */
@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 进入用户管理
     * @return
     */
    @RequestMapping
    public ModelAndView users() {
        Map<String, Object> condition = new HashMap<>();
        ModelAndView modelAndView = new ModelAndView("users");

        return modelAndView;
    }


}
