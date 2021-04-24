package com.cjhxfund.autocode.disruptor.wrapper;

import com.cjhxfund.autocode.disruptor.event.ValueEvent;

/**
 * Created by Jeffrey on 2021/3/17.
 */
public interface DisruptorHandleService {

    /**
     * disruptor execution method to handle event
     * @param event
     */
    void execute(ValueEvent event);

}
