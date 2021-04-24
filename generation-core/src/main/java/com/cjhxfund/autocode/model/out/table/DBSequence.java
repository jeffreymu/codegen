package com.cjhxfund.autocode.model.out.table;

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
public class DBSequence implements Serializable {

    private String name;
    private String cnName;
    private int start;
    private long minValue;
    private long maxValue;
    private String description;

}
