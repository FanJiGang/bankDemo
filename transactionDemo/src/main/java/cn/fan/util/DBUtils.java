package cn.fan.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    private static BasicDataSource bds;

    static {
        Properties ppt = new Properties();
        try {
            ppt.load(DBUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = ppt.getProperty("driverClassName");
            url = ppt.getProperty("url");
            username = ppt.getProperty("username");
            password = ppt.getProperty("password");
            bds = new BasicDataSource();
            bds.setDriverClassName(driver);
            bds.setUrl(url);
            bds.setUsername(username);
            bds.setPassword(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConn(){
        Connection conn=null;
        try {
            conn = bds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //释放数据库连接
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //提交事务
    public static void commit(Connection conn){
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //回滚事务
    public static void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
