package org.fpmislata.group1.GeneralDAO.SQLCreator.common;

import java.util.ArrayList;
import java.util.List;

public class Query {
    private String sql;
    private List<Object> values;

    public Query() {
        this("",new ArrayList<>());
    }

    public Query(String sql, List<Object> values) {
        setSql(sql);
        setValues(values);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        if(sql == null)
            throw new NullPointerException();
        this.sql = sql;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        if(values == null)
            throw new NullPointerException();
        this.values = values;
    }
}
