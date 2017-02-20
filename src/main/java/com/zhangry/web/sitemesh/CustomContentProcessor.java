package com.zhangry.web.sitemesh;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Created by zhangry on 2017/2/20.
 */
public class CustomContentProcessor extends TagBasedContentProcessor {
    public CustomContentProcessor() {
        this(new TagRuleBundle[]{new CoreHtmlTagRuleBundle(), new DecoratorTagRuleBundle(), new ExSectionTagRuleBundle(), new DivExtractingTagRuleBundle()});
    }

    public CustomContentProcessor(TagRuleBundle... tagRuleBundles) {
        super(tagRuleBundles);
    }

    public Content build(CharBuffer data, SiteMeshContext siteMeshContext) throws IOException {
        WebAppContext appContext = (WebAppContext)siteMeshContext;
        return appContext.getRequest().getAttribute("exception") != null?null:(appContext.getRequest().getHeader("X-Requested-With") != null && appContext.getRequest().getHeader("X-Requested-With").equals("XMLHttpRequest")?null:super.build(data, siteMeshContext));
    }
}

