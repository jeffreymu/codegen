package com.cjhxfund.autocode.writer;

import com.cjhxfund.autocode.generator.wirter.ContentFileWriter;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jeffrey on 2021/3/14.
 */
public class ContentFileWriterTest {

    @Test
    public void test_write_file() throws IOException {
        String content = "this is test content...";
        ContentFileWriter.writeLocal("test-file", content);
    }

    @Test
    public void test_append_file() throws IOException {
        String content = "next test content...";
        ContentFileWriter.append("test-file", content);
    }
}
