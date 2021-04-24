package com.cjhxfund.autocode.disruptor.event;

import com.lmax.disruptor.EventFactory;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/21.
 */
@Data
public class ValueEvent implements Serializable, Cloneable {

    private static final long serialVersionUID = 9040867737103906848L;
    private Object value;
    private EventType eventType;

    public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>() {
        public ValueEvent newInstance() {
            return new ValueEvent();
        }
    };

    public enum EventType {
        DB_TABLE, TABLE_MODULE, DB_DICT, INIT_DATA, DB_SEQUENCE,

        MDB_TABLE, MDB_GROUP, MDB_CATEGORY, MDB_SEQUENCE,

        RPC_INTERFACE, PROTO_FILE,

        SYSTEM_DICT, SYSTEM_ERROR, SYSTEM_FIELD,

        FILE_CONTENT
    }

    @Override
    public Object clone() {
        ValueEvent o = null;
        try {
            o = (ValueEvent) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}
