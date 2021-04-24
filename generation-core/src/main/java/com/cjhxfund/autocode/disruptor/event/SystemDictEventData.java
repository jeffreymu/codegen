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
public class SystemDictEventData {

    private ValueEvent.EventType type;
    private File file;

    public static class EventFactory {
        public static SystemDictEventData newEvent(File f) {
            return SystemDictEventData
                    .builder()
                    .file(f)
                    .type(ValueEvent.EventType.SYSTEM_DICT)
                    .build();
        }
    }
}
