package com.cjhxfund.autocode.parser.template;

import com.cjhxfund.autocode.parser.STArgsEntity;
import com.cjhxfund.autocode.parser.STTemplateParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Jeffrey on 2021/3/13.
 */

public class SgtTemplateTest {
    STArgsEntity[] params = null;
    @Before
    public void setup() {
        params = new STArgsEntity[] {
                new STArgsEntity("BID", "string"),
                new STArgsEntity("Species", "string"),
                new STArgsEntity("Color", "string"),
                new STArgsEntity("Weight", "float64"),
                new STArgsEntity("NetWeight", "float64"),
                new STArgsEntity("Owner", "string"),
                new STArgsEntity("Status", "int"),
        };
    }

    @Test
    public void test_gen_file() throws IOException {
        STTemplateParser.output(STTemplateParser.parse(params));
    }
}
