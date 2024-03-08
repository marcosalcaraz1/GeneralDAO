package org.fpmislata.group1.GeneralDAO.RawSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface DBUtil {
    ResultSet select(String sql, List<Object> params);
    void insert(String sql,List<Object> params);
    int remove(String sql,List<Object> params);
    int update(String sql,List<Object> params);
    void commit();
    void rollback();
}
