package com.cjhxfund.autocode.wesklake.util;

import com.cjhxfund.autocode.wesklake.model.xsd.common.dict.DictType;

import java.util.*;

public class SystemDictDiffer {

    public static List<DictType> reduceAll(List<DictType> left, List<DictType> right){
        if (left == null){
            return null;
        }
        if (right == null){
            return left;
        }
        List<DictType> res = new LinkedList<>(left);
        Set<String> set = new HashSet<>();
        for(DictType item : right){
            set.add(item.getId());
        }
        Iterator<DictType> iter = res.iterator();
        while(iter.hasNext()){
            DictType item = iter.next();
            if(set.contains(item.getId())){
                iter.remove();
            }
        }
        return res;
    }
}
