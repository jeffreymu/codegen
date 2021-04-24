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
public class ProtoFileEventData {

    private File file;
    private ValueEvent.EventType type;

    public static class EventFactory {
        public static ProtoFileEventData newEvent(File f) {
            return ProtoFileEventData
                    .builder()
                    .file(f)
                    .type(ValueEvent.EventType.PROTO_FILE)
                    .build();
        }
    }
}
