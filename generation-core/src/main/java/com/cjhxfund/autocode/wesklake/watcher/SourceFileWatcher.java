package com.cjhxfund.autocode.wesklake.watcher;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

/**
 * Created by Jeffrey on 2021/3/17.
 */
public interface SourceFileWatcher {

    void watching(String watchableDir);
}
