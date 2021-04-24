package com.cjhxfund.autocode.model.out.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
public class SystemDictHxx implements Serializable {

    private String hashKey;
    private SystemDict[] dicts;

}
