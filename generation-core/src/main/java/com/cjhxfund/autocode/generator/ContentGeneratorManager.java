package com.cjhxfund.autocode.generator;

import com.cjhxfund.autocode.generator.common.FmErrorHxxGenerator;
import com.cjhxfund.autocode.generator.common.SystemDictHxxGenerator;
import com.cjhxfund.autocode.generator.db.DBDictionaryGenerator;
import com.cjhxfund.autocode.generator.db.DBInitDataGenerator;
import com.cjhxfund.autocode.generator.db.DBSequenceGenerator;
import com.cjhxfund.autocode.generator.db.DBTableGenerator;
import com.cjhxfund.autocode.generator.mdb.MDBSequenceGenerator;
import com.cjhxfund.autocode.generator.mdb.MDBTableCxxGenerator;
import com.cjhxfund.autocode.generator.mdb.MDBTableCxxImplGenerator;
import com.cjhxfund.autocode.generator.mdb.MDBTableGenerator;
import com.cjhxfund.autocode.generator.proto.ProtoFileGenerator;
import com.cjhxfund.autocode.generator.service.RPCServiceCxxGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class ContentGeneratorManager {

    @Autowired
    private DBTableGenerator platFileGenerator;

    @Autowired
    private ProtoFileGenerator protoFileGenerator;

    @Autowired
    private MDBTableCxxGenerator tableHxxGenerator;

    @Autowired
    private MDBTableCxxImplGenerator tableCxxGenerator;

    @Autowired
    private MDBTableGenerator mdbTableGenerator;

    @Autowired
    private MDBSequenceGenerator sequenceGenerator;

    @Autowired
    private RPCServiceCxxGenerator rpcServiceCxxGenerator;

    @Autowired
    private DBDictionaryGenerator dictionaryGenerator;

    @Autowired
    private DBInitDataGenerator dbInitDataGenerator;

    @Autowired
    private DBSequenceGenerator dbSequenceGenerator;

    @Autowired
    private FmErrorHxxGenerator fmErrorHxxGenerator;

    @Autowired
    private SystemDictHxxGenerator systemDictHxxGenerator;

    public DBTableGenerator getPlatFileGenerator() {
        return platFileGenerator;
    }

    public ProtoFileGenerator getProtoFileGenerator() {
        return protoFileGenerator;
    }

    public MDBTableCxxGenerator getTableHxxGenerator() {
        return tableHxxGenerator;
    }

    public MDBTableCxxImplGenerator getTableCxxGenerator() {
        return tableCxxGenerator;
    }

    public MDBTableGenerator getMdbTableGenerator() {
        return mdbTableGenerator;
    }

    public MDBSequenceGenerator getSequenceGenerator() {
        return sequenceGenerator;
    }

    public RPCServiceCxxGenerator getRpcServiceCxxGenerator() {
        return rpcServiceCxxGenerator;
    }

    public DBDictionaryGenerator getDictionaryGenerator() {
        return dictionaryGenerator;
    }

    public DBInitDataGenerator getDbInitDataGenerator() {
        return dbInitDataGenerator;
    }

    public DBSequenceGenerator getDbSequenceGenerator() {
        return dbSequenceGenerator;
    }

    public FmErrorHxxGenerator getFmErrorHxxGenerator() {
        return fmErrorHxxGenerator;
    }

    public SystemDictHxxGenerator getSystemDictHxxGenerator() {
        return systemDictHxxGenerator;
    }
}
