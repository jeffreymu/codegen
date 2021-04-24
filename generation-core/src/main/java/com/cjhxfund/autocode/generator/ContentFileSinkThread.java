package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.ContentType;
import com.cjhxfund.autocode.model.out.service.RpcServiceRegister;
import com.cjhxfund.autocode.template.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;

import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class ContentFileSinkThread implements Runnable {

    private boolean isRunning = true;
    private ConcurrentLinkedQueue<String> queue;
    private RpcServiceRegister sourceData;
    private TemplateLoader templateLoader;
    private ContentType contentType;
    private String homePath;

    public ContentFileSinkThread(String homePath, ContentType contentType,
                                 RpcServiceRegister sourceData, TemplateLoader templateLoader) {
        this.homePath = homePath;
        this.contentType = contentType;
        this.sourceData = sourceData;
        this.templateLoader = templateLoader;
    }

    @Override
    public void run() {
        while(isRunning){
            sink(contentType);
        }
    }

    private void sink(ContentType contentType) {
        ST sTemplate = null;
        log.info("Handle RPC service file of {}", contentType.toString());
        switch (contentType) {
            default:
                break;
        }
        sTemplate.add("params", sourceData);
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
