package com.cjhxfund.autocode.scanner;

import com.cjhxfund.autocode.model.Pair;
import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.loader.MDBTableTypeSourceFileLoader;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ModulePropertyType;
import com.cjhxfund.autocode.wesklake.model.xsd.table.TableType;
import com.cjhxfund.autocode.wesklake.parse.DefinitionFileParserFactory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class DBTableModuleDefinition {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    private final static String DB_TABLE_CATEGORY_DIR = "/data/table";
    private final static String TABLE_DEFINITION_CLASSIFIER = "table_";

    /**
     * table modules: key is table category
     * value is sub-system ID
     * find it when generate table script into special path
     */
    private Map<String, String> tableCategory = new TreeMap<>(new Comparator<String>() {
        public int compare(String obj1, String obj2) {
            return obj1.compareTo(obj2);
        }
    });

    /**
     * Key: Sub-system ID of table module
     * Value: List of tables in this module
     */
    private Multimap<String, String> tablesOfModule = ArrayListMultimap.create();

    /**
     * Key: table english name
     * Value: pair of table id && chinese name
     */
    private Map<String, Pair<String, String>> tableNamePairs = new ConcurrentHashMap<>();
    private Map<String, TableType> tableTypeMap = new ConcurrentHashMap<>();

    public final static String TABLE_NAME_START_CHARTS = "table_";
    public final static String DEFINITION_FILE_NAME_EXT_CHARTS = ".xml";

    @Autowired
    private MDBTableTypeSourceFileLoader sourceFileLoader;

    private ThreadPoolExecutor threadPool = null;

    @PostConstruct
    private void init() {
        threadPool = new ThreadPoolExecutor(10, 15, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        loadTableCategory();
    }

    public TableType findTableTypeByName(String tableName) {
        return tableTypeMap.get(tableName);
    }

    public String findTableID(String tableCName) {
        return sourceFileLoader.getMDBTables().get(tableCName);
    }

    public Pair<String, String> findTablePair(String key) {
        return tableNamePairs.get(key);
    }

    public String findTableIDByName(String tableName) {
        if (findTablePair(tableName) == null)
            return null;
        return tableNamePairs.get(tableName).getFirstMember();
    }

    public String findSubSystemID(String tableCategory) {
        return this.tableCategory.get(tableCategory);
    }

    public List<String> findTablesOfModule(String subSystem) {
        return (List<String>) tablesOfModule.get(subSystem);
    }

    public void loadTableCategory() {
        if (westLakeHome == null) {
            throw new IllegalArgumentException("Must define west-lake installation path");
        }
        tableCategory.clear();
        String tablePath = westLakeHome + DB_TABLE_CATEGORY_DIR;
        File[] files = new File(tablePath).listFiles(file -> file.isDirectory());
        CountDownLatch countDownLatch = new CountDownLatch(files.length);
        Stream.of(Optional.ofNullable(files).orElse(new File[]{}))
                .map(File::getName).forEach(e -> loadTableModules(tablePath, e, countDownLatch));

        log.info("++++++++++++++++++++++++++++++++++++");
        log.info("Tables definition path: {}", tablePath);
        tableCategory.forEach((key, value) -> log.info("T-Category: " + key + "->" + value));
        log.info("++++++++++++++++++++++++++++++++++++");
        try {
            countDownLatch.await();
            log.info("Loaded {} table name pairs ", tableNamePairs.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadTableModules(String tablePath, String category, CountDownLatch countDownLatch) {
        List<File> fileList = (List<File>) FileUtils.listFiles(
                new File(tablePath + File.separator + category), new String[]{"xml"}, false);

        if (fileList.isEmpty() || category == null) {
            return;
        }
        ModulePropertyType modulePropertyType = null;
        try {
            modulePropertyType = (ModulePropertyType) DefinitionFileParserFactory.get(
                    WestLakeSourceFileConfig.SYSTEM_MODULE_CACHE_KEY).parse(
                    fileList.get(0), ModulePropertyType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        if (modulePropertyType != null) {
            String subSystemID = modulePropertyType.getSubsystemId();
            tableCategory.put(category, subSystemID);
            TableFileScanThread scanThread = new TableFileScanThread(subSystemID, tablePath, category, countDownLatch);
            threadPool.execute(scanThread);
        }
    }

    class TableFileScanThread implements Runnable {

        private String subSystem;
        private String tablePath;
        private String category;
        private CountDownLatch latching;

        public TableFileScanThread(String subSystem, String tablePath, String category, CountDownLatch latching) {
            this.subSystem = subSystem;
            this.tablePath = tablePath;
            this.category = category;
            this.latching = latching;
        }

        @Override
        public void run() {
            loadTablesOfModule(subSystem, tablePath, category);
            latching.countDown();
            log.info("Scan table category [{}] is completed", category);
        }

        private void loadTablesOfModule(String subSystem, String tablePath, String category) {
            List<File> fileList = (List<File>) FileUtils.listFiles(
                    new File(tablePath + File.separator + category), new String[]{"xml"}, true);
            if (fileList == null || fileList.size() < 0) {
                return;
            }
            try {
                for (File f : fileList) {
                    if (f.getName().startsWith(TABLE_DEFINITION_CLASSIFIER) && f.getName().endsWith(".xml")) {
                        TableType tableType = (TableType) DefinitionFileParserFactory.get(
                                WestLakeSourceFileConfig.DB_TABLE_CACHE_KEY).parse(f, TableType.class);
                        String tableCnName = f.getName().substring(TABLE_NAME_START_CHARTS.length(),
                                f.getName().length() - DEFINITION_FILE_NAME_EXT_CHARTS.length());
                        if (!sourceFileLoader.hasThisTable(tableCnName)) {
                            // log.warn("Not define this table {} in MDB tables", tableCnName);
                            continue;
                        }
                        String tableEnName = tableType.getEnname();
                        String tableID = findTableID(tableCnName);
                        Pair<String, String> tablePair = new Pair<>(tableID, tableCnName);
                        tableNamePairs.put(tableEnName, tablePair);
                        tableTypeMap.put(tableType.getEnname(), tableType);
                        tablesOfModule.put(subSystem, tableType.getEnname());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
