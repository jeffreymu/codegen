package com.cjhxfund.autocode.scanner;

import com.cjhxfund.autocode.wesklake.config.WestLakeSourceFileConfig;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ModulePropertyType;
import com.cjhxfund.autocode.wesklake.model.xsd.rpc.ServiceInterfaceType;
import com.cjhxfund.autocode.wesklake.parse.DefinitionFileParserFactory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
@Component
public class RPCInterfaceDefinition {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    private final static String RPC_INTERFACE_DEFINITION_CLASSIFIER = "interface_";
    private final static String RPC_INTERFACE_CATEGORY_DIR = "/data/interface";

    private ThreadPoolExecutor threadPool = null;
    private Map<String, String> interfaceCategory = new TreeMap<>(new Comparator<String>() {
        public int compare(String obj1, String obj2) {
            return obj1.compareTo(obj2);
        }
    });
    private Multimap<String, String> interfacesOfModule = ArrayListMultimap.create();
    private Multimap<String, ServiceInterfaceType> interfacesTypeOfModule = ArrayListMultimap.create();

    @PostConstruct
    private void init() {
        threadPool = new ThreadPoolExecutor(10, 15, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
//        loadRPCInterfaceCategory();
    }

    public List<String> findRPCInterfacesOfModule(String moduleName) {
        return (List<String>) interfacesOfModule.get(moduleName);
    }

    public List<ServiceInterfaceType> findRPCInterfacesTypeOfModule(String moduleName) {
        return (List<ServiceInterfaceType>) interfacesTypeOfModule.get(moduleName);
    }

    private void loadRPCInterfaceCategory() {
        if (westLakeHome == null) {
            throw new IllegalArgumentException("Must define west-lake installation path");
        }
        interfaceCategory.clear();
        String interfacePath = westLakeHome + RPC_INTERFACE_CATEGORY_DIR;
        File[] files = new File(interfacePath).listFiles(file -> file.isDirectory());
        CountDownLatch countDownLatch = new CountDownLatch(files.length);
        Stream.of(Optional.ofNullable(files).orElse(new File[]{}))
                .map(File::getName).forEach(e -> loadInterfaceModules(interfacePath, e, countDownLatch));

        log.info("++++++++++++++++++++++++++++++++++++");
        log.info("RPC interface definition path: {}", interfacePath);
        interfaceCategory.forEach((key, value) -> log.info("RPC-Category: " + key + "->" + value));
        log.info("++++++++++++++++++++++++++++++++++++");
        try {
            countDownLatch.await();
            log.info("Loaded {} rpc-interface pairs ", interfacesOfModule.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadInterfaceModules(String interfacePath, String category, CountDownLatch countDownLatch) {
        List<File> fileList = (List<File>) FileUtils.listFiles(
                new File(interfacePath + File.separator + category), new String[]{"xml"}, false);

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
            interfaceCategory.put(category, subSystemID);
            RpcInterfaceFileScanThread scanThread = new RpcInterfaceFileScanThread(subSystemID, interfacePath, category, countDownLatch);
            threadPool.execute(scanThread);
        }
    }

    class RpcInterfaceFileScanThread implements Runnable {
        private String subSystem;
        private String filePath;
        private String category;
        private CountDownLatch latching;

        public RpcInterfaceFileScanThread (String subSystem, String filePath, String category, CountDownLatch latching) {
            this.subSystem = subSystem;
            this.filePath = filePath;
            this.category = category;
            this.latching = latching;
        }

        @Override
        public void run() {
            loadTablesOfModule(subSystem, filePath, category, RPC_INTERFACE_DEFINITION_CLASSIFIER);
            latching.countDown();
            log.info("Scan interface category [{}] is completed", category);
        }

        private void loadTablesOfModule(String subSystem, String filePath, String category, String classifier) {
            List<File> fileList = (List<File>) FileUtils.listFiles(
                    new File(filePath + File.separator + category), new String[]{"xml"}, true);
            if (fileList == null || fileList.size() < 0) {
                return;
            }
            try {
                for (File f : fileList) {
                    String moduleName = f.getParentFile().getName();
                    if (f.getName().startsWith(classifier) && f.getName().endsWith(".xml")) {
                        ServiceInterfaceType serviceInterfaceType = (ServiceInterfaceType) DefinitionFileParserFactory.get(
                                WestLakeSourceFileConfig.RPC_INTERFACE_CACHE_KEY).parse(f, ServiceInterfaceType.class);
                        String rpcEnName = serviceInterfaceType.getSummary().getName();
                        interfacesOfModule.put(moduleName, rpcEnName);
                        interfacesTypeOfModule.put(moduleName, serviceInterfaceType);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
