package com.cjhxfund.autocode.disruptor.handler;

import com.cjhxfund.autocode.disruptor.event.ValueEvent;
import com.lmax.disruptor.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Slf4j
@Component
public class DisruptorExceptionHandler implements ExceptionHandler<ValueEvent> {

    @Override
    public void handleEventException(Throwable throwable, long l, ValueEvent event) {
        throwable.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable throwable) {

    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {

    }
}
