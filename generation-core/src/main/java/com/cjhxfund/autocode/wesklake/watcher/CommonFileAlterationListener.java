package com.cjhxfund.autocode.wesklake.watcher;

import com.cjhxfund.autocode.disruptor.event.*;
import com.cjhxfund.autocode.disruptor.wrapper.WatchableDisruptorWrapper;
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
public class CommonFileAlterationListener extends FileAlterationListenerAdaptor {

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
        publisher.publishMDBCategory(MDBCategoryEventData.EventFactory.newEvent(file));
    }

    @Override
    public void onFileChange(File file) {
        log.info(MessageFormat.format("File [{0}] is modified",file.getAbsolutePath()));
        if (file.getName().equalsIgnoreCase("mdb_table.xml")) {
            publisher.publishMDBTable(MDBTableEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("mdb_table_group.xml")) {
            publisher.publishMDBGroup(MDBGroupEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("mdb.xml")) {
            publisher.publishMDBCategory(MDBCategoryEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("sequence.xml")) {
            publisher.publishMDBSequence(MDBSequenceEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("dict.xml")) {
            publisher.publishSystemDict(SystemDictEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("system_error.xml")) {
            publisher.publishSystemError(SystemErrorEventData.EventFactory.newEvent(file));
        }
        if (file.getName().equalsIgnoreCase("stdfield.xml")) {
            publisher.publishSystemField(SystemFieldEventData.EventFactory.newEvent(file));
        }
    }

    @Override
    public void onFileDelete(File file) {
        log.info(MessageFormat.format("File [{0}] is removed",file.getAbsolutePath()));
    }
    
}
