package com.cjhxfund.autocode.disruptor.handler;

import com.cjhxfund.autocode.cache.WestLakeSourceDataCache;
import com.cjhxfund.autocode.disruptor.event.*;
import com.cjhxfund.autocode.disruptor.wrapper.ContentDisruptorWrapper;
import com.cjhxfund.autocode.generator.assembler.DataAssembleManager;
import com.cjhxfund.autocode.model.out.common.FmErrors;
import com.cjhxfund.autocode.model.out.common.SystemDictHxx;
import com.cjhxfund.autocode.model.out.mdb.DBTableCxx;
import com.cjhxfund.autocode.model.out.mdb.DBTableImplCxx;
import com.cjhxfund.autocode.model.out.mdb.MDBRecoverySequence;
import com.cjhxfund.autocode.model.out.mdb.MDBTable;
import com.cjhxfund.autocode.model.out.proto.ProtoFlatContent;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.model.out.table.DBInitData;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.Dictionaries;
import com.cjhxfund.autocode.model.out.table.Sequences;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.base.BasicDataType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ModulePropertyType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.ArrayOfDictType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.error.ArrayOfSystemErrorType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.feild.ArrayOfStdfieldType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.mdb.*;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import com.cjhxfund.autocode.wesklake.parse.DefinitionFileParserFactory;
import com.cjhxfund.autocode.wesklake.util.SystemDictDiffer;
import com.cjhxfund.autocode.wesklake.util.XmlContentFilter;
import com.google.common.collect.Sets;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Watchable file handler object, which can listen the event changed files
 * such as database file, interface file and common defined file under west leak tool.
 * To build the target write data for different event type, and publish the final
 * content event data to queue
 */
@Slf4j
@Component
@Scope("prototype")
public class WatchableFileHandler implements WorkHandler<ValueEvent> {

    public final static String DB_TABLE_MODULE_KEY = "module.xml";
    public final static String DEFINITION_FILE_NAME_EXT_CHARTS = ".xml";

    public final static String TABLE_NAME_START_CHARTS = "table_";
    public final static String RPC_INTERFACE_NAME_START_CHARTS = "interface_";

    @Autowired
    private ContentDisruptorWrapper outputDisruptor;

    @Autowired
    private WestLakeSourceDataCache westLakeSourceDataCache;

    @Autowired
    private DataAssembleManager dataAssembleManager;

    @Override
    public void onEvent(ValueEvent event) throws Exception {
        ValueEvent.EventType eventType = event.getEventType();
        switch (eventType) {
            case DB_TABLE:
                log.info("Receive: " + ((DBTableEventData)event.getValue()).getFile().getPath());
                handleDBTable((DBTableEventData)event.getValue());
                break;
            case TABLE_MODULE:
                log.info("Receive: " + ((DBTableModuleEventData)event.getValue()).getFile().getPath());
                handleTableModule((DBTableModuleEventData)event.getValue());
                break;
            case SYSTEM_DICT:
                log.info("Receive: " + ((SystemDictEventData)event.getValue()).getFile().getName());
                handleSystemDict((SystemDictEventData)event.getValue());
                break;
            case SYSTEM_ERROR:
                log.info("Receive: " + ((SystemErrorEventData)event.getValue()).getFile().getName());
                handleSystemError((SystemErrorEventData)event.getValue());
                break;
            case SYSTEM_FIELD:
                log.info("Receive: " + ((SystemFieldEventData)event.getValue()).getFile().getName());
                handleSystemField((SystemFieldEventData)event.getValue());
                break;
            case INIT_DATA:
                log.info("Receive: " + ((DBInitDataEventData)event.getValue()).getFile().getName());
                handleDBInitData((DBInitDataEventData)event.getValue());
                break;
            case MDB_CATEGORY:
                log.info("Receive: " + ((MDBCategoryEventData)event.getValue()).getFile().getName());
                handleMDBCategory((MDBCategoryEventData)event.getValue());
                break;
            case MDB_GROUP:
                log.info("Receive: " + ((MDBGroupEventData)event.getValue()).getFile().getName());
                handleMDBTableGroup((MDBGroupEventData)event.getValue());
                break;
            case MDB_TABLE:
                log.info("Receive: " + ((MDBTableEventData)event.getValue()).getFile().getName());
                handleMDBTable((MDBTableEventData)event.getValue());
                break;
            case MDB_SEQUENCE:
                log.info("Receive: " + ((MDBSequenceEventData)event.getValue()).getFile().getName());
                handleMDBSequence((MDBSequenceEventData)event.getValue());
                break;
            case PROTO_FILE:
                log.info("Receive: " + ((ProtoFileEventData)event.getValue()).getFile().getPath());
                break;
            case RPC_INTERFACE:
                log.info("Receive: " + ((RpcInterfaceEventData)event.getValue()).getFile().getPath());
                handleRPCInterface((RpcInterfaceEventData)event.getValue());
                break;
            default:
                break;
        }
    }

    private void handleSystemField(SystemFieldEventData value) {
        File dictFile = new File(value.getFile().getPath());
        ArrayOfStdfieldType arrayOfStdfieldType = null;
        try {
            arrayOfStdfieldType = (ArrayOfStdfieldType) DefinitionFileParserFactory.get(WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY)
                    .parse(dictFile, ArrayOfStdfieldType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (arrayOfStdfieldType != null) {
            log.info("Update system field cache using updated field typs");
            westLakeSourceDataCache.put(WestLakeSourceFileConfig.STD_FIELD_CACHE_KEY, arrayOfStdfieldType);
        }
    }

    private void handleSystemError(SystemErrorEventData value) {
        ArrayOfSystemErrorType cachedSystemErrors = (ArrayOfSystemErrorType) westLakeSourceDataCache.get(
                WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY);
        log.info("Cached system error has {}", cachedSystemErrors.getSystemError().size());
        File errorFile = new File(value.getFile().getPath());
        ArrayOfSystemErrorType updatedSystemErrors = null;
        try {
            updatedSystemErrors = (ArrayOfSystemErrorType) DefinitionFileParserFactory.get(WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY)
                    .parse(errorFile, ArrayOfSystemErrorType.class);
            westLakeSourceDataCache.put(WestLakeSourceFileConfig.SYS_ERROR_CACHE_KEY, updatedSystemErrors);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        log.info("Updated system error has {}", updatedSystemErrors.getSystemError().size());
        FmErrors fmErrors = dataAssembleManager.getSystemErrorAssembler().assemble(null, null, null, updatedSystemErrors);
        publishFmErrors(fmErrors);
    }

    private void handleDBInitData(DBInitDataEventData value) {
        String fileName = value.getFile().getPath();
        String tDataFileName = value.getFile().getName();
        String cnName = tDataFileName.substring(6, tDataFileName.indexOf("."));
        log.info("Data file name: " + cnName);
        try {
            String xmlContent = XmlContentFilter.filter(fileName);
            InputStream inputStream = IOUtils.toInputStream(xmlContent, StandardCharsets.UTF_8.name());
            BasicDataType basicDataType = (BasicDataType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.DB_INIT_DATA_CACHE_KEY).parse(inputStream, BasicDataType.class);
            DBInitData dbInitData = dataAssembleManager.getInitDataSqlAssembler().assemble(
                    null, null, cnName, basicDataType);
            publishDBInitData(dbInitData);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log.info("Cached database init data has {}");
    }

    private void handleSystemDict(SystemDictEventData value) {
        ArrayOfDictType cachedDicts = (ArrayOfDictType) westLakeSourceDataCache.get(
                WestLakeSourceFileConfig.DICT_CACHE_KEY);
        log.info("Cached system dict has {}", cachedDicts.getDict().size());
        File dictFile = new File(value.getFile().getPath());
        ArrayOfDictType changedDicts = null;
        List<DictType> diffDicts = new ArrayList<>();
        try {
            changedDicts = (ArrayOfDictType) DefinitionFileParserFactory.get(WestLakeSourceFileConfig.DICT_CACHE_KEY)
                    .parse(dictFile, ArrayOfDictType.class);
            westLakeSourceDataCache.put(WestLakeSourceFileConfig.DICT_CACHE_KEY, changedDicts);
            log.info("Changed system dict has {}", changedDicts.getDict().size());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        diffDicts = SystemDictDiffer.reduceAll(changedDicts.getDict(), cachedDicts.getDict());
        log.info("Diff system dict has {}", diffDicts.size());
        SystemDictHxx systemDictHxx = dataAssembleManager.getSystemDictHxxAssembler().assemble(null, null, null, diffDicts);
        publishSystemDict(systemDictHxx);

        Dictionaries dictionaries = dataAssembleManager.getSystemDictSqlAssembler().assemble(
                null, null, null, changedDicts);
        publishDBDictionary(dictionaries);
    }

    private void handleMDBSequence(MDBSequenceEventData value) {
        ArrayOfSequenceType cachedDBSequences = (ArrayOfSequenceType) westLakeSourceDataCache.get(
                WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY);
        log.info("Cached DB sequence has {}", cachedDBSequences.getSequence().size());
        File sequenceFile = new File(value.getFile().getPath());
        try {
            cachedDBSequences = (ArrayOfSequenceType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.DB_TABLE_SEQUENCE_CACHE_KEY)
                    .parse(sequenceFile, ArrayOfSequenceType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        log.info("Changed DB sequence has {}", cachedDBSequences.getSequence().size());
        MDBRecoverySequence mdbRecoverySequence = dataAssembleManager.getSequenceRecoveryAssembler().assemble(
                null, null, null, cachedDBSequences);
        publishMDBRecoverySequence(mdbRecoverySequence);

        Sequences dbSequences = dataAssembleManager.getDbSequenceAssembler().assemble(
                null, null, null, cachedDBSequences);
        publishDBSequence(dbSequences);
    }

    private void handleMDBTable(MDBTableEventData value) {
        ArrayOfMDBTableType cachedMDBTables = (ArrayOfMDBTableType) westLakeSourceDataCache.get(
                WestLakeSourceFileConfig.MDB_TABLE_CACHE_KEY);
        log.info("Cached MDB tables has {}", cachedMDBTables.getMDBTable().size());

        ArrayOfMDBTableType arrayOfMDBTableType = null;
        Set<String> cachedObj = new HashSet<>();
        Set<String> changedObj = new HashSet<>();
        Map<String, MDBTableType> changedMap = new ConcurrentHashMap<>();
        if (cachedMDBTables != null) {
            cachedMDBTables.getMDBTable().stream().forEach(e -> cachedObj.add(e.getId()));
        }

        try {
            arrayOfMDBTableType = (ArrayOfMDBTableType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_CACHE_KEY).parse(value.getFile(), ArrayOfMDBTableType.class);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List<MDBTableType> mdbTableTypes = arrayOfMDBTableType.getMDBTable();
        mdbTableTypes.stream().forEach(e -> {
            changedObj.add(e.getId());
            changedMap.put(e.getId(), e);
        });
        log.info("Changed MDB table size is {}", mdbTableTypes.size());
        Sets.SetView<String> diffResult = Sets.symmetricDifference(cachedObj, changedObj);
        for (String tableID : diffResult) {
            handleSingleMDBTable(changedMap.get(tableID));
        }
    }

    private void handleSingleMDBTable(MDBTableType table) {
        log.info("Handle MDB table {}", table.getChName());
        MDBTable mdbTable = dataAssembleManager.getMdbTableAssembler().assemble(
                table.getDatabaseName(), table.getDatabaseName(), null, table);
        publishMDBTable(mdbTable);
    }

    private void handleMDBCategory(MDBCategoryEventData value) {
        try {
            ArrayOfMDBType arrayOfMDBType = (ArrayOfMDBType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_TYPE_CACHE_KEY).parse(value.getFile(), ArrayOfMDBType.class);
            List<MDBType> mdbTypes = arrayOfMDBType.getMDB();
            log.info("Refresh MDB type cache...");
            westLakeSourceDataCache.put(WestLakeSourceFileConfig.MDB_TABLE_TYPE_CACHE_KEY, mdbTypes);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void handleMDBTableGroup(MDBGroupEventData value) {
        try {
            ArrayOfMDBTableGroupType arrayOfMDBTableGroupType = (ArrayOfMDBTableGroupType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.MDB_TABLE_TYPE_GROUP_CACHE_KEY).parse(value.getFile(), ArrayOfMDBTableGroupType.class);
            List<MDBTableGroupType> mdbTableGroupTypes = arrayOfMDBTableGroupType.getMDBTableGroup();
            log.info("Refresh MDB group type cache...");
            westLakeSourceDataCache.put(WestLakeSourceFileConfig.MDB_TABLE_TYPE_GROUP_CACHE_KEY, mdbTableGroupTypes);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void handleRPCInterface(RpcInterfaceEventData value) {
        File interfaceServiceFile = value.getFile();
        String interfaceFileName = interfaceServiceFile.getName().substring(RPC_INTERFACE_NAME_START_CHARTS.length(),
                interfaceServiceFile.getName().length() - DEFINITION_FILE_NAME_EXT_CHARTS.length());
        File moduleFile = new File(value.getFile().getParentFile() + File.separator + DB_TABLE_MODULE_KEY);
        String moduleName = value.getFile().getParentFile().getName();
        try {
            ModulePropertyType modulePropertyType = (ModulePropertyType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.SYSTEM_MODULE_CACHE_KEY).parse(moduleFile, ModulePropertyType.class);
            String subSystem = retrieveSubSystemID(moduleFile);
            ServiceInterfaceType serviceInterfaceType = (ServiceInterfaceType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.RPC_INTERFACE_CACHE_KEY).parse(interfaceServiceFile, ServiceInterfaceType.class);

            ProtoFlatContent flatContent = dataAssembleManager.getRpcInterfaceAssembler().assemble(
                    subSystem, moduleName, serviceInterfaceType.getSummary().getName(), serviceInterfaceType);
            publishProtoContent(flatContent);

            RpcServiceRegister rpcServiceRegister = dataAssembleManager.getRpcServiceCxxAssembler().assemble(
                    subSystem, moduleName, serviceInterfaceType.getSummary().getName(), serviceInterfaceType);
            publishRPCService(rpcServiceRegister);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void handleTableModule(DBTableModuleEventData value) {
        List<File> fileList = (List<File>) FileUtils.listFiles(
                value.getFile(), new String[]{"xml"}, true);
        fileList.stream().forEach(f -> log.info(f.getName()));
    }

    private void handleDBTable(DBTableEventData value) {
        File tableFile = value.getFile();
        String tableFileName = tableFile.getName().substring(TABLE_NAME_START_CHARTS.length(),
                tableFile.getName().length() - DEFINITION_FILE_NAME_EXT_CHARTS.length());
        File moduleFile = new File(value.getFile().getParentFile() + File.separator + DB_TABLE_MODULE_KEY);
        String moduleName = value.getFile().getParentFile().getName();
        DBTable dbTable = null;
        try {
            TableType tableType = (TableType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.DB_TABLE_CACHE_KEY).parse(tableFile, TableType.class);
            String subSystem = retrieveSubSystemID(moduleFile);
            dbTable = dataAssembleManager.getDbTableAssembler().assemble(subSystem, moduleName, tableFileName, tableType);
            publishDBTable(dbTable);

            MDBTable mdbTable = dataAssembleManager.getMdbTableAssembler().assemble(
                    subSystem, moduleName, null, null);
            publishMDBTable(mdbTable);

            if (dbTable == null)
                return;
            publishDBTableEx(dataAssembleManager.getDbTableCxxAssembler().assembleCxxFile(dbTable));
            publishDBTableEx(dataAssembleManager.getDbTableImplCxxAssembler().assembleCxxImplFile(dbTable));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private String retrieveSubSystemID(File moduleFile) throws JAXBException {
        ModulePropertyType modulePropertyType = (ModulePropertyType) DefinitionFileParserFactory.get(
                WestLakeSourceFileConfig.SYSTEM_MODULE_CACHE_KEY).parse(moduleFile, ModulePropertyType.class);
        log.info("Interface module id is {}", modulePropertyType.getSubsystemId());
        return modulePropertyType.getSubsystemId();
    }

    /*************** PUBLISH KINDS OF STUFF TO GENERATORS ***************/
    public void publishDBTable(DBTable dbTable) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.FILE_CONTENT)
                .targetFileName(dbTable.getTableFileName())
                .contentData(dbTable)
                .build());
    }

    public void publishDBTableEx(DBTableCxx dbTable) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.FILE_CONTENT)
                .targetFileName(dbTable.getName())
                .contentData(dbTable)
                .build());
    }

    public void publishDBTableEx(DBTableImplCxx dbTable) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.FILE_CONTENT)
                .targetFileName(dbTable.getName())
                .contentData(dbTable)
                .build());
    }

    public void publishProtoContent(ProtoFlatContent protoFlatContent) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.FILE_CONTENT)
                .targetFileName(protoFlatContent.getProtoFileName())
                .contentData(protoFlatContent)
                .build());
    }

    public void publishMDBTable(MDBTable mdbTable) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.MDB_TABLE)
                .targetFileName(null)
                .contentData(mdbTable)
                .build());
    }

    public void publishDBDictionary(Dictionaries dictionaries) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.DB_DICT)
                .targetFileName(null)
                .contentData(dictionaries)
                .build());
    }

    public void publishSystemDict(SystemDictHxx systemDict) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.SYSTEM_DICT)
                .targetFileName(null)
                .contentData(systemDict)
                .build());
    }

    public void publishFmErrors(FmErrors fmErrors) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.SYSTEM_ERROR)
                .targetFileName(null)
                .contentData(fmErrors)
                .build());
    }

    public void publishDBInitData(DBInitData dbInitData) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.INIT_DATA)
                .targetFileName(null)
                .contentData(dbInitData)
                .build());
    }

    public void publishMDBRecoverySequence(MDBRecoverySequence recoverySequence) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.MDB_SEQUENCE)
                .targetFileName(null)
                .contentData(recoverySequence)
                .build());
    }

    public void publishDBSequence(Sequences sequences) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.DB_SEQUENCE)
                .targetFileName(null)
                .contentData(sequences)
                .build());
    }

    public void publishRPCService(RpcServiceRegister rpcRegister) {
        outputDisruptor.publishContent(ContentFileEventData
                .builder()
                .type(ValueEvent.EventType.RPC_INTERFACE)
                .targetFileName(null)
                .contentData(rpcRegister)
                .build());
    }
}
