package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.model.out.table.DBSequence;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import javax.xml.bind.JAXBException;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Slf4j
public class DBSequenceGeneratorTest {

    public static void generateTable(ArrayOfSequenceType result, String stFile, String templateName) {
        STGroup stg = new STGroupFile(stFile);
        ST sTemplate = stg.getInstanceOf(templateName);

        DBSequence sequence = DBSequence.builder()
                .name("seq_etfmmessage_id")
                .start(1)
                .minValue(1)
                .maxValue(900000000)
                .description("ETF监控消息序号")
                .build();
        sTemplate.add("params", sequence);
        log.info(sTemplate.render());
    }


    public static void main(String[] args) throws JAXBException {
        ArrayOfSequenceType result = null;
//        DBSequenceParser.parse(
//                "sequence.xml", ArrayOfSequenceType.class);
        DBSequenceGeneratorTest.generateTable(result, "st/db/db-sequence.stg", "sequenceTemplate");

    }
}
