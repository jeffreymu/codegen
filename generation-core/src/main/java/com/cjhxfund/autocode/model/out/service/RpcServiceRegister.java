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
public class RpcServiceRegister implements Serializable {

    private String hashKey;
    private String moduleName;
    private String componentName;
    private String serviceName;
    private RpcService[] services;

}
