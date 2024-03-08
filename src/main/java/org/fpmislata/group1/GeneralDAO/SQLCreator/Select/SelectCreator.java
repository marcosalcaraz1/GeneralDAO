package org.fpmislata.group1.GeneralDAO.SQLCreator.Select;

public interface SelectCreator {
    SelectCreator fields(String... fields);
    SelectCreator from(String table);
}
