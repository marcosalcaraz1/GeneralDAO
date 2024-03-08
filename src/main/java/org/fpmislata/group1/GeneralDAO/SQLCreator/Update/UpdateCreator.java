package org.fpmislata.group1.GeneralDAO.SQLCreator.Update;

public interface UpdateCreator {
    UpdateCreator table(String table);
    UpdateCreator whereIsEquals(String field,Object value);
    UpdateCreator setField(String field,Object value);
}
