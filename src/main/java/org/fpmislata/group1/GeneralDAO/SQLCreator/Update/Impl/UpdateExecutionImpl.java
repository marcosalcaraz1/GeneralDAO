package org.fpmislata.group1.GeneralDAO.SQLCreator.Update.Impl;

import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateCreator;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateQuery;
import org.fpmislata.group1.GeneralDAO.SQLCreator.common.Query;

public class UpdateExecutionImpl implements UpdateExecution {

    private final UpdateQuery updateQuery = new UpdateQueryImpl();

    private final DBUtil dbUtil;

    public UpdateExecutionImpl(DBUtil dbUtil){
        this.dbUtil = dbUtil;
    }

    @Override
    public UpdateCreator table(String table) {
        return updateQuery.table(table);
    }

    @Override
    public UpdateCreator whereIsEquals(String field, Object value) {
        return updateQuery.whereIsEquals(field,value);
    }

    @Override
    public UpdateCreator setField(String field, Object value) {
        return updateQuery.setField(field,value);
    }

    @Override
    public void execute() {
        Query query = this.updateQuery.getQuery();
        dbUtil.update(query.getSql(), query.getValues());
    }
}
