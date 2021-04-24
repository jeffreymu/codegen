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
public class MDBSequenceInfo implements Serializable {

    private int id;
    private String enName;
    private String chName;
    private int initValue;
    private boolean dailyReset;
    private boolean oracle;
    private boolean continuous;
    private String mdbList;
    private String recoverySrc;
    private String recoverySqlOracle;
    private String recoverySqlMysql;
    private String remark;
    
}
