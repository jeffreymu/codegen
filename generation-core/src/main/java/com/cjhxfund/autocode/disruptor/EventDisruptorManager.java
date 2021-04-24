package com.cjhxfund.autocode.disruptor;

import com.cjhxfund.autocode.disruptor.handler.ContentFileDataHandler;
import com.cjhxfund.autocode.disruptor.handler.WatchableFileHandler;
import com.cjhxfund.autocode.disruptor.wrapper.ContentDisruptorWrapper;
import com.cjhxfund.autocode.disruptor.wrapper.WatchableDisruptorWrapper;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class EventDisruptorManager {

    @Autowired
    private WatchableFileHandler watchableFileHandler;

    @Autowired
    private ContentFileDataHandler contentFileDataHandler;

    private int threadSize = 2;

    private void init() {
        startWatchableFile();
    }

    private void startWatchableFile() {
        WatchableFileHandler[] handlers = new WatchableFileHandler[threadSize];
        for (int i = 0; i < threadSize; i++) {
            handlers[i] = new WatchableFileHandler();
        }
        WatchableDisruptorWrapper inputDisruptor = new WatchableDisruptorWrapper(handlers);
        inputDisruptor.start();
        ContentDisruptorWrapper outputDisruptor = new ContentDisruptorWrapper(new EventHandler[]{});
        outputDisruptor.start();
    }
}
