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
public class DBTableModuleEventData {

    private ValueEvent.EventType type;
    private File file;

    public static class EventFactory {
        public static DBTableModuleEventData newEvent(File f) {
            return DBTableModuleEventData
                    .builder()
                    .file(f)
                    .type(ValueEvent.EventType.TABLE_MODULE)
                    .build();
        }
    }
}
