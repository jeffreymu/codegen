package com.cjhxfund.autocode.model.out.proto;

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
public class RpcInterface implements Serializable {

    private String interfaceName;
    private RpcInterfaceStruct[] requestList;
    private RpcInterfaceStruct[] responseList;

}
