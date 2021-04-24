package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.cache.SystemFieldsRetriever;
import com.cjhxfund.autocode.model.out.mdb.MDBSubTable;
import com.cjhxfund.autocode.model.out.mdb.MDBTable;
import com.cjhxfund.autocode.model.out.mdb.MDBTableEnum;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.MDBTableType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.SubsystemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Component
public class MDBTableAssembler implements ContentFileAssembler<MDBTable, MDBTableType> {

    private final static String MDB_CXX_FILE_HASH_KEY = "#";
    private final static String MDB_TABLE_DEFAULT_NAMESPACE = "rxmdb_trade";

    @Autowired
    private SystemFieldsRetriever fieldsRetriever;

    @Override
    public MDBTable assemble(String subSystem, String moduleName, String fileName, MDBTableType tableType) {
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(subSystem);
        MDBTable mdbTable = MDBTable
                .builder()
                .hashKey(MDB_CXX_FILE_HASH_KEY)
                .namespace(MDB_TABLE_DEFAULT_NAMESPACE)
                .name("")
                .module((subsystemType != null) ? subsystemType.getEnName() : "")
                .upperModule((subsystemType != null) ? subsystemType.getEnName().toUpperCase() : "")
                .build();

        mdbTable.setEnumName("");
        mdbTable.setEnums(assembleEnums(tableType));
        mdbTable.setLastEnum(assembleEnum(tableType));
        mdbTable.setTables(assembleSubTables(tableType));
        mdbTable.setTables(assembleSequences(tableType));

        return mdbTable;
    }

    private MDBSubTable[] assembleSequences(MDBTableType tableType) {
        return new MDBSubTable[0];
    }

    private MDBSubTable[] assembleSubTables(MDBTableType tableType) {
        return new MDBSubTable[0];
    }

    private MDBTableEnum assembleEnum(MDBTableType tableType) {
        return null;
    }

    private MDBTableEnum[] assembleEnums(MDBTableType tableType) {
        return new MDBTableEnum[0];
    }

    private String findSubSystemEnName(String subSystem) {
        SubsystemType subsystemType = fieldsRetriever.findSubSystem(subSystem);
        if (subsystemType == null)
            throw new RuntimeException("Subsystem is not defined");
        return subsystemType.getEnName();
    }
}
