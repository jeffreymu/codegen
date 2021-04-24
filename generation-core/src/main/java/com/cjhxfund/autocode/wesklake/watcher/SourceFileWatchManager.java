package com.cjhxfund.autocode.wesklake.watcher;

import com.cjhxfund.autocode.wesklake.config.WatchableFilePathConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class SourceFileWatchManager {

    @Value("${westlake.installation.path}")
    private String westLakeHome;

    @Autowired
    private DBFileAlterationListener dbFileAlterationListener;
    @Autowired
    private CommonFileAlterationListener commonFileAlterationListener;
    @Autowired
    private DBConfigFileAlterationListener dbConfigFileAlterationListener;
    @Autowired
    private RpcInterfaceFileAlterationListener rpcInterfaceFileAlterationListener;

    @PostConstruct
    private void launchWatchers() {
        log.info("++++++++++++++++++++++++++++++++++++");
        log.info("Launching file watchers...");
        launchOne(dbFileAlterationListener, WatchableFilePathConfig.WATCH_DB_TABLE_DIR);
        launchOne(commonFileAlterationListener, WatchableFilePathConfig.WATCH_COMMON_DIR);
        launchOne(dbConfigFileAlterationListener, WatchableFilePathConfig.WATCH_BASIC_DATA_DIR);
        launchOne(rpcInterfaceFileAlterationListener, WatchableFilePathConfig.WATCH_INTERFACE_DIR);
        log.info("++++++++++++++++++++++++++++++++++++");
    }

    private void launchOne(FileAlterationListenerAdaptor listener, String pathConfig) {
        new SourceFileChangedWatcher(listener).watching(
                westLakeHome + File.separator + pathConfig);
    }
}
