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
public class Sequences implements Serializable {

    private DBSequence[] sequences;
}
