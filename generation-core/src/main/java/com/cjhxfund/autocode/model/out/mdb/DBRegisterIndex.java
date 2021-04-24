package com.cjhxfund.autocode.model.out.mdb;

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
public class DBRegisterIndex implements Serializable {

    private int tableId;
    private int id;
    private boolean flag;
    private String fields;

}
