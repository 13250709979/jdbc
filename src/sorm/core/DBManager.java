package sorm.core;

import sorm.bean.Configuration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBManager {
    private static Configuration configuration;

    /**
     * 静态代码块，初始化执行，且仅执行一次
     */
    static{
        Properties pro=new Properties();
        try {
            pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 获取db.properties文件的配置
         * 配置Configuration
         */
        configuration=new Configuration();
        configuration.setDriver(pro.getProperty("driver"));
        configuration.setPoPackage(pro.getProperty("poPackage"));
        configuration.setPwd(pro.getProperty("pwd"));
        configuration.setSrcPath(pro.getProperty("srcPath"));
        configuration.setUrl(pro.getProperty("url"));
        configuration.setUser(pro.getProperty("user"));
        configuration.setUsingDB(pro.getProperty("usingDB"));
    }

    /**
     * 直接建立连接
     * @return
     */
    public static Connection getConnection(){
        try {
            Class.forName(configuration.getDriver());
            return DriverManager.getConnection(configuration.getUrl(),configuration.getUser(),configuration.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取configuration
     * @return
     */
    public static Configuration getConfiguration(){
        return configuration;
    }

}
