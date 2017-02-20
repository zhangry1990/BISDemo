package com.zhangry.web.sitemesh;

import com.zhangry.web.sitemesh.ExSectionExtractingRule;
import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.tagprocessor.State;

/**
 * Created by zhangry on 2017/2/20.
 */
public class ExSectionTagRuleBundle implements TagRuleBundle {
    public ExSectionTagRuleBundle() {
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("ex-section", new ExSectionExtractingRule(siteMeshContext, (ContentProperty) contentProperty.getChild("ex-section")));
    }

    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
