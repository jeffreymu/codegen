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
public class DBTableCxx implements Serializable {

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
    private String pkIndexParam;

    private String[] indexOrders;
    private TableIndexCxx[] tableIndexes;
    private MDBTableEnum[] enums;
    private MDBTableEnum lastEnum;
    private String createDate;
    private MDBSubTable[] tables;
    private MDBSequenceInfo[] seqs;

    @Setter
    @Getter
    public class TableIndexCxx {
        private String no;
        private String indexName;
        private String fieldName;
    }
}
