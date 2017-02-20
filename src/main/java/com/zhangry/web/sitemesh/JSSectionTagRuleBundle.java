package com.zhangry.web.sitemesh;

/**
 * Created by zhangry on 2017/2/20.
 */

public class JSSectionTagRuleBundle implements TagRuleBundle {
    public JSSectionTagRuleBundle() {
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("js-section", new ExportTagToContentRule(siteMeshContext, (ContentProperty)contentProperty.getChild("js-section"), false));
    }

    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
