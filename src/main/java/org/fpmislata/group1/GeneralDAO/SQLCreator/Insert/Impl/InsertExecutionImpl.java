package org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.Impl;

import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertExecution;

import java.util.List;

public class InsertExecutionImpl implements InsertExecution {


    private final DBUtil dbUtil;
    private final InsertQuery insertQuery = new InsertQueryImpl();


    public InsertExecutionImpl(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }


    @Override
    public InsertCreator into(String table) {
        return insertQuery.into(table);
    }

    @Override
    public InsertCreator fields(String... fields) {
        return insertQuery.fields(fields);
    }

    @Override
    public InsertCreator value(List<Object> valueList) {
        return insertQuery.value(valueList);
    }

    @Override
    public void execute() {
        Query query = this.insertQuery.getQuery();
        dbUtil.select(query.getSql(), query.getValues());
    }
}
