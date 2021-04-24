package com.cjhxfund.autocode.generator.wirter;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class ContentFileWriter {

    public static void writeLocal(String fileName, String content) throws IOException {
        String path = Class.class.getClass().getResource("/").getPath();
        File targetFile = new File(path + File.separator + fileName);
        Files.write(content.getBytes(), targetFile);
    }

    public static void append(String fileName, String content) throws IOException {
        String path = Class.class.getClass().getResource("/").getPath();
        CharSequence charSequence = content.subSequence(0, content.length() - 1);
        File targetFile = new File(path + File.separator + fileName);
        Files.append(charSequence, targetFile, Charset.defaultCharset());
    }

    public static void write(String targetFileName, String content) throws IOException {
        File targetFile = new File(targetFileName);
        if (targetFile.exists()) {
            FileUtils.forceDelete(targetFile);
        }
        FileUtils.forceMkdirParent(targetFile);
        Files.write(content, targetFile, Charsets.UTF_8);
    }

    public static void writeByCharset(String targetFileName, String content, java.nio.charset.Charset charset) throws IOException {
        File targetFile = new File(targetFileName);
        if (targetFile.exists()) {
            FileUtils.forceDelete(targetFile);
        }
        FileUtils.forceMkdirParent(targetFile);
        Files.write(content, targetFile, charset);
    }

    public static void copyFile(String srcFile, String targetFile) throws IOException {
        FileUtils.copyFile(new File(srcFile), new File(targetFile));
    }
}
