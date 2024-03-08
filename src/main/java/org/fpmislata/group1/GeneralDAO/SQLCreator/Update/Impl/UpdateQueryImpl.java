package org.fpmislata.group1.GeneralDAO.SQLCreator.Update.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

import java.util.ArrayList;
import java.util.List;

public class UpdateQueryImpl implements UpdateQuery {

    private String table = null;
    private String where = null;
    private List<Object> objectListWhere = new ArrayList<>();
    private String set;
    private List<Object> objectListSet = new ArrayList<>();


    @Override
    public Query getQuery() {
        List<Object> params = new ArrayList<>(objectListSet);
        params.addAll(this.objectListWhere);
        return new Query(constructSql(),params);
    }

    private String constructSql() {
        String sql = "UPDATE";
        sql = addTable(sql);
        sql = addSet(sql);
        sql = addWhere(sql);
        return sql;
    }


    private String addTable(String sql) {
        if(table != null){
            sql += " "+table;
        }
        return sql;
    }

    private String addSet(String sql) {
        if(this.set != null){
            sql += this.set;
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
    public UpdateCreator table(String table) {
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
    public UpdateCreator whereIsEquals(String field, Object value) {
        if(field == null || value == null)
            throw new NullPointerException();
        this.where = " WHERE "+ field + "=?";
        this.objectListWhere = new ArrayList<>(List.of(value));
        return this;
    }

    @Override
    public UpdateCreator setField(String field, Object value) {
        if(field == null)
            throw new NullPointerException();
        if(this.set == null){
            this.set = " SET "+field+"=?";
        }else{
            this.set += ", "+field+"=?";
        }
        this.objectListSet.add(value);
        return this;
    }

}
