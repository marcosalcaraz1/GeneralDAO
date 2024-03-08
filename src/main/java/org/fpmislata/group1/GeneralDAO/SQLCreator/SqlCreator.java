package org.fpmislata.group1.GeneralDAO.SQLCreator;

import org.fpmislata.group1.GeneralDAO.SQLCreator.Delete.DeleteExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Select.SelectExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Update.UpdateExecution;
import org.fpmislata.group1.GeneralDAO.SQLCreator.Insert.InsertExecution;

public interface SqlCreator {
    SelectExecution select();
    InsertExecution insert();
    UpdateExecution update();
    DeleteExecution delete();
}
