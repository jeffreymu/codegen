package com.cjhxfund.autocode.wesklake.watcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
public class SourceFileChangedWatcher implements SourceFileWatcher {

    private FileAlterationMonitor monitor;
    private FileAlterationObserver observer;
    private FileAlterationListenerAdaptor listener;

    public SourceFileChangedWatcher(FileAlterationListenerAdaptor listener) {
        this.listener = listener;
    }

    @Override
    public void watching(String watchableDir) {
        long interval = TimeUnit.SECONDS.toMillis(1);
        try {
            log.info("Watching dir: " + watchableDir);
            observer = new FileAlterationObserver(watchableDir);
            observer.addListener(listener);
            monitor = new FileAlterationMonitor(interval, observer);
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Launch the file alteration monitor error");
        }
    }

    @PreDestroy
    public void stopMonitor() {
        if (monitor != null) {
            try {
                monitor.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
