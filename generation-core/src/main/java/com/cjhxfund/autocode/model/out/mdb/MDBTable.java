package com.cjhxfund.autocode.model.out.mdb;

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
public class MDBTable implements Serializable {

    private String hashKey;
    private String name;
    private String module;
    private String upperModule;

    private MDBSubTable[] tables;
    private String namespace;
    private String enumName;
    private MDBTableEnum[] enums;
    private MDBTableEnum lastEnum;

    private MDBSequence[] sequences;

}
