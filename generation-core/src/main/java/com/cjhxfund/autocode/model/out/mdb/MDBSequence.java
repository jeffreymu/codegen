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
public class MDBSequence implements Serializable {

    private String name;
    private int orderId;
    private long start;
    private String flag1;
    private String flag2;
}
