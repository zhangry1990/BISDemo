package com.zhangry.web.sitemesh;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CSSSectionTagRuleBundle implements TagRuleBundle {
    public CSSSectionTagRuleBundle() {
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("css-section", new ExportTagToContentRule(siteMeshContext, (ContentProperty)contentProperty.getChild("css-section"), false));
    }

    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}

