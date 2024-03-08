package org.fpmislata.group1.GeneralDAO.SQLCreator.Insert;

import java.util.List;

public interface InsertCreator {
    InsertCreator into(String table);
    InsertCreator fields(String... fields);
    InsertCreator value(List<Object> valueList);

}
