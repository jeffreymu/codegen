package com.cjhxfund.autocode.model.out.mdb;

import lombok.*;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/15.
 */
@Data
@Builder
@ToString
public class RecoverySequence implements Serializable {

    private String name;
    private RecoveryPair[] pairs;
    private SqlPair sqlPair;

    @Data
    public static class RecoveryPair {
        private String recoveryName;
        private String recoveryId;
    }

    @Data
    public static class SqlPair {
        private String mysqlSql;
        private String oracleSql;
    }
}
