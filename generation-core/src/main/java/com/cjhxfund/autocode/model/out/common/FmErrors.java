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
public class FmErrors implements Serializable {

    private String hashKey;
    private String sysNamespace;
    private SystemError[] errors;

}
