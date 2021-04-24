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
public class RpcInterfaceEventData {

    private File file;
    private ValueEvent.EventType type;

    public static class EventFactory {
        public static RpcInterfaceEventData newEvent(File f) {
            return RpcInterfaceEventData
                    .builder()
                    .file(f)
                    .type(ValueEvent.EventType.RPC_INTERFACE)
                    .build();
        }
    }
}
