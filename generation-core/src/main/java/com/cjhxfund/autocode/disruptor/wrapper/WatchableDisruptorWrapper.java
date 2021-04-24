package com.cjhxfund.autocode.disruptor.wrapper;

import com.cjhxfund.autocode.disruptor.event.*;
import com.cjhxfund.autocode.disruptor.handler.DisruptorExceptionHandler;
import com.cjhxfund.autocode.disruptor.handler.WatchableFileHandler;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class WatchableDisruptorWrapper {

    private int bufferSize = 64;
    private int threadSize = 4;

    @Autowired
    private WatchableFileHandler handler;

    private DisruptorExceptionHandler exceptionHandler;

    private Disruptor<ValueEvent> disruptor;

    @PostConstruct
    private void init() {
        disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, bufferSize,
                MonitorThreadFactory.create("w-disruptor-", false),
                ProducerType.MULTI,
                new BlockingWaitStrategy());

        WatchableFileHandler[] handlers = new WatchableFileHandler[threadSize];
        for (int i = 0; i < threadSize; i++)
            handlers[i] = handler;
        disruptor.handleEventsWithWorkerPool(handlers);
        start();
    }

    public WatchableDisruptorWrapper(WorkHandler[] workHandlers) {
        disruptor = new Disruptor<ValueEvent>(ValueEvent.EVENT_FACTORY, bufferSize,
                MonitorThreadFactory.create("w-disruptor-", false),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(workHandlers);
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
    public void publishMDBCategory(MDBCategoryEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(MDB_CATEGORY_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, MDBCategoryEventData> MDB_CATEGORY_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, MDBCategoryEventData>() {
        public void translateTo(ValueEvent event, long sequence, MDBCategoryEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.MDB_CATEGORY);
        }
    };

    /**
     * publish system error
     * @param eventData
     */
    public void publishSystemError(SystemErrorEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(SYSTEM_ERROR_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, SystemErrorEventData> SYSTEM_ERROR_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, SystemErrorEventData>() {
        public void translateTo(ValueEvent event, long sequence, SystemErrorEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.SYSTEM_ERROR);
        }
    };

    /**
     * publish system error
     * @param eventData
     */
    public void publishSystemField(SystemFieldEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(SYSTEM_FIELD_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, SystemFieldEventData> SYSTEM_FIELD_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, SystemFieldEventData>() {
        public void translateTo(ValueEvent event, long sequence, SystemFieldEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.SYSTEM_FIELD);
        }
    };

    /**
     * public system dictionary
     * @param eventData
     */
    public void publishSystemDict(SystemDictEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(SYSTEM_DICT_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, SystemDictEventData> SYSTEM_DICT_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, SystemDictEventData>() {
        public void translateTo(ValueEvent event, long sequence, SystemDictEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.SYSTEM_DICT);
        }
    };

    /**
     * public mdb sequence
     * @param eventData
     */
    public void publishMDBSequence(MDBSequenceEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(MDB_SEQUENCE_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, MDBSequenceEventData> MDB_SEQUENCE_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, MDBSequenceEventData>() {
        public void translateTo(ValueEvent event, long sequence, MDBSequenceEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.MDB_SEQUENCE);
        }
    };

    /**
     * public MDB group
     * @param eventData
     */
    public void publishMDBGroup(MDBGroupEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(MDB_GROUP_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, MDBGroupEventData> MDB_GROUP_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, MDBGroupEventData>() {
        public void translateTo(ValueEvent event, long sequence, MDBGroupEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.MDB_GROUP);
        }
    };

    /**
     * publish MDB table
     * @param eventData
     */
    public void publishMDBTable(MDBTableEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(MDB_TABLE_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, MDBTableEventData> MDB_TABLE_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, MDBTableEventData>() {
        public void translateTo(ValueEvent event, long sequence, MDBTableEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.MDB_TABLE);
        }
    };

    /**
     * public RPC interface
     * @param eventData
     */
    public void publishRpcInterface(RpcInterfaceEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(RPC_INTERFACE_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, RpcInterfaceEventData> RPC_INTERFACE_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, RpcInterfaceEventData>() {
        public void translateTo(ValueEvent event, long sequence, RpcInterfaceEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.RPC_INTERFACE);
        }
    };

    /**
     * publish DB table
     * @param eventData
     */
    public void publishDBTable(DBTableEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(DB_TABLE_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, DBTableEventData> DB_TABLE_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, DBTableEventData>() {
        public void translateTo(ValueEvent event, long sequence, DBTableEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.DB_TABLE);
        }
    };

    /**
     * publish DB table module
     * @param eventData
     */
    public void publishDBTableModule(DBTableModuleEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(DB_TABLE_MODULE_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, DBTableModuleEventData> DB_TABLE_MODULE_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, DBTableModuleEventData>() {
        public void translateTo(ValueEvent event, long sequence, DBTableModuleEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.TABLE_MODULE);
        }
    };

    /**
     * publish DB init data
     * @param eventData
     */
    public void publishDBInitData(DBInitDataEventData eventData) {
        final RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(DB_INIT_DATA_TRANSLATOR, eventData);
    }

    private static final EventTranslatorOneArg<ValueEvent, DBInitDataEventData> DB_INIT_DATA_TRANSLATOR = new EventTranslatorOneArg<ValueEvent, DBInitDataEventData>() {
        public void translateTo(ValueEvent event, long sequence, DBInitDataEventData requestEvent) {
            event.setValue(requestEvent);
            event.setEventType(ValueEvent.EventType.INIT_DATA);
        }
    };
}
