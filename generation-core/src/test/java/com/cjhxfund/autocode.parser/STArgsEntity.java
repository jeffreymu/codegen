package com.cjhxfund.autocode.parser;

/**
 * Created by Jeffrey on 2021/3/11.
 */
public class STArgsEntity {
    private String name;
    private String type;
    private String json;

    public STArgsEntity(String name, String type) {
        this.name = name;
        this.type = type;
        this.json = name.toLowerCase();
    }

    public String getType() { return type; }
    public String getName() { return name; }
    public String getJson() {return json; }

}
