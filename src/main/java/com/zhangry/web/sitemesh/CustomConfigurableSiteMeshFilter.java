package com.zhangry.web.sitemesh;

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