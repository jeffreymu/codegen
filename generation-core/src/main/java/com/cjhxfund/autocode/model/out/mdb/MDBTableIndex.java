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
public class MDBTableIndex implements Serializable {

    private String name;
    private int id;
    private int tableId;
    private String flag;
    private String fields;



}
