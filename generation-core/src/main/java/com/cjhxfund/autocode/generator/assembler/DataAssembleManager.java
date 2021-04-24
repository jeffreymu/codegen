package com.cjhxfund.autocode.generator.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class DataAssembleManager {

    @Autowired
    private DBTableAssembler dbTableAssembler;

    @Autowired
    private DBTableCxxAssembler dbTableCxxAssembler;

    @Autowired
    private DBTableImplCxxAssembler dbTableImplCxxAssembler;

    @Autowired
    private MDBTableAssembler mdbTableAssembler;

    @Autowired
    private SequenceRecoveryAssembler sequenceRecoveryAssembler;

    @Autowired
    private DBSequenceAssembler dbSequenceAssembler;

    @Autowired
    private SystemErrorAssembler systemErrorAssembler;

    @Autowired
    private SystemDictSqlAssembler systemDictSqlAssembler;

    @Autowired
    private SystemDictHxxAssembler systemDictHxxAssembler;

    @Autowired
    private DBInitDataSqlAssembler initDataSqlAssembler;

    @Autowired
    private RPCInterfaceAssembler rpcInterfaceAssembler;

    @Autowired
    private RPCServiceCxxAssembler rpcServiceCxxAssembler;

    public DBTableAssembler getDbTableAssembler() {
        return dbTableAssembler;
    }

    public DBTableCxxAssembler getDbTableCxxAssembler() {
        return dbTableCxxAssembler;
    }

    public DBTableImplCxxAssembler getDbTableImplCxxAssembler() {
        return dbTableImplCxxAssembler;
    }

    public MDBTableAssembler getMdbTableAssembler() {
        return mdbTableAssembler;
    }

    public SequenceRecoveryAssembler getSequenceRecoveryAssembler() {
        return sequenceRecoveryAssembler;
    }

    public DBSequenceAssembler getDbSequenceAssembler() {
        return dbSequenceAssembler;
    }

    public SystemDictSqlAssembler getSystemDictSqlAssembler() {
        return systemDictSqlAssembler;
    }

    public DBInitDataSqlAssembler getInitDataSqlAssembler() {
        return initDataSqlAssembler;
    }

    public RPCInterfaceAssembler getRpcInterfaceAssembler() {
        return rpcInterfaceAssembler;
    }

    public RPCServiceCxxAssembler getRpcServiceCxxAssembler() {
        return rpcServiceCxxAssembler;
    }

    public SystemErrorAssembler getSystemErrorAssembler() {
        return systemErrorAssembler;
    }

    public SystemDictHxxAssembler getSystemDictHxxAssembler() {
        return systemDictHxxAssembler;
    }
}
