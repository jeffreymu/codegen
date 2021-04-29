package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdtypeType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableGroupType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.system.ArrayOfSubsystemType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonSourceFileLoaderManager {

    @Autowired
    private  DictSourceFileLoader dictSourceFileLoader;
    @Autowired
    private DBSequenceFileLoader dbSequenceFileLoader;
    @Autowired
    private MDBTableTypeSourceFileLoader mdbTableTypeSourceFileLoader;

    @Autowired
    private  BasicStdTypeSourceFileLoader basicStdTypeSourceFileLoader;
    @Autowired
    private BasicSubSystemSourceFileLoader basicSubSystemSourceFileLoader;
    @Autowired
    private BasicSystemErrorSourceFileLoader basicSystemErrorSourceFileLoader;

    public void reload() {
        log.info("Starting reload all common files...");
        dictSourceFileLoader.load(WestLakeSourceFileConfig.DICT_SOURCE_XML_FILE_NAME,
                ArrayOfDictType.class);
        dbSequenceFileLoader.load(WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_XML_FILE_NAME,
                ArrayOfSequenceType.class);
        mdbTableTypeSourceFileLoader.load(WestLakeSourceFileConfig.MDB_TYPE_SOURCE_XML_FILE_NAME,
                ArrayOfMDBTableType.class, ArrayOfMDBType.class, ArrayOfMDBTableGroupType.class);
        basicStdTypeSourceFileLoader.load(WestLakeSourceFileConfig.STD_TYPE_SOURCE_XML_FILE_NAME,
                ArrayOfStdtypeType.class, ArrayOfStdfieldType.class);
        basicSubSystemSourceFileLoader.load(WestLakeSourceFileConfig.SUB_SYSTEM_SOURCE_XML_FILE_NAME,
                ArrayOfSubsystemType.class);
        basicSystemErrorSourceFileLoader.load(WestLakeSourceFileConfig.SYS_ERROR_SOURCE_XML_FILE_NAME,
                ArrayOfSystemErrorType.class);
        log.info("Common files reloading is done");
    }
}
