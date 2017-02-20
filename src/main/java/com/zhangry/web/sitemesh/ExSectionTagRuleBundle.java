package com.zhangry.web.sitemesh;

/**
 * Created by zhangry on 2017/2/20.
 */
public class ExSectionTagRuleBundle implements TagRuleBundle {
    public ExSectionTagRuleBundle() {
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("ex-section", new ExSectionExtractingRule(siteMeshContext, (ContentProperty)contentProperty.getChild("ex-section")));
    }

    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
