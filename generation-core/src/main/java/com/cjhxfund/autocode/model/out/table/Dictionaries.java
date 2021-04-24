package com.cjhxfund.autocode.model.out.table;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
public class Dictionaries implements Serializable {

    private DBDictionary[] dicts;

}
