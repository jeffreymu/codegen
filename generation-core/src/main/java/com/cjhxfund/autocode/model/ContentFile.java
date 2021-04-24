package com.cjhxfund.autocode.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Jeffrey on 2021/3/18.
 */
@Data
@ToString
public class ContentFile {

    private String content;
    private String targetName;
    private String charset;

    public static class Factory {
        public static ContentFile create(String name, String content) {
            ContentFile contentFile = new ContentFile();
            contentFile.setContent(content);
            contentFile.setTargetName(name);
            contentFile.setCharset("UTF-8");
            return contentFile;
        }
    }
}
