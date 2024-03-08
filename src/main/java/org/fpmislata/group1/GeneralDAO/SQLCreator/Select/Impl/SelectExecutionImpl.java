package org.fpmislata.group1.GeneralDAO.SQLCreator.Select.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectExecution;
import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectQuery;

import java.sql.ResultSet;

public class SelectExecutionImpl implements SelectExecution {

    private final DBUtil dbUtil;
    private final SelectQuery selectQuery = new SelectQueryImpl();

    public SelectExecutionImpl(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }


    @Override
    public SelectCreator fields(String... fields) {
        return this.selectQuery.fields(fields);
    }

    @Override
    public SelectCreator from(String table) {
        return this.selectQuery.from(table);
    }

    @Override
    public ResultSet get() {
        Query query = this.selectQuery.getQuery();
        return dbUtil.select(query.getSql(), query.getValues());
    }
}
