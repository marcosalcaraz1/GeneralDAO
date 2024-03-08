package org.fpmislata.group1.GeneralDAO.common.ProgramPropertiesImpl;

import org.fpmislata.group1.GeneralDAO.common.ProgramProperties;
import org.fpmislata.group1.GeneralDAO.RawSQL.DBUtilImpl.ConfigConnection;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class GeneralProperties implements org.fpmislata.group1.GeneralDAO.common.ProgramProperties {

    public static final ProgramProperties ProgramProperties = getProgramProperties();
    private final ConfigConnection configConnection;

    public GeneralProperties(String url){
        if(url == null)
            throw new NullPointerException();

        Properties appProps = getProperties(getAbsoluteUrl(url));
        this.configConnection = getConfigConnection(appProps);
    }

    private static Properties getProperties(String appConfigPath) {
        Properties appProps = new Properties();
        try {
            appProps.load(Files.newInputStream(Paths.get(appConfigPath)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return appProps;
    }

    private static String getAbsoluteUrl(String url) {
        return System.getProperty("user.dir") + (url.startsWith("/") ? "" : "/") + url;
    }

    private static GeneralProperties getProgramProperties() {
        final String urlProgramProperties = "src/main/resources/program.properties";
        try{
            return new GeneralProperties(urlProgramProperties);
        }catch (Exception e){
            return null;
        }
    }

    private ConfigConnection getConfigConnection(Properties appProps) {
        String prefixProperty = "connection";
        String user = appProps.getProperty(prefixProperty +".user");
        String password = appProps.getProperty(prefixProperty +".password");
        String urlConfig = appProps.getProperty(prefixProperty +".url");
        if(user == null || password == null || urlConfig == null)
            return null;
        return new ConfigConnection(urlConfig,user,password);
    }

    @Override
    public ConfigConnection getConfigConnection() {
        return this.configConnection;
    }
}
