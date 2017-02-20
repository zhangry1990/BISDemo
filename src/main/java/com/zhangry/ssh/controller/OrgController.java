package com.zhangry.ssh.controller;


import com.zhangry.ssh.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 机构管理
 *
 */
@Controller
@RequestMapping(value = "/orgs")
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    /**
     * 进入机构管理
     * @return
     */
    @RequestMapping
    public ModelAndView orgs() {
        Map<String, Object> condition = new HashMap<String, Object>();
        String orgTree = orgService.getOrgTree(condition);

        ModelAndView modelAndView = new ModelAndView("orgs");
        modelAndView.addObject("orgTree", orgTree);

        return modelAndView;
    }


}
