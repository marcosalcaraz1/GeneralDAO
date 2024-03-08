package org.fpmislata.group1.GeneralDAO.SQLCreator;

import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;
import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl.DBUtilImpl;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.Impl.DeleteExecutionImpl;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.Impl.SelectExecutionImpl;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.Impl.UpdateExecutionImpl;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateExecution;
import org.fpmislata.group1.GeneralDAO.common.ProgramPropertiesImpl.GeneralProperties;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.Impl.InsertExecutionImpl;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertExecution;

public class SqlCreatorGeneralImpl implements SqlCreator{

    private final DBUtil dbUtil;

    public SqlCreatorGeneralImpl(){
        this.dbUtil = DBUtilImpl
                .getInstance()
                .setConnection(GeneralProperties.ProgramProperties.getConfigConnection());
    }


    @Override
    public SelectExecution select() {
        return new SelectExecutionImpl(dbUtil);
    }

    @Override
    public InsertExecution insert() {
        return new InsertExecutionImpl(dbUtil);
    }

    @Override
    public UpdateExecution update() {
        return new UpdateExecutionImpl(dbUtil);
    }

    @Override
    public DeleteExecution delete() {
        return new DeleteExecutionImpl(dbUtil);
    }
}
