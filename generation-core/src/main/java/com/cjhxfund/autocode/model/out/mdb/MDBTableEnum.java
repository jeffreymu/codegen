package com.cjhxfund.autocode.model.out.mdb;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Jeffrey on 2021/3/15.
 */
@Data
@Builder
@ToString
public class MDBTableEnum implements Comparable<MDBTableEnum> {

    private String attribute;
    private Integer number;

    @Override
    public int compareTo(MDBTableEnum o) {
        return this .getNumber().compareTo(o.getNumber());
    }
}
