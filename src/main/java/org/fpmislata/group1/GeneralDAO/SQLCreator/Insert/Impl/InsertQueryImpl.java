package org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InsertQueryImpl implements InsertQuery {

    private String table;
    List<Object> valueList = new ArrayList<>();
    private List<String> fieldList = new ArrayList<>();

    @Override
    public Query getQuery() {
        return new Query(constructSql(),new ArrayList<>(valueList));
    }

    private String constructSql() {
        String sql = "INSERT";
        sql = addTable(sql);
        sql = addFields(sql);
        sql = addValues(sql);
        return sql;
    }

    private String addTable(String sql) {
        if(table != null){
            sql += " INTO "+table;
        }
        return sql;
    }

    private String addFields(String sql) {
        if(!fieldList.isEmpty()){
            sql +=" "+ constructFieldsClause();
        }
        return sql;
    }

    private String constructFieldsClause() {
        StringBuilder sql = new StringBuilder();
        sql.append("(");
        for(String field: fieldList){
            sql.append(field).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }

    private String addValues(String sql){
        if(!this.valueList.isEmpty()){
            sql += " VALUE " + constructValueClause();
        }
        return sql;
    }

    private String constructValueClause() {
        StringBuilder sql = new StringBuilder();
        sql.append("(");
        for(int i = 0;i<valueList.size();i++){
            sql.append("?").append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        return sql.toString();
    }




    @Override
    public InsertCreator into(String table) {
        setTable(table);
        return this;
    }

    private void setTable(String table) {
        validateTable(table);
        this.table = table;
    }

    private static void validateTable(String table) {
        if(table == null)
            throw new NullPointerException();
    }





    @Override
    public InsertCreator value(List<Object> valueList) {
        setValueList(valueList);
        return this;
    }

    public void setValueList(List<Object> valueList) {
        validateValueList(valueList);
        this.valueList = new ArrayList<>(valueList);
    }

    private static void validateValueList(List<Object> valueList) {
        if(valueList == null)
            throw new NullPointerException();
    }


    @Override
    public InsertCreator fields(String... fields) {
        this.setFieldList(parseToList(fields));
        return this;
    }

    private static ArrayList<String> parseToList(String[] fields) {
        return new ArrayList<>(Arrays.stream(fields).toList());
    }

    private void setFieldList(List<String> fieldList) {
        validateFields(fieldList);
        this.fieldList = fieldList;
    }

    private static void validateFields(List<String> fields) {
        if(fields == null || fields.stream().anyMatch(Objects::isNull))
            throw new NullPointerException();
    }



}
