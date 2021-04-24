package com.cjhxfund.autocode.generator.builder;

import com.cjhxfund.autocode.model.BasicType;
import com.cjhxfund.autocode.model.out.mdb.MDBTableField;
import com.cjhxfund.autocode.model.out.table.DBTable;
import com.cjhxfund.autocode.model.out.table.TableField;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DBTableFieldBuilder {

    private static final int INT16_FIELD_LENGTH = 2;
    private static final int INT32_FIELD_LENGTH = 4;
    private static final int INT64_FIELD_LENGTH = 6;
    private static final int DOUBLE_FIELD_LENGTH = 8;
    private static final int DEFAULT_OFFSET_LENGTH = 1;

    private int currentPos = 0;
    private int doubleTypeLastPos, stringTypePos, int16TypePos, int32TypePos, int64TypePos = 0;

    private List<TableField> doubleFields = new ArrayList<>();
    private List<TableField> stringFields = new ArrayList<>();
    private List<TableField> int16Fields = new ArrayList<>();
    private List<TableField> int32Fields = new ArrayList<>();
    private List<TableField> int64Fields = new ArrayList<>();

    private List<TableField> totalFields = new ArrayList<>();
    private List<MDBTableField> tableFields = new ArrayList<>();

    /**
     * table total size
     * @return
     */
    public int totalSize() {
        int maxLength = 0;

        if (int64TypePos > 0) {
            maxLength = int64TypePos + 4;
        }
        if (int64TypePos == 0 && int32TypePos > 0) {
            maxLength = int32TypePos + 2;
        }
        if (int64TypePos == 0 && int32TypePos == 0 && int16TypePos > 0) {
            maxLength = int16TypePos + 1;
        }
        if (int64TypePos == 0 && int32TypePos == 0 && int16TypePos == 0 && stringTypePos > 0) {
            maxLength = stringTypePos + 1;
        }
        if (int64TypePos == 0 && int32TypePos == 0 && int16TypePos == 0 && stringTypePos == 0 && doubleTypeLastPos > 0) {
            maxLength = doubleTypeLastPos + 1;
        }
        return maxLength;
    }

    /**
     * Sort fields by order: DOUBLE->STRING->INT16->INT32->INT64
     *
     * @param dbTable
     */
    public List<TableField> sortFields(DBTable dbTable) {
        TableField[] fields = dbTable.getFields();
        TableField lastField = dbTable.getLastField();
        List<TableField> list = new ArrayList<TableField>(fields.length + 1);
        Collections.addAll(list, lastField);
        Collections.addAll(list, fields);
        for (TableField field : list) {
            if (field.getOrigType() == BasicType.DOUBLE) {
                doubleFields.add(field);
            }
            if (field.getOrigType() == BasicType.STRING) {
                stringFields.add(field);
            }
            if (field.getOrigType() == BasicType.INT16){
                int16Fields.add(field);
            }
            if (field.getOrigType() == BasicType.INT32){
                int32Fields.add(field);
            }
            if (field.getOrigType() == BasicType.INT64){
                int64Fields.add(field);
            }
        }
        Collections.sort(doubleFields);
        Collections.sort(stringFields);
        Collections.sort(int16Fields);
        Collections.sort(int32Fields);
        Collections.sort(int64Fields);

        totalFields.addAll(doubleFields);
        totalFields.addAll(stringFields);
        totalFields.addAll(int16Fields);
        totalFields.addAll(int32Fields);
        totalFields.addAll(int64Fields);
        return totalFields;
    }

    /**
     * Build table fields by order
     * DOUBLE->STRING->INT16->INT32->INT64
     */
    public List<MDBTableField> buildFields(String tableID, boolean needCheck) {
        if (hasDoubleTypeOfField()) {
            List<MDBTableField> fields = buildDoubleFields(tableID, needCheck);
            tableFields.addAll(fields);
        }
        if (hasStringTypeOfField()) {
            List<MDBTableField> fields = buildStringFields(tableID, needCheck);
            tableFields.addAll(fields);
        }
        if (hasInt16TypeOfField()) {
            List<MDBTableField> fields = buildInt16Fields(tableID, needCheck);
            tableFields.addAll(fields);
        }
        if (hasInt32TypeOfField()) {
            List<MDBTableField> fields = buildInt32Fields(tableID, needCheck);
            tableFields.addAll(fields);
        }
        if (hasInt64TypeOfField()) {
            List<MDBTableField> fields = buildInt64Fields(tableID, needCheck);
            tableFields.addAll(fields);
        }
        return tableFields;
    }

    private List<MDBTableField> buildDoubleFields(String tableID, boolean needCheck) {
        List<MDBTableField> resultFields = new ArrayList<>();
        if (doubleFields.isEmpty()) {
            return resultFields;
        }

        for (TableField field : doubleFields) {
            resultFields.add(MDBTableField.builder()
                    .id(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .name(field.getName())
                    .type(field.getOrigType().name())
                    .len(DOUBLE_FIELD_LENGTH)
                    .pos(doubleTypeLastPos)
                    .build());
            if (field.getOrigType() == BasicType.DOUBLE) {
                doubleTypeLastPos += DOUBLE_FIELD_LENGTH;
            }
        }
        currentPos = doubleTypeLastPos + DOUBLE_FIELD_LENGTH;
        return resultFields;
    }

    private List<MDBTableField> buildStringFields(String tableID, boolean needCheck) {
        List<MDBTableField> resultFields = new ArrayList<>();
        if (stringFields.isEmpty()) {
            return resultFields;
        }
        int startPos = currentPos;
        for (TableField field : stringFields) {
            resultFields.add(MDBTableField.builder()
                    .id(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .name(field.getName())
                    .type(field.getOrigType().name())
                    .len(Integer.parseInt(field.getLength()) + 1)
                    .pos(startPos)
                    .build());
            if (field.getOrigType() == BasicType.STRING) {
                startPos = Integer.parseInt(field.getLength()) + 1 + startPos;
            }
        }
        stringTypePos = startPos;
        currentPos = stringTypePos;
        return resultFields;
    }

    private List<MDBTableField> buildInt16Fields(String tableID, boolean needCheck) {
        List<MDBTableField> resultFields = new ArrayList<>();
        if (int16Fields.isEmpty()) {
            return resultFields;
        }
        int startPos = currentPos;
        for (TableField field : int16Fields) {
            resultFields.add(MDBTableField.builder()
                    .id(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .name(field.getName())
                    .type(field.getOrigType().name())
                    .len(Integer.parseInt(field.getLength()))
                    .pos(currentPos)
                    .build());
            if (field.getOrigType() == BasicType.INT16) {
                startPos += INT16_FIELD_LENGTH;
            }
        }
        int16TypePos = startPos;
        currentPos = int16TypePos + INT16_FIELD_LENGTH;
        return resultFields;
    }

    private List<MDBTableField> buildInt32Fields(String tableID, boolean needCheck) {
        List<MDBTableField> resultFields = new ArrayList<>();
        if (int32Fields.isEmpty()) {
            return resultFields;
        }
        int startPos = currentPos;
        for (TableField field : int32Fields) {
            resultFields.add(MDBTableField.builder()
                    .id(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .name(field.getName())
                    .type(field.getOrigType().name())
                    .len(Integer.parseInt(field.getLength()))
                    .pos(currentPos)
                    .build());
            if (field.getOrigType() == BasicType.INT32) {
                startPos += INT32_FIELD_LENGTH;
            }
        }
        int32TypePos = startPos;
        currentPos = int32TypePos + INT32_FIELD_LENGTH;
        return resultFields;
    }

    private List<MDBTableField> buildInt64Fields(String tableID, boolean needCheck) {
        List<MDBTableField> resultFields = new ArrayList<>();
        if (int64Fields.isEmpty()) {
            return resultFields;
        }
        int startPos = currentPos;
        for (TableField field : int64Fields) {
            resultFields.add(MDBTableField.builder()
                    .id(needCheck ? Integer.parseInt("1000" + tableID) : Integer.parseInt(tableID))
                    .name(field.getName())
                    .type(field.getOrigType().name())
                    .len(Integer.parseInt(field.getLength()))
                    .pos(currentPos)
                    .build());
            if (field.getOrigType() == BasicType.INT64) {
                startPos += INT64_FIELD_LENGTH;
            }
        }
        int64TypePos = startPos;
        currentPos = int64TypePos + DEFAULT_OFFSET_LENGTH;
        return resultFields;
    }

    private boolean hasDoubleTypeOfField() {
        return this.stringFields.size() > 0;
    }

    private boolean hasInt16TypeOfField() {
        return this.int16Fields.size() > 0;
    }

    private boolean hasInt32TypeOfField() {
        return this.int32Fields.size() > 0;
    }

    private boolean hasInt64TypeOfField() {
        return this.int64Fields.size() > 0;
    }

    private boolean hasStringTypeOfField() {
        return this.stringFields.size() > 0;
    }

}
