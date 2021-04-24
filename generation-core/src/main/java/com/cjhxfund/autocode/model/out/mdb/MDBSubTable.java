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
public class MDBSubTable implements Serializable, Comparable<MDBSubTable> {

    private String name;
    private String cnName;
    private int id;
    private int size;
    private String tableFlag;
    private String flag1;
    private String flag2;
    private String flag3;
    private String comment;

    private MDBTableField[] fields;
    private DBRegisterIndex[] indexes;

    @Override
    public int compareTo(MDBSubTable o) {
        return this.getId() - o.getId() ;
    }

}
