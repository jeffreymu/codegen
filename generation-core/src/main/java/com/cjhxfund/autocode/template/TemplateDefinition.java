package com.cjhxfund.autocode.template;

import lombok.Builder;
import lombok.Data;
import org.stringtemplate.v4.STGroup;

/**
 * Created by Jeffrey on 2021/3/20.
 */
@Data
@Builder
public class TemplateDefinition {

    private String stgFileName;
    private STGroup stGroup;
}
