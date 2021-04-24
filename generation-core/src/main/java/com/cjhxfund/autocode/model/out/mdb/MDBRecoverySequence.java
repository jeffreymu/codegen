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
public class MDBRecoverySequence implements Serializable {

    private String hashKey;
    private RecoverySequence[] sequences;

}
