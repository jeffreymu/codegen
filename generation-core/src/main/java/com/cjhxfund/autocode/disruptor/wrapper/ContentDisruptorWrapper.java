package com.cjhxfund.autocode.disruptor.wrapper;

import com.cjhxfund.autocode.disruptor.event.ContentFileEventData;
import com.cjhxfund.autocode.disruptor.event.ValueEvent;
import com.cjhxfund.autocode.disruptor.handler.ContentFileDataHandler;
import com.cjhxfund.autocode.disruptor.handler.DisruptorExceptionHandler;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ContentDisruptorWrapper {

    private int bufferSize = 16;
    private int threadSize = 2;

    @Autowired
    private DisruptorExceptionHandler exceptionHandler;

    private Disruptor<ValueEvent> disruptor;

    @Autowired
    private ContentFileDataHandler contentFileDataHandler;

    @PostConstruct
    private void init() {
        disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, bufferSize,
                MonitorThreadFactory.create("c-disruptor-", false),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith(contentFileDataHandler);
        start();
    }

    public ContentDisruptorWrapper(EventHandler[] eventHandlers) {
        disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, bufferSize,
                MonitorThreadFactory.create("c-disruptor-", false),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWith(eventHandlers);
    }

    public void start() {
        disruptor.setDefaultExceptionHandler(exceptionHandler);
        disruptor.start();
    }

    @PreDestroy
    public void shutdown() {
        if (disruptor != null)
            disruptor.shutdown();
    }

    /**
     * publish MDB category
     * @param eventData
     */
    public void publishContent(ContentFileEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(MDB_CATEGORY_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, ContentFileEventData> MDB_CATEGORY_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, ContentFileEventData>() {
        public void translateTo(ValueEvent event, long sequence, ContentFileEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.FILE_CONTENT);
        }
    };
}
