package com.zhangry.security.web.access;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.*;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private ISecureObjectManager secureObjectManager;
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    public CustomFilterInvocationSecurityMetadataSource() {
    }

    public void setSecureObjectManager(ISecureObjectManager secureObjectManager) {
        this.secureObjectManager = secureObjectManager;
        this.initRequestMap();
    }

    private void initRequestMap() {
        if(this.secureObjectManager == null) {
            throw new IllegalArgumentException("secureObjectManager can not be null.");
        } else {
            Map attributes = this.secureObjectManager.loadAllConfigAttributes();
            this.requestMap = new LinkedHashMap();
            Iterator var2 = attributes.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry entry = (Map.Entry)var2.next();
                if(!this.requestMap.containsKey(entry.getKey())) {
                    this.requestMap.put(new AntPathRequestMatcher((String)entry.getKey()), entry.getValue());
                }
            }

        }
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation)object).getRequest();
        Iterator var3 = this.requestMap.entrySet().iterator();

        Map.Entry entry;
        do {
            if(!var3.hasNext()) {
                return null;
            }

            entry = (Map.Entry)var3.next();
        } while(!((RequestMatcher)entry.getKey()).matches(request));

        return (Collection)entry.getValue();
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        HashSet allAttributes = new HashSet();
        Iterator var2 = this.requestMap.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry entry = (Map.Entry)var2.next();
            allAttributes.addAll((Collection)entry.getValue());
        }

        return allAttributes;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}

