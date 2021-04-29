package com.cjhxfund.autocode.wesklake.watcher;

import com.cjhxfund.autocode.disruptor.event.DBInitDataEventData;
import com.cjhxfund.autocode.disruptor.event.DBTableEventData;
import com.cjhxfund.autocode.disruptor.event.RpcInterfaceEventData;
import com.cjhxfund.autocode.disruptor.event.ValueEvent;
import com.cjhxfund.autocode.disruptor.wrapper.WatchableDisruptorWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class WatchableSourceFilePublisher {

    @Autowired
    private WatchableDisruptorWrapper publisher;

    protected Map<ValueEvent.EventType, File> watchableFiles = new ConcurrentHashMap<>();

    public void putFile(ValueEvent.EventType eventType, File f) {
        watchableFiles.put(eventType, f);
    }

    public void publish() {
        if (watchableFiles.isEmpty()) {
            log.info("Publish nothing changed files");
        }
        for (ValueEvent.EventType key : watchableFiles.keySet()) {
            if (key == ValueEvent.EventType.DB_TABLE) {
                log.info("Publish database table file {}", watchableFiles.get(key));
                publisher.publishDBTable(DBTableEventData.EventFactory.newEvent(watchableFiles.get(key)));
            }

            if (key == ValueEvent.EventType.RPC_INTERFACE) {
                log.info("Publish rpc interface file {}", watchableFiles.get(key));
                publisher.publishRpcInterface(RpcInterfaceEventData.EventFactory.newEvent(watchableFiles.get(key)));
            }

            if (key == ValueEvent.EventType.INIT_DATA) {
                log.info("Publish database init-data file {}", watchableFiles.get(key));
                publisher.publishDBInitData(DBInitDataEventData.EventFactory.newEvent(watchableFiles.get(key)));
            }
        }
        watchableFiles.clear();
    }
}
