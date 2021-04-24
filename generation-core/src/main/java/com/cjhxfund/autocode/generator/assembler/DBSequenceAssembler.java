package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.model.out.table.DBSequence;
import com.cjhxfund.autocode.model.out.table.Sequences;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.ArrayOfSequenceType;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.SequenceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffrey on 2021/3/22.
 */
@Slf4j
@Component
public class DBSequenceAssembler implements ContentFileAssembler<Sequences, ArrayOfSequenceType> {

    private final static int MAX_SEQ_VALUE = 2100000000;
    @Override
    public Sequences assemble(String subSystem, String moduleName, String destFileName, ArrayOfSequenceType tableType) {
        List<SequenceType> sequences = tableType.getSequence();
        List<DBSequence> dbSequences = new ArrayList<>();
        for (SequenceType seq : sequences) {
            if (seq.getForOracle().equalsIgnoreCase("true")) {
                dbSequences.add(DBSequence
                        .builder()
                        .name(seq.getEnName())
                        .description(seq.getChName())
                        .start(Integer.parseInt(seq.getStartValue()))
                        .minValue(1)
                        .maxValue(MAX_SEQ_VALUE)
                        .build()
                );
            }
        }
        return Sequences.builder()
                .sequences(dbSequences.toArray(new DBSequence[dbSequences.size()]))
                .build();
    }
}
