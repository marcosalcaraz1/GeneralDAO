package org.fpmislata.group1.GeneralDAO.DAO;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    List<T> getAll();
    Optional<T> findById(int id);
    void insert(T value);
    void delete(int id);
    void update(int id,T value);
}
