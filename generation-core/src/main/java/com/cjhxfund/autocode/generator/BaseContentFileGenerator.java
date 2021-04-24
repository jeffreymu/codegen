package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.generator.wirter.ContentFileWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public abstract class BaseContentFileGenerator {

    /**
     * write content into target file
     * @param targetDescFile
     * @param content
     */
    protected void writeContent(String targetDescFile, String content) {
        try {
            ContentFileWriter.write(targetDescFile, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * copy proto file
     * @param srcFile
     * @param targetFile
     * @throws IOException
     */
    protected void copyFile(String srcFile, String targetFile) throws IOException {
        ContentFileWriter.copyFile(srcFile, targetFile);
    }
}
