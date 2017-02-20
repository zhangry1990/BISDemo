package com.zhangry.jssh.support;

import com.google.common.collect.Lists;
import com.zhangry.security.core.ISecureObject;
import com.zhangry.security.core.userdetails.DefaultSecurityManager;
import com.zhangry.ssh.service.ResourceService;
import com.zhangry.ssh.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.access.InvalidInvocationException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomSecurityManager extends DefaultSecurityManager {
    private static Logger logger = LoggerFactory.getLogger(CustomSecurityManager.class);
    private static Map<String, Collection<ConfigAttribute>> metadataSource = null;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceService resourceService;
    private String clientType;

    public CustomSecurityManager() {
    }

    @Transactional(
            readOnly = true
    )
    public Map<String, Collection<ConfigAttribute>> loadAllConfigAttributes() {
        if(metadataSource == null) {
            metadataSource = new HashMap();
            HashSet signinCF = new HashSet();
            signinCF.add(new SecurityConfig("IS_AUTHENTICATED_ANONYMOUSLY"));
            metadataSource.put("/signin", signinCF);
            HashSet userCF = new HashSet();
            userCF.add(new SecurityConfig("user"));
            metadataSource.put("/**", userCF);
            Map configMap = this.resourceService.getAllEnabledResources();
            Iterator var6 = configMap.keySet().iterator();

            while(var6.hasNext()) {
                String key = (String)var6.next();
                ArrayList list = Lists.newArrayList();
                String[] vals = ((String)configMap.get(key)).split(",");
                String[] var8 = vals;
                int var9 = vals.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    String val = var8[var10];
                    SecurityConfig config = new SecurityConfig(val);
                    list.add(config);
                }

                metadataSource.put(key, list);
            }
        }

        return metadataSource;
    }

    public Collection<ConfigAttribute> loadConfigAttributes(ISecureObject iSecureObject) {
        throw new InvalidInvocationException("对该方法的调用是非法行为.");
    }

    public UserDetails loadUserByUsername(String username) {
        return this.userService.loadUserByUsername(username);
    }

    public String getClientType() {
        return this.clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}

