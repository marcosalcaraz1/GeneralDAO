package org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

import java.util.ArrayList;
import java.util.List;

public class DeleteQueryImpl implements DeleteQuery {

    private String table = null;
    private String where = null;
    private List<Object> objectList = new ArrayList<>();


    @Override
    public Query getQuery() {
        return new Query(constructSql(), new ArrayList<>(objectList));
    }

    private String constructSql() {
        String sql = "DELETE";
        sql = addTable(sql);
        sql = addWhere(sql);
        return sql;
    }

    private String addTable(String sql) {
        if(table != null){
            sql += " FROM "+table;
        }
        return sql;
    }

    private String addWhere(String sql){
        if(where != null){
            sql += where;
        }
        return sql;
    }


    @Override
    public DeleteCreator from(String table) {
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


    @Override
    public DeleteCreator whereIsEquals(String field, Object value) {
        if(field == null || value == null)
            throw new NullPointerException();
        this.where = " WHERE "+ field + "=?";
        this.objectList = new ArrayList<>(List.of(value));
        return this;
    }
}
