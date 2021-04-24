package com.cjhxfund.autocode.parser;

import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Jeffrey on 2021/3/13.
 */
@Slf4j
public class STTemplateParser {

    public static ST parse( STArgsEntity[] parms2) {
        STGroup stg = new STGroupFile("testplayer.stg");
        ST s2 = stg.getInstanceOf("sqlTemplate2");
        s2.add("parms2", Arrays.asList(parms2));
        log.info(s2.render());
        return s2;
    }

    public static void output(ST st) throws IOException {
        String path = Class.class.getClass().getResource("/").getPath();
        FileOutputStream out = new FileOutputStream(new File(path + File.separator + "template1.go"));
        out.write(st.render().getBytes());
        out.close();
    }
}
