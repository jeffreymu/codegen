package com.cjhxfund.autocode.model.out.common;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
@ToString
public class SystemDict implements Serializable {
    private String hashKey;
    private String namespaceLevel1;
    private String namespaceLevel2;
    private String namespaceLevel3;
    private String name;
    private String cnName;
    private String upperName;
    private DictEnum[] enums;
    private DictEnum lastEnum;
}
