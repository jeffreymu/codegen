package com.cjhxfund.autocode.disruptor.event;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Jeffrey on 2021/3/17.
 */
@Data
@Builder
@ToString(callSuper = true)
public class ContentFileEventData {

    private ValueEvent.EventType type;
    private String targetFileName;
    private Object contentData;

    public static class EventFactory {
        public static ContentFileEventData newEvent(String file, Object data) {
            return ContentFileEventData
                    .builder()
                    .targetFileName(file)
                    .type(ValueEvent.EventType.FILE_CONTENT)
                    .build();
        }
    }
}
