package org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl;

import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtil;

import java.sql.*;
import java.util.List;

public class DBUtilImpl implements DBUtil {

    private static DBUtilImpl instance;
    public Connection connection;

    public static DBUtilImpl getInstance(){
        if(instance == null){
            instance = new DBUtilImpl();
        }
        return instance;
    }


    private DBUtilImpl(){

    }

    public DBUtilImpl setConnection(ConfigConnection config){
        return setConnection(config,true);
    }

    public DBUtilImpl setConnection(ConfigConnection config,boolean setAutocommit) {
        if (config == null)
            throw new NullPointerException();
        try {
            if (this.connection != null)
                this.connection.close();
            this.connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            this.connection.setAutoCommit(setAutocommit);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return this;
    }


    @Override
    public ResultSet select(String sql, List<Object> params) {
        if(sql == null)
            throw new NullPointerException();
        try{
            return getPreparedStatement(sql, params).executeQuery();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }


    }


    @Override
    public void insert(String sql, List<Object> params) {
        if(sql == null)
            throw new NullPointerException();
        try{
            getPreparedStatement(sql,params).executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int remove(String sql, List<Object> params) {
        if(sql == null)
            throw new NullPointerException();
        try{
            return getPreparedStatement(sql,params).executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int update(String sql, List<Object> params) {
        if(sql == null)
            throw new NullPointerException();
        try{
            return getPreparedStatement(sql,params).executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void commit() {
        try{
            connection.commit();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void rollback() {
        try{
            connection.rollback();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private PreparedStatement getPreparedStatement(String sql, List<Object> params) throws SQLException {
        PreparedStatement preparedStatement =  connection.prepareStatement(sql);
        if(params != null)
            setParams(preparedStatement, params);
        return preparedStatement;
    }

    private void setParams(PreparedStatement preparedStatement,List<Object> objectList) throws SQLException {
        for(int i = 0;i< objectList.size();i++){
            Object object = objectList.get(i);
            preparedStatement.setObject(i+1,object);
        }
    }

}
