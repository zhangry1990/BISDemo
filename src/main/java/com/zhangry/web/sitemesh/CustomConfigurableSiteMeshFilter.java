package com.zhangry.web.sitemesh;

import com.zhangry.web.sitemesh.CustomContentProcessor;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomConfigurableSiteMeshFilter extends ConfigurableSiteMeshFilter {
    public CustomConfigurableSiteMeshFilter() {
    }

    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.setCustomContentProcessor(new CustomContentProcessor());
    }
}