package com.cjhxfund.autocode.generator.assembler;

import com.cjhxfund.autocode.model.out.mdb.MDBRecoverySequence;
import com.cjhxfund.autocode.model.out.mdb.RecoverySequence;
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
public class SequenceRecoveryAssembler implements ContentFileAssembler<MDBRecoverySequence, ArrayOfSequenceType> {

    private final static String CXX_FILE_HASH_KEY = "#";

    @Override
    public MDBRecoverySequence assemble(String subSystem, String moduleName, String destFileName, ArrayOfSequenceType tableType) {
        List<SequenceType> sequences = tableType.getSequence();
        List<SequenceType> mdbSequences = new ArrayList<>();
        for (SequenceType seq : sequences) {
            if (seq.getForMDB().equalsIgnoreCase("true")) {
                mdbSequences.add(seq);
            }
        }
        List<RecoverySequence> seqList = new ArrayList<>();
        try {
            for (SequenceType mdbSequence : mdbSequences) {
                RecoverySequence subSequence = RecoverySequence.builder().name(mdbSequence.getEnName()).build();
                String recoveryFrom = mdbSequence.getRecoveryFrom();
                if (recoveryFrom == null || recoveryFrom.equals("")) {
                    log.info("Sequence: " + mdbSequence.getEnName());
                    if (!mdbSequence.getRecoverySqlMySql().equals("") && !mdbSequence.getRecoverySqlOracle().equals("")) {
                        RecoverySequence.SqlPair sqlPair = new RecoverySequence.SqlPair();
                        sqlPair.setMysqlSql(mdbSequence.getRecoverySqlMySql());
                        sqlPair.setOracleSql(mdbSequence.getRecoverySqlOracle());
                        subSequence.setSqlPair(sqlPair);
                    }
                } else {
                    String[] recoveryArray = recoveryFrom.split("\\|");
                    List<RecoverySequence.RecoveryPair> pairs = new ArrayList<>();
                    for (int i = 0; i < recoveryArray.length; i++) {
                        RecoverySequence.RecoveryPair pair = new RecoverySequence.RecoveryPair();
                        int indexOf = recoveryArray[i].indexOf(":");
                        pair.setRecoveryName(recoveryArray[i].substring(0, indexOf));
                        pair.setRecoveryId(recoveryArray[i].substring(indexOf + 1));
                        pairs.add(pair);
                    }
                    subSequence.setPairs(pairs.toArray(new RecoverySequence.RecoveryPair[pairs.size()]));
                }
                seqList.add(subSequence);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return MDBRecoverySequence.builder()
                .hashKey(CXX_FILE_HASH_KEY)
                .sequences(seqList.toArray(new RecoverySequence[seqList.size()]))
                .build();
    }
}
