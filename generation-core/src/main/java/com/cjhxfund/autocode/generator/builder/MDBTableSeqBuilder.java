package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.cache.DatabaseSequenceRetriever;
import com.cjhxfund.autocode.model.out.mdb.MDBSequence;
import com.cjhxfund.autocode.model.out.mdb.MDBSequenceInfo;
import com.cjhxfund.autocode.wesklake.model.xsd.common.db.SequenceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public final class MDBTableSeqBuilder {

    @Autowired
    private DatabaseSequenceRetriever sequenceRetriever;

    public List<MDBSequence> buildMDBSequence() {
        List<MDBSequence> dbSequences = new ArrayList<>();
        List<SequenceType> sequenceTypes = sequenceRetriever.findSequences();
        for (SequenceType type : sequenceTypes) {
            if (type.getForMDB().equalsIgnoreCase("true")) {
                dbSequences.add(MDBSequence
                        .builder()
                        .name(type.getEnName())
                        .start(Integer.parseInt(type.getStartValue()))
                        .orderId(Integer.parseInt(type.getId()))
                        .flag1(type.getDailyReset())
                        .flag2(type.getIsRebootContinue())
                        .build());
            }
        }
        return dbSequences;
    }

    public List<MDBSequenceInfo> buildMDBSequenceFullInfo() {
        List<MDBSequenceInfo> sequenceInfos = new ArrayList<>();
        List<SequenceType> sequenceTypes = sequenceRetriever.findSequences();
        for (SequenceType type : sequenceTypes) {
            if (type.getForMDB().equalsIgnoreCase("true")) {
                sequenceInfos.add(MDBSequenceInfo
                        .builder()
                        .id(Integer.parseInt(type.getId()))
                        .enName(type.getEnName())
                        .chName(type.getChName())
                        .initValue(Integer.parseInt(type.getStartValue()))
                        .dailyReset(type.getDailyReset().equalsIgnoreCase("true") ? true : false)
                        .oracle(type.getForOracle().equalsIgnoreCase("true") ? true : false)
                        .continuous(type.getIsRebootContinue().equalsIgnoreCase("true") ? true : false)
                        .mdbList(type.getMDBList())
                        .recoverySrc(type.getRecoveryFrom())
                        .recoverySqlOracle(type.getRecoverySqlOracle())
                        .recoverySqlMysql(type.getRecoverySqlMySql())
                        .remark(type.getRemark())
                        .build());
            }
        }
        return sequenceInfos;
    }

}
