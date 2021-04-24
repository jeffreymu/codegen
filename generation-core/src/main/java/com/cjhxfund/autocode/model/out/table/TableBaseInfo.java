package com.cjhxfund.autocode.model.out.table;

import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
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
public class TableBaseInfo implements Serializable {

    private String subSystem;
    private String moduleName;
    private String tableFileName;
    private TableType tableType;
}
