package com.cjhxfund.autocode.disruptor.event;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.File;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Data
@Builder
@ToString(callSuper = true)
public class MDBGroupEventData {

    private ValueEvent.EventType type;
    private File file;

    public static class EventFactory {
        public static MDBGroupEventData newEvent(File f) {
            return MDBGroupEventData
                    .builder()
                    .file(f)
                    .type(ValueEvent.EventType.MDB_GROUP)
                    .build();
        }
    }
}
