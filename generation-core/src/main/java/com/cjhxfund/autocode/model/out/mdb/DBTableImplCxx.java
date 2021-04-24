package com.cjhxfund.autocode.model.out.mdb;

import com.cjhxfund.autocode.model.out.table.FileType;
import com.cjhxfund.autocode.model.out.table.TableField;
import lombok.*;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
@ToString
public class DBTableImplCxx implements Serializable {

    private String hashKey;
    private String name;
    private String subSystem;
    private String tableModuleName;
    private FileType fileType;

    /**
     * ********************************
     * TO GENERATE MDB TABLE CXX FILES
     */
    private int tableId;
    private String upperTableName;
    private String upperTableModuleName;
    private TableField[] tableFields;
    private String createDate;

    /**
     * MDB TABLE IMPL CXX FILE
     */
    private int pkIndexNo;
    private String pkIndexName;

    private String[] pkIndexRecords;
    private String[] diffRecords;
    private TableIndex[] indexes;
    private TableIndex[] queryIndexes;
    private TableField[] autoNumFills;
    private TableField[] autoStrFills;

    private String pkIndexCmp;
    private String pkIndexArgs;
    private String pkIndexCorrectArgs;

    private IndexCmpFunc[] cmpFuncs;
    private TableIndex pkHashFuncs;
    private TableIndex[] hashFuncs;

    @Setter
    @Getter
    public static class IndexCmpFunc {
        private String no;
        private String fieldExpression;
    }

    @Setter
    @Getter
    public static class TableIndex {
        private String tableName;
        private boolean ispk;
        private String name;
        private String no;
        private String fieldExpression;
        private String fieldArgs;
        private String[] records;
    }
}
