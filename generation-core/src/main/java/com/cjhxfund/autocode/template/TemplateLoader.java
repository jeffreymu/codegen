package com.cjhxfund.autocode.template;

import org.stringtemplate.v4.ST;

/**
 * Created by Jeffrey on 2021/3/18.
 */
public interface TemplateLoader<ContentFile> {

    ST pick(String stgFileName, String templateName);

    String render(ST sTemplate, String paramName, ContentFile contentFile);
}
