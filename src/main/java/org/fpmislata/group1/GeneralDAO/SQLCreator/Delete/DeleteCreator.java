package org.fpmislata.group1.GeneralDAO.SQLCreator.Delete;

public interface DeleteCreator {
    DeleteCreator from(String table);
    DeleteCreator whereIsEquals(String field,Object value);
}
