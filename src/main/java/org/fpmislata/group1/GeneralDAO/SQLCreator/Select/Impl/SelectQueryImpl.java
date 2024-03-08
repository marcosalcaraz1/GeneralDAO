package org.fpmislata.group1.GeneralDAO.SQLCreator.Select.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SelectQueryImpl implements SelectQuery {

    private List<String> fieldList = new ArrayList<>();
    private String table = null;

    @Override
    public Query getQuery() {
        return new Query(constructSql(),new ArrayList<>());
    }

    private String constructSql() {
        String sql = "SELECT";
        sql = addFields(sql);
        sql = addTable(sql);
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
        for(String field: fieldList){
            sql.append(field).append(",");
        }
        sql.deleteCharAt(sql.length() - 1);
        return sql.toString();
    }

    private String addTable(String sql) {
        if(table != null){
            sql += " FROM "+table;
        }
        return sql;
    }



    @Override
    public SelectCreator fields(String... fields) {
        setFieldList(parseToList(fields));
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



    @Override
    public SelectCreator from(String table) {
        this.setTable(table);
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

}
