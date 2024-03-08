package org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl;

public class ConfigConnection {

    String url,user,password;

    public ConfigConnection(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }


    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
