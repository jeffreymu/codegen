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
public class DBInitData implements Serializable {

    private String tableName;
    private String tableCnName;
    private String modeName;
    private CfgPair[] configParams;

    @Data
    public static class CfgPair {
        private int id;
        private String value;
        private String name;
    }

}
