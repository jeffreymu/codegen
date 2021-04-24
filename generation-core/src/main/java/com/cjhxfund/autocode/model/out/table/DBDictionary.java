package com.cjhxfund.autocode.model.out.table;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by Jeffrey on 2021/3/14.
 */
@Data
@Builder
@ToString
public class DBDictionary implements Comparable<DBDictionary>, Serializable {

    private String dictId;
    private String dictName;
    private DictPair[] pairs;

    @Data
    public static class DictPair {
        private String key;
        private String name;
    }

    @Override
    public int compareTo(DBDictionary o) {
        return this.getDictId().compareTo(o.getDictId());
    }

}
