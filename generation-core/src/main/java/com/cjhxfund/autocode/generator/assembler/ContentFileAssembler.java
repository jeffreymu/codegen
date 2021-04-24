package com.cjhxfund.autocode.generator.assembler;

/**
 * Created by Jeffrey on 2021/3/22.
 */
public interface ContentFileAssembler<R, T> {

    /**
     * assemble method to build kind of content data
     * @param subSystem
     * @param moduleName
     * @param destFileName
     * @param tableType
     * @return
     */
    R assemble(String subSystem, String moduleName, String destFileName, T tableType);

}
