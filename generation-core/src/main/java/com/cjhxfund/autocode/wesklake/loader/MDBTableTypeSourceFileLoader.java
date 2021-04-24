package com.cjhxfund.autocode.wesklake.loader;

import com.cjhxfund.autocode.cache.DataCacheManager;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableGroupType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBTableType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.ArrayOfMDBType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.MDBTableType;
import com.cjhxfund.autocode.wesklake.parse.DefinitionFileParserFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class MDBTableTypeSourceFileLoader implements SourceDefFileLoader {

    @Autowired
    private DataCacheManager cache;

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    /**
     * Key: table chinese name
     * Value: MDB table ID of table module
     */
    private Map<String, String> mdbTables = new HashMap<String, String>();

    @PostConstruct
    private void init() {
        load(WestLakeSourceFileConfig.MDB_TYPE_SOURCE_XML_FILE_NAME,
                ArrayOfMDBTableType.class, ArrayOfMDBType.class, ArrayOfMDBTableGroupType.class);
    }

    @Override
    public void load(String fileName, Class... clazz) {
        ArrayOfMDBType arrayOfMDBType = null;
        ArrayOfMDBTableType arrayOfMDBTableType = null;
        ArrayOfMDBTableGroupType arrayOfMDBTableGroupType = null;

        File mdbTableFile = new File(westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + WestLakeSourceFileConfig.MDB_TABLE_SOURCE_XML_FILE_NAME);
        File mdbTypeFile = new File(westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + WestLakeSourceFileConfig.MDB_TYPE_SOURCE_XML_FILE_NAME);
        File mdbGroupFile = new File(westLakeHome
                + WestLakeSourceFileConfig.COMMON_FILES_PATH
                + WestLakeSourceFileConfig.MDB_GROUP_SOURCE_XML_FILE_NAME);
        try {
            arrayOfMDBTableType = (ArrayOfMDBTableType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_CACHE_KEY).parse(mdbTableFile, clazz[0]);
            arrayOfMDBType = (ArrayOfMDBType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_TYPE_CACHE_KEY).parse(mdbTypeFile, clazz[1]);
            arrayOfMDBTableGroupType = (ArrayOfMDBTableGroupType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_TYPE_GROUP_CACHE_KEY).parse(mdbGroupFile, clazz[2]);
        } catch (JAXBException e) {
            e.printStackTrace();
            log.error("Parse {} file error", fileName);
        }
        if (arrayOfMDBType != null) {
            log.info("Loaded {} types of in-memory db from {}", arrayOfMDBType.getMDB().size(), mdbTypeFile.getPath());
            cache.put(WestLakeSourceFileConfig.MDB_TABLE_TYPE_CACHE_KEY, arrayOfMDBType);
        }
        if (arrayOfMDBTableType != null) {
            log.info("Loaded total {} memory tables from {}", arrayOfMDBTableType.getMDBTable().size(), mdbTableFile.getPath());
            cache.put(WestLakeSourceFileConfig.MDB_TABLE_CACHE_KEY, arrayOfMDBTableType);
            List<MDBTableType> mdbTableTypes =  arrayOfMDBTableType.getMDBTable();
            mdbTableTypes.stream().forEach(t -> this.mdbTables.put(t.getChName(), t.getId()));
        }
        if (arrayOfMDBTableGroupType != null) {
            log.info("Loaded {} table groups of in-memory db from {}", arrayOfMDBTableGroupType.getMDBTableGroup().size(), mdbGroupFile.getPath());
            cache.put(WestLakeSourceFileConfig.MDB_GROUP_CACHE_KEY, arrayOfMDBTableGroupType);
        }
        log.info("Loaded {} MDB tables defined in non-duplication" , this.mdbTables.values().size());
    }

    public Map<String, String> getMDBTables() {
        return this.mdbTables;
    }

    public boolean hasThisTable(String tableCnName) {
        return this.mdbTables.containsKey(tableCnName);
    }

}
