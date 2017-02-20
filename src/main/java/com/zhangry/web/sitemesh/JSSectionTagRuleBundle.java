package com.zhangry.web.sitemesh;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * Created by zhangry on 2017/2/20.
 */

public class JSSectionTagRuleBundle implements TagRuleBundle {
    public JSSectionTagRuleBundle() {
    }

    public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        defaultState.addRule("js-section", new ExportTagToContentRule(siteMeshContext, (ContentProperty) contentProperty.getChild("js-section"), false));
    }

    public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
    }
}
