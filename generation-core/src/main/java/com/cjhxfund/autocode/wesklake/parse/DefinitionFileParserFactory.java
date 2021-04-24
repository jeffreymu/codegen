package com.cjhxfund.autocode.wesklake.parse;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.parse.common.*;
import com.cjhxfund.autocode.wesklake.parse.db.DBInitDataParser;
import com.cjhxfund.autocode.wesklake.parse.db.DBSequenceParser;
import com.cjhxfund.autocode.wesklake.parse.db.DBTableParser;
import com.cjhxfund.autocode.wesklake.parse.mdb.MDBTableTypeGroupParser;
import com.cjhxfund.autocode.wesklake.parse.mdb.MDBTableTypeParser;
import com.cjhxfund.autocode.wesklake.parse.mdb.MDBTablesParser;
import com.cjhxfund.autocode.wesklake.parse.rpc.RpcInterfaceParser;
import com.cjhxfund.autocode.wesklake.parse.syserror.SystemErrorParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2021/3/21.
 */
public class DefinitionFileParserFactory {

    public final static Map<String, XmlFileParser> fileParsers = new HashMap<>();

    /**
     * cache all definition xml files
     */
    static {
        fileParsers.put(WestLakeSourceFileConfig.SYSTEM_MODULE_CACHE_KEY, new ModuleParser());
        fileParsers.put(WestLakeSourceFileConfig.DB_TABLE_CACHE_KEY, new DBTableParser());
        fileParsers.put(WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY, new DBSequenceParser());
        fileParsers.put(WestLakeSourceFileConfig.DB_INIT_DATA_CACHE_KEY, new DBInitDataParser());

        fileParsers.put(WestLakeSourceFileConfig.SUB_SYSTEM_CACHE_KEY, new SubSystemParser());
        fileParsers.put(WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY, new SystemErrorParser());
        fileParsers.put(WestLakeSourceFileConfig.DICT_CACHE_KEY, new SystemDictParser());
        fileParsers.put(WestLakeSourceFileConfig.STD_TYPE_CACHE_KEY, new SystemStdTypeParser());
        fileParsers.put(WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY, new SystemStdFieldParser());

        fileParsers.put(WestLakeSourceFileConfig.RPC_INTERFACE_CACHE_KEY, new RpcInterfaceParser());
        fileParsers.put(WestLakeSourceFileConfig.MDB_TABLE_CACHE_KEY, new MDBTablesParser());
        fileParsers.put(WestLakeSourceFileConfig.MDB_TABLE_TYPE_CACHE_KEY, new MDBTableTypeParser());
        fileParsers.put(WestLakeSourceFileConfig.MDB_TABLE_TYPE_GROUP_CACHE_KEY, new MDBTableTypeGroupParser());

    }

    public static XmlFileParser get(String key) {
        if (!fileParsers.containsKey(key)) {
            throw new RuntimeException("File parser was not instanced of " + key);
        }
        return fileParsers.get(key);
    }
}
