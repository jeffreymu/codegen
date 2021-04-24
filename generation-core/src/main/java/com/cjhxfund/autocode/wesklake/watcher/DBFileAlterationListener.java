package com.cjhxfund.autocode.wesklake.watcher;

import com.cjhxfund.autocode.disruptor.event.DBTableModuleEventData;
import com.cjhxfund.autocode.disruptor.wrapper.WatchableDisruptorWrapper;
import com.cjhxfund.autocode.disruptor.event.DBTableEventData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.MessageFormat;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class DBFileAlterationListener extends FileAlterationListenerAdaptor {

    @Autowired
    private WatchableDisruptorWrapper publisher;

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info(MessageFormat.format("Directory [{0}] is created",directory.getAbsolutePath()));
        if (directory.isDirectory()) {
            publisher.publishDBTableModule(DBTableModuleEventData.EventFactory.newEvent(directory));
        }
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info(MessageFormat.format("Directory[{0}] is modified",directory.getAbsolutePath()));
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info(MessageFormat.format("Directory[{0}] is removed",directory.getAbsolutePath()));
    }

    @Override
    public void onFileCreate(File file) {
        log.info(MessageFormat.format("File [{0}] is created",file.getAbsolutePath()));
        if (file.getName().endsWith("xml")) {
            publisher.publishDBTable(DBTableEventData.EventFactory.newEvent(file));
        }
    }

    @Override
    public void onFileChange(File file) {
        log.info(MessageFormat.format("File [{0}] is modified",file.getAbsolutePath()));
        publisher.publishDBTable(DBTableEventData.EventFactory.newEvent(file));
    }

    @Override
    public void onFileDelete(File file) {
        log.info(MessageFormat.format("File [{0}] is removed",file.getAbsolutePath()));
    }
    
}
