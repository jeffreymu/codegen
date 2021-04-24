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
public class ProtoFlatContent implements Serializable {

    private String protoFileName;
    private String subSystem;
    private String moduleName;
    private String packageName;

    private RpcMessage[] messages;
    private RpcInterface interfaces;

}
