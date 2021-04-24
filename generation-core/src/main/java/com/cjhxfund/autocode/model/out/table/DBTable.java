package com.cjhxfund.autocode.model.out.table;

import com.cjhxfund.autocode.model.out.mdb.DBTableCxx;
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
public class DBTable implements Serializable {

    // general property
    private String subSystem;
    private String tableModuleName;
    private String tableFileName;

    // history table
    private boolean hasHisTable;
    private String hisName;
    private String hisShadowName;

    // check table
    private boolean needDBCheck;
    private boolean needMDBCheck;
    private String checkName;
    private String checkShadowName;

    // properties of table
    private String name;
    private String chineseName;
    private String shadowName;
    private TableField[] fields;
    private TableField lastField;
    private TableIndex pkIndex;
    private TableIndex[] indexes;

    // database table cxx file
    private DBTableCxx dbTableCxx;
    private FileType fileType;
    private TableIndex[] allIndexes;

}
