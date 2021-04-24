package com.cjhxfund.autocode.disruptor.handler;

import com.cjhxfund.autocode.disruptor.event.ContentFileEventData;
import com.cjhxfund.autocode.disruptor.event.ValueEvent;
import com.cjhxfund.autocode.generator.ContentGeneratorManager;
import com.cjhxfund.autocode.model.out.common.FmErrors;
import com.cjhxfund.autocode.model.out.common.SystemDictHxx;
import com.cjhxfund.autocode.model.out.mdb.DBTableCxx;
import com.cjhxfund.autocode.model.out.mdb.DBTableImplCxx;
import com.cjhxfund.autocode.model.out.mdb.MDBRecoverySequence;
import com.cjhxfund.autocode.model.out.mdb.MDBTable;
import com.cjhxfund.autocode.model.out.proto.ProtoFlatContent;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.model.out.table.*;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Handler object of content file, which can generate kind of
 * content of plat files when the event appears.
 */
@Slf4j
@Component
public class ContentFileDataHandler implements EventHandler<ValueEvent> {

    @Autowired
    private ContentGeneratorManager contentGeneratorManager;

    @Override
    public void onEvent(ValueEvent valueEvent, long l, boolean b) throws Exception {
        log.info("Receive Content: " + valueEvent.getEventType().name());
        ValueEvent.EventType eventType = valueEvent.getEventType();
        switch (eventType) {
            case FILE_CONTENT:
                generateFile(valueEvent.getValue());
                break;
            default:
                break;
        }
    }

    private void generateFile(Object value) {
        Object contentObj = ((ContentFileEventData) value).getContentData();
        if (contentObj instanceof DBTable) {
            if (((DBTable) contentObj).getFileType() == FileType.DB_SQL_FILE) {
                contentGeneratorManager.getPlatFileGenerator().generate((DBTable) contentObj);
            }
        }
        if (contentObj instanceof ProtoFlatContent) {
            contentGeneratorManager.getProtoFileGenerator().generate((ProtoFlatContent) contentObj);
        }
        if (contentObj instanceof DBTableCxx) {
            contentGeneratorManager.getTableHxxGenerator().generate((DBTableCxx) contentObj);
        }
        if (contentObj instanceof DBTableImplCxx) {
            contentGeneratorManager.getTableCxxGenerator().generate((DBTableImplCxx) contentObj);
        }
        if (contentObj instanceof MDBTable) {
            contentGeneratorManager.getMdbTableGenerator().generate((MDBTable) contentObj);
        }
        if (contentObj instanceof Dictionaries) {
            contentGeneratorManager.getDictionaryGenerator().generate((Dictionaries) contentObj);
        }
        if (contentObj instanceof FmErrors) {
            contentGeneratorManager.getFmErrorHxxGenerator().generate((FmErrors) contentObj);
        }
        if (contentObj instanceof SystemDictHxx) {
            contentGeneratorManager.getSystemDictHxxGenerator().generate((SystemDictHxx) contentObj);
        }
        if (contentObj instanceof DBInitData) {
            contentGeneratorManager.getDbInitDataGenerator().generate((DBInitData) contentObj);
        }
        if (contentObj instanceof Sequences) {
            contentGeneratorManager.getDbSequenceGenerator().generate((Sequences) contentObj);
        }
        if (contentObj instanceof MDBRecoverySequence) {
            contentGeneratorManager.getSequenceGenerator().generate((MDBRecoverySequence) contentObj);
        }
        if (contentObj instanceof RpcServiceRegister) {
            contentGeneratorManager.getRpcServiceCxxGenerator().generate((RpcServiceRegister) contentObj);
        }
    }
}
