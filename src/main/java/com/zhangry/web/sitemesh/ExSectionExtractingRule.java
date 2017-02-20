package com.zhangry.web.sitemesh;

import java.io.IOException;

/**
 * Created by zhangry on 2017/2/20.
 */
public class ExSectionExtractingRule extends BasicBlockRule<String> {
    private final ContentProperty targetProperty;
    private final boolean includeInContent = false;
    private final SiteMeshContext context;

    public ExSectionExtractingRule(SiteMeshContext context, ContentProperty targetProperty) {
        this.context = context;
        this.targetProperty = targetProperty;
    }

    protected String processStart(Tag tag) throws IOException {
        if(tag.hasAttribute("id", false)) {
            this.tagProcessorContext.pushBuffer(this.targetProperty.getOwningContent().createDataOnlyBuffer());
            tag.writeTo(this.tagProcessorContext.currentBuffer());
            this.tagProcessorContext.pushBuffer();
            return tag.getAttributeValue("id", false);
        } else {
            return null;
        }
    }

    protected void processEnd(Tag tag, String id) throws IOException {
        if(id != null) {
            CharSequence innerContent = this.tagProcessorContext.currentBufferContents();
            this.tagProcessorContext.popBuffer();
            this.tagProcessorContext.currentBuffer().append(innerContent);
            if(tag.getType() != Type.EMPTY) {
                tag.writeTo(this.tagProcessorContext.currentBuffer());
            }

            CharSequence outerContent = this.tagProcessorContext.currentBufferContents();
            this.tagProcessorContext.popBuffer();
            this.tagProcessorContext.currentBuffer().append(outerContent);
            if(!this.targetProperty.hasValue()) {
                ((ContentProperty)this.targetProperty.getChild(id)).setValue(innerContent);
            }
        }

    }
}

