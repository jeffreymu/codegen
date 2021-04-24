package com.cjhxfund.autocode.model.out.service;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/15.
 */
@Data
@Builder
@ToString
public class RpcService implements Serializable {

    private String hashKey;
    private String serviceName;
    private String componentName;
    private String namespaceL1;
    private String namespaceL2;


}
