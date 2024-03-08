package org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.Impl;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteExecution;
import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

public class DeleteExecutionImpl implements DeleteExecution {

    private final DeleteQuery deleteQuery = new DeleteQueryImpl();
    private final DBUtil dbUtil;

    public DeleteExecutionImpl(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }


    @Override
    public DeleteCreator from(String table) {
        return this.deleteQuery.from(table);
    }

    @Override
    public DeleteCreator whereIsEquals(String field, Object value) {
        return this.deleteQuery.whereIsEquals(field,value);
    }

    @Override
    public void execute() {
        Query query = this.deleteQuery.getQuery();
        dbUtil.remove(query.getSql(),query.getValues());
    }
}
