package org.fpmislata.group1.GeneralDAO.DAO;

import java.util.List;
import java.util.Optional;

public class GenericDaoImpl<T> implements GenericDao<T>{

    Class<T> tClass;

    public GenericDaoImpl(Class<T> tClass){
        this.tClass = tClass;
    }



    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(T value) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, T value) {

    }
}
